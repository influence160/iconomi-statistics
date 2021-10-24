package com.ott.iconomi.statistics.domain.mapper;

import com.ott.iconomi.statistics.domain.config.MapperSpringConfiguration;
import com.ott.iconomi.statistics.domain.model.StructureElement;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import com.ott.iconomi.statistics.data.entity.StructureElementEntity;

@Mapper(config = MapperSpringConfiguration.class)
public interface StructureElementDomainMapper extends Converter<StructureElement, StructureElementEntity> {

    @Override
    public StructureElementEntity convert(StructureElement domain);
}