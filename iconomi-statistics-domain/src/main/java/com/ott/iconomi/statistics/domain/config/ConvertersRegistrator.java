package com.ott.iconomi.statistics.domain.config;

import com.ott.iconomi.statistics.domain.mapper.*;
import com.ott.iconomi.statistics.domain.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConvertersRegistrator implements ApplicationListener<ContextRefreshedEvent> {
    ConverterRegistry conversionService;
    /*@Autowired */ AssetDomainMapper assetDomainMapper;
    /*@Autowired */ AssetEntityMapper assetEntityMapper;
    /*@Autowired */ CurrentStructureDomainMapper currentStructureDomainMapper;
    /*@Autowired */ CurrentStructureEntityMapper currentStructureEntityMapper;
    /*@Autowired */ SnapshotDomainMapper snapshotDomainMapper;
    /*@Autowired */ SnapshotEntityMapper snapshotEntityMapper;
    /*@Autowired */ StrategyDomainMapper strategyDomainMapper;
    /*@Autowired */ StrategyEntityMapper strategyEntityMapper;
    /*@Autowired */ StructureElementDomainMapper structureElementDomainMapper;
    /*@Autowired */ StructureElementEntityMapper structureElementEntityMapper;
    /*@Autowired */ StructureHistoricalDomainMapper structureHistoricalDomainMapper;
    /*@Autowired */ StructureHistoricalEntityMapper structureHistoricalEntityMapper;

    public ConvertersRegistrator(ConverterRegistry conversionService, AssetDomainMapper assetDomainMapper, AssetEntityMapper assetEntityMapper, CurrentStructureDomainMapper currentStructureDomainMapper, CurrentStructureEntityMapper currentStructureEntityMapper, SnapshotDomainMapper snapshotDomainMapper, SnapshotEntityMapper snapshotEntityMapper, StrategyDomainMapper strategyDomainMapper, StrategyEntityMapper strategyEntityMapper, StructureElementDomainMapper structureElementDomainMapper, StructureElementEntityMapper structureElementEntityMapper, StructureHistoricalDomainMapper structureHistoricalDomainMapper, StructureHistoricalEntityMapper structureHistoricalEntityMapper) {
        this.conversionService = conversionService;
        this.assetDomainMapper = assetDomainMapper;
        this.assetEntityMapper = assetEntityMapper;
        this.currentStructureDomainMapper = currentStructureDomainMapper;
        this.currentStructureEntityMapper = currentStructureEntityMapper;
        this.snapshotDomainMapper = snapshotDomainMapper;
        this.snapshotEntityMapper = snapshotEntityMapper;
        this.strategyDomainMapper = strategyDomainMapper;
        this.strategyEntityMapper = strategyEntityMapper;
        this.structureElementDomainMapper = structureElementDomainMapper;
        this.structureElementEntityMapper = structureElementEntityMapper;
        this.structureHistoricalDomainMapper = structureHistoricalDomainMapper;
        this.structureHistoricalEntityMapper = structureHistoricalEntityMapper;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("Registring converters");
        conversionService.addConverter(assetDomainMapper);
        conversionService.addConverter(assetEntityMapper);
        conversionService.addConverter(currentStructureDomainMapper);
        conversionService.addConverter(currentStructureEntityMapper);
        conversionService.addConverter(snapshotDomainMapper);
        conversionService.addConverter(snapshotEntityMapper);
        conversionService.addConverter(strategyDomainMapper);
        conversionService.addConverter(strategyEntityMapper);
        conversionService.addConverter(structureElementDomainMapper);
        conversionService.addConverter(structureElementEntityMapper);
        conversionService.addConverter(structureHistoricalDomainMapper);
        conversionService.addConverter(structureHistoricalEntityMapper);
    }
}
