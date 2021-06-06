package com.ott.iconomi.statistics.domain.mapper;

import com.ott.iconomi.statistics.domain.config.MapperSpringConfiguration;
import com.ott.iconomi.statistics.domain.model.Snapshot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

import com.ott.iconomi.statistics.data.entity.SnapshotEntity;

@Mapper(config = MapperSpringConfiguration.class)
public interface SnapshotDomainMapper extends Converter<Snapshot, SnapshotEntity>{

	@Mapping(target = "currentStructures", ignore = true)
	@Override
	public abstract SnapshotEntity convert(Snapshot snapshot);


//	@AfterMapping
//	public default void afterMapping(Snapshot anySource, @MappingTarget SnapshotEntity target) {
//		System.out.println("afterMapping snapshot target");
//		target.getCurrentStructures().forEach(cs -> cs.setSnapshot(target));
//	}
}
