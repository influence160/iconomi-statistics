package com.ott.iconomi.statistics.importer.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Iconomi API (both the REST API and the Websocket API) allows for {{@link #RATE_LIMIT_PER_MINUTE}} requests per minute.
 *
 * Cette classe permet de s'assurer de ne pas dépasser cette limite
 */
@Component
@Slf4j
public class RequestsLimitChecker {

    private static final int RATE_LIMIT_PER_MINUTE = 60;

    Long lastCycleStart;
//    private AtomicInteger requestsMade = new AtomicInteger(0);
    private int requestsMade = 0;

    public synchronized void checkBeforeRequests(int requestsToDo) {
        Assert.state(requestsToDo < RATE_LIMIT_PER_MINUTE, "requestsToDo cant be greater than RATE_LIMIT_PER_MINUTE.");
        if (lastCycleStart == null) {
            lastCycleStart = System.currentTimeMillis();
            return;
        }
        if (requestsMade + requestsToDo <= RATE_LIMIT_PER_MINUTE) {
            if (System.currentTimeMillis() - lastCycleStart > (60 * 1000)) {
                log.warn("System.currentTimeMillis() - lastCycleStart > 1 minute ... you should change the solution");
            }
            requestsMade += requestsToDo;
        } else {
            try {
                long sleepTime = (60 * 1000) - System.currentTimeMillis() + lastCycleStart + 1;
                log.info("sleeping " + sleepTime + " to avoid exeeding limit requests rate.");
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            requestsMade = requestsToDo;
            lastCycleStart = System.currentTimeMillis();
        }


    }

    public void reset() {
        lastCycleStart = null;
        requestsMade = 0;
    }
//    public synchronized void checkBeforeRequests(int requestsToDo) {
//        if (lastCycleStart == null) {
//            lastCycleStart = System.currentTimeMillis();
//        } else {
//            log.info("duree du cycle : " + (System.currentTimeMillis() - lastCycleStart));
//
//            log.info("requetes faites : " + requestsMade);
//            requestsMade += requestsToDo;
//        }
//    }
}
