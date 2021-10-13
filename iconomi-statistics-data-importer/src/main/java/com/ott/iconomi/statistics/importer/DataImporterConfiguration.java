package com.ott.iconomi.statistics.importer;

import net.iconomi.api.client.IconomiApiBuilder;
import net.iconomi.api.client.IconomiRestApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataImporterConfiguration {


    @Bean
    public IconomiRestApi restApi(@Value("${API_KEY}") String apiKey, @Value("${API_SECRET}") String apiSecret) {
        return new IconomiApiBuilder()
                .setApiKey(apiKey, apiSecret)
                .buildRestApi();
    }

}
