package com.ott.iconomi.statistics.domain.config;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import com.ott.iconomi.statistics.domain.mapper.*;

@Configuration
@Slf4j
public class DomainConfiguration {

//	@Bean(name="conversionService")
//	public ConversionService getConversionService() {
//	    ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
//	    bean.setConverters(...); //add converters
//	    bean.afterPropertiesSet();
//	    return bean.getObject();
//	}

//    @Bean
//    ConfigurableConversionService getConversionService() {
//    	ConfigurableConversionService conversionService = new DefaultConversionService();
//    	conversionService.addConverter(Mappers.getMapper(StrategyEntityMapper.class));
//    	conversionService.addConverter(Mappers.getMapper(StrategyDomainMapper.class));
//    	conversionService.addConverter(Mappers.getMapper(SnapshotDomainMapper.class));
//    	conversionService.addConverter(Mappers.getMapper(SnapshotEntityMapper.class));
//    	return conversionService;
//    }
//    @Bean
//    ConfigurableConversionService getConversionService(StrategyEntityMapper strategyEntityMapper, StrategyDomainMapper strategyDomainMapper,
//    		SnapshotDomainMapper snapshotDomainMapper, SnapshotEntityMapper snapshotEntityMapper) {
//    	ConfigurableConversionService conversionService = new DefaultConversionService();
//    	conversionService.addConverter(strategyEntityMapper);
//    	conversionService.addConverter(snapshotEntityMapper);
//    	conversionService.addConverter(snapshotDomainMapper);
//    	conversionService.addConverter(strategyDomainMapper);
//    	return conversionService;
//    }





	@Bean
	ConfigurableConversionService getConversionService() {
		ConfigurableConversionService conversionService = new DefaultConversionService();
		return conversionService;
	}


}
