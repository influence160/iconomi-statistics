package com.ott.iconomi.statistics.domain.mapper;

import com.ott.iconomi.statistics.domain.config.MapperSpringConfiguration;
import com.ott.iconomi.statistics.domain.model.CurrentStructure;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

import com.ott.iconomi.statistics.data.entity.CurrentStructureEntity;

@Mapper(config = MapperSpringConfiguration.class)
public interface CurrentStructureEntityMapper extends Converter<CurrentStructureEntity, CurrentStructure>{

    @Mapping(target = "snapshot", ignore = true)
    //@Mapping(target = "strategy", ignore = true)//TODO strategy.shortName to strategyShortName
    @Override
    public CurrentStructure convert(CurrentStructureEntity entity);
}