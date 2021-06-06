package com.ott.iconomi.statistics.domain.mapper;

import com.ott.iconomi.statistics.domain.config.MapperSpringConfiguration;
import com.ott.iconomi.statistics.domain.model.Snapshot;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import com.ott.iconomi.statistics.data.entity.SnapshotEntity;

@Mapper(config = MapperSpringConfiguration.class)
public interface SnapshotEntityMapper extends Converter<SnapshotEntity, Snapshot> {

	@Override
	Snapshot convert(SnapshotEntity snapshotEntity);
	
}
