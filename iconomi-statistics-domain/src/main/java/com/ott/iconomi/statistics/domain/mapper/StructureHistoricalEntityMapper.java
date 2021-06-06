package com.ott.iconomi.statistics.domain.mapper;

import com.ott.iconomi.statistics.domain.config.MapperSpringConfiguration;
import com.ott.iconomi.statistics.domain.model.StructureHistorical;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import com.ott.iconomi.statistics.data.entity.StructureHistoricalEntity;

@Mapper(config = MapperSpringConfiguration.class)
public interface StructureHistoricalEntityMapper extends Converter<StructureHistoricalEntity, StructureHistorical>{

    public StructureHistorical convert(StructureHistoricalEntity entity);
}