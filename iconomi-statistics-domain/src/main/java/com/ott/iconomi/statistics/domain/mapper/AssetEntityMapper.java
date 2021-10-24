package com.ott.iconomi.statistics.domain.mapper;

import com.ott.iconomi.statistics.domain.config.MapperSpringConfiguration;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import com.ott.iconomi.statistics.data.entity.AssetEntity;
import com.ott.iconomi.statistics.domain.model.Asset;

@Mapper(config = MapperSpringConfiguration.class)
public interface AssetEntityMapper extends Converter<AssetEntity, Asset>{

    @Override
    public Asset convert(AssetEntity entity);
}