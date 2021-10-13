package com.ott.iconomi.statistics.importer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataImporterApplicationRunner implements ApplicationRunner {

    private RestApiDataImporter restApiDataImporter;

    public DataImporterApplicationRunner(RestApiDataImporter restApiDataImporter) {
        this.restApiDataImporter = restApiDataImporter;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        String apiKey = args.getOptionValues("API_KEY").get(0);
//        String secretKey = args.getOptionValues("API_SECRET").get(0);
        restApiDataImporter.importData();
    }

}
