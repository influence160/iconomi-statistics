package com.ott.iconomi.statistics.domain.mapper;

import com.ott.iconomi.statistics.domain.config.MapperSpringConfiguration;
import com.ott.iconomi.statistics.domain.model.Strategy;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import com.ott.iconomi.statistics.data.entity.StrategyEntity;

@Mapper(config = MapperSpringConfiguration.class)
public interface StrategyEntityMapper extends Converter<StrategyEntity, Strategy>{
	
	public Strategy convert(StrategyEntity entity);
}