package com.ott.iconomi.statistics.domain.mapper;

import com.ott.iconomi.statistics.domain.config.MapperSpringConfiguration;
import com.ott.iconomi.statistics.domain.model.Strategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

import com.ott.iconomi.statistics.data.entity.StrategyEntity;

@Mapper(config = MapperSpringConfiguration.class)
public interface StrategyDomainMapper extends Converter<Strategy, StrategyEntity> {

	@Mapping(target = "currentStructure", ignore = true)
	public StrategyEntity convert(Strategy domain);

//	@AfterMapping
//	public default void afterMapping(Strategy strategy, @MappingTarget StrategyEntity target) {
//		System.out.println("afterMapping strategy target");
//		target.getCurrentStructure().setStrategy(target);
//	}
}
