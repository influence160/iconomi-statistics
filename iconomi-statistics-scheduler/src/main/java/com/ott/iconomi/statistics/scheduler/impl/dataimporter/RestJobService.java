package com.ott.iconomi.statistics.scheduler.impl.dataimporter;

import com.ott.iconomi.statistics.importer.RestApiDataImporter;
import com.ott.iconomi.statistics.scheduler.job.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@ConditionalOnProperty(name = "iconomi.statistics.scheduler.importer.source", havingValue = "REST_API")
public class RestJobService implements JobService {

    private RestApiDataImporter restApiDataImporter;

    public RestJobService(RestApiDataImporter restApiDataImporter, @Value("${API_KEY}") String apiKey, @Value("${API_SECRET}") String apiSecret) {
        this.restApiDataImporter = restApiDataImporter;
    }

    @Override
    public void importData() throws Exception {
        log.info("Starting import data using REST_API implementation.");
        try {
            restApiDataImporter.importData();
            log.info("End import data using REST_API implementation.");
        } catch (IOException e) {
            log.error("Exception importData.", e);
            throw e;
        }
    }
}
