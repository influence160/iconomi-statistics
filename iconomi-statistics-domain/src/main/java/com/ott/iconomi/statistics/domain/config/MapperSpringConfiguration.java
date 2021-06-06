package com.ott.iconomi.statistics.domain.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.extensions.spring.SpringMapperConfig;

@MapperConfig(componentModel = "spring", uses = ConversionServiceAdapter.class)
@SpringMapperConfig(conversionServiceAdapterPackage ="com.ott.iconomi.statistics.domain.config")
//conversionServiceAdapterClassName ="MyAdapter"
//conversionServiceBeanName = "myConversionService"
public interface MapperSpringConfiguration {
}