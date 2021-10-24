package com.ott.iconomi.statistics.domain.mapper;

import com.ott.iconomi.statistics.data.entity.PriceHistoryEntity;
import com.ott.iconomi.statistics.domain.config.MapperSpringConfiguration;
import com.ott.iconomi.statistics.domain.model.PriceHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

@Mapper(config = MapperSpringConfiguration.class)
public interface PriceHistoryDomainMapper extends Converter<PriceHistory, PriceHistoryEntity> {

    @Mapping(target = "snapshot", ignore = true)
    @Override
    PriceHistoryEntity convert(PriceHistory domain);
}
