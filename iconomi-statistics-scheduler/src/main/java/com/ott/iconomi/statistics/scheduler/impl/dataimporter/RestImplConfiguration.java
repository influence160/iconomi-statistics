package com.ott.iconomi.statistics.scheduler.impl.dataimporter;

import com.ott.iconomi.statistics.importer.DataImporterApplication;
import com.ott.iconomi.statistics.importer.DataImporterApplicationRunner;
import com.ott.iconomi.statistics.importer.DataImporterConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@Configuration
//@Import({DataImporterConfiguration.class})
@ComponentScan(basePackages = "com.ott.iconomi.statistics.importer"
        , excludeFilters = {
            @ComponentScan.Filter(
                    type = FilterType.ASSIGNABLE_TYPE,
                    classes = {
//                            DataImporterConfiguration.class,
                            DataImporterApplication.class,
                            DataImporterApplicationRunner.class,
                    })
        })
@ConditionalOnBean(RestJobService.class)
public class RestImplConfiguration {
}
