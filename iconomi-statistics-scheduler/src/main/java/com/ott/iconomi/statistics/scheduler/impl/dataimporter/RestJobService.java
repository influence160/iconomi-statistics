package com.ott.iconomi.statistics.scheduler.impl.dataimporter;

import com.ott.iconomi.statistics.scheduler.job.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(name = "iconomi.statistics.scheduler.importer.source", havingValue = "REST_API")
public class RestJobService implements JobService {
    @Override
    public void importData() {
        log.info("Starting import data using REST_API implementation.");
        //TODO
        log.info("End import data using REST_API implementation.");
    }
}
