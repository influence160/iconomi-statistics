package com.ott.iconomi.statistics.domain.mapper;

import com.ott.iconomi.statistics.domain.config.MapperSpringConfiguration;
import com.ott.iconomi.statistics.domain.model.CurrentStructure;
import org.mapstruct.*;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import com.ott.iconomi.statistics.data.entity.CurrentStructureEntity;

@Mapper(config = MapperSpringConfiguration.class)
public interface CurrentStructureDomainMapper extends Converter<CurrentStructure, CurrentStructureEntity> {

    @Mapping(target = "snapshot", ignore = true)
    @Mapping(target = "strategy", ignore = true)
    public CurrentStructureEntity convert(CurrentStructure domain);

}
