package com.ott.iconomi.statistics.importer;

import com.ott.iconomi.statistics.domain.events.SnapshotTakenEvent;
import com.ott.iconomi.statistics.domain.model.Snapshot;
import com.ott.iconomi.statistics.domain.repository.SnapshotRepository;
import com.ott.iconomi.statistics.importer.dataload.converter.StrategyConverter;
import com.ott.iconomi.statistics.importer.utils.IOFunction;
import com.ott.iconomi.statistics.importer.utils.RequestsLimitChecker;
import lombok.extern.slf4j.Slf4j;
import net.iconomi.api.client.IconomiApiBuilder;
import net.iconomi.api.client.IconomiRestApi;
import net.iconomi.api.client.model.Strategy;
import net.iconomi.api.client.model.Structure;
import net.iconomi.api.client.model.StructureElement;
import net.iconomi.api.client.model.Ticker;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RestApiDataImporter {

    private IconomiRestApi restApi;
    private RequestsLimitChecker requestsLimitChecker;
    private SnapshotRepository snapshotRepository;
    private StrategyConverter strategyConverter;
    private ApplicationEventPublisher applicationEventPublisher;

    public RestApiDataImporter(IconomiRestApi restApi, RequestsLimitChecker requestsLimitChecker, SnapshotRepository snapshotRepository, StrategyConverter strategyConverter, ApplicationEventPublisher applicationEventPublisher) {
        this.restApi = restApi;
        this.requestsLimitChecker = requestsLimitChecker;
        this.snapshotRepository = snapshotRepository;
        this.strategyConverter = strategyConverter;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void importData(
//            String apiKey, String apiSecret
    ) throws IOException {
        try {
            log.info("Snapshots count : " + snapshotRepository.count());
            Snapshot.SnapshotBuilder snapshotBuilder = Snapshot.builder();
            snapshotBuilder.startTime(LocalDateTime.now());
            requestsLimitChecker.checkBeforeRequests(1);
            List<Strategy> strategies = getRestApi().getStrategiesList();
            log.info("Loaded " + strategies.size() + " strategy.");
            List<com.ott.iconomi.statistics.domain.model.Strategy> domainStrategies = new ArrayList<>(strategies.size());
            for (Strategy strategy : strategies) {
                requestsLimitChecker.checkBeforeRequests(3);
                log.trace("loading strategy " + strategy.getTicker());
                strategy = repeatUntilNotNull(getRestApi()::getStrategy, strategy.getTicker());
                Assert.notNull(strategy, "strategy is null.");
                Structure structure = repeatUntilNotNull(getRestApi()::getStrategyStructure, strategy.getTicker());
                Assert.notNull(structure, "structure is null.");
                Ticker ticker = repeatUntilNotNull(getRestApi()::getStrategyPriceChanges, strategy.getTicker());
                Assert.notNull(ticker, "ticker is null.");
                domainStrategies.add(strategyConverter.convert(strategy, structure, ticker));
                log.debug("strategy " + strategy.getTicker() + " loaded.");
            }
            Set<com.ott.iconomi.statistics.domain.model.Strategy> strategiesSet = new HashSet<>(domainStrategies);
            snapshotBuilder.strategies(strategiesSet);
            snapshotBuilder.currentStructures(strategiesSet.stream().map(com.ott.iconomi.statistics.domain.model.Strategy::getCurrentStructure).collect(Collectors.toList()));
            saveSnapshotAfterEnd(snapshotBuilder);
        } catch (Exception e) {
            throw e;
        } finally {
            requestsLimitChecker.reset();
        }
    }

    private void saveSnapshotAfterEnd(Snapshot.SnapshotBuilder snapshotBuilder) {
        snapshotBuilder.failed(false);
        Snapshot snapshot = snapshotBuilder.build();
        log.info("Snapshot Taken with " + snapshot.getStrategies().size() + " strategies and " + snapshot.getCurrentStructures().size() + " structures.");
        log.debug("snapshot = " + snapshot  + "\nstrategies = " + snapshot.getStrategies() + "\nstructures = " + snapshot.getCurrentStructures());
        SnapshotTakenEvent event = new SnapshotTakenEvent(this, snapshot);
        applicationEventPublisher.publishEvent(event);
    }

    private static final int RATE_LIMIT_TIME = 60 * 1000;

    @Deprecated
    public <T, R> R repeatUntilNotNull(IOFunction<T, R> function, T argument)/*, int times*/ throws IOException {
        long startTime = System.currentTimeMillis();
        int i = 0;
//        while (i < times) {
        while (System.currentTimeMillis() - startTime <= RATE_LIMIT_TIME) {
            R result = function.apply(argument);
            if (result != null) {
                return result;
            } else {
//                log.error(function + "(" + argument + ") result is null for the " + (i+1) + " time, " + (times - i - 1) + " are tries left." );
                log.error(function + "(" + argument + ") result is null for the " + (i+1) + " time, " + (System.currentTimeMillis() - startTime) + " milliseconds of trying time is left." );
                try {
                    Thread.sleep(1000 * 5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
        }
        throw new NullPointerException(function + "(" + argument + ") result is null for the " + i+1 + " time. after " + (System.currentTimeMillis() - startTime) + " milliseconds.");
    }

    private IconomiRestApi getRestApi() {
        return restApi;
    }
}
