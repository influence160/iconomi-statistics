package com.ott.iconomi.statistics.importer.dataload.converter;

import com.ott.iconomi.statistics.domain.model.Asset;
import com.ott.iconomi.statistics.domain.model.CurrentStructure;
import com.ott.iconomi.statistics.domain.model.Strategy;
import com.ott.iconomi.statistics.domain.model.StructureElement;
import net.iconomi.api.client.model.Ticker;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.ott.iconomi.statistics.domain.model.Asset.OTHER_ASSETS_CCY;
import static com.ott.iconomi.statistics.domain.model.Asset.OTHER_ASSETS_NAME;

@Component
public class StrategyConverter {

    public Strategy convert(net.iconomi.api.client.model.Strategy strategy, net.iconomi.api.client.model.Structure structure, Ticker ticker) {
        Strategy.StrategyBuilder strategyBuilder = Strategy.builder();
        strategyBuilder.shortName(strategy.getTicker())
                .name(strategy.getName())
                .manager(strategy.getManager())
                //.dateCreated()
                //.dateUpdated()
                //.lastAction(structure.getLastRebalanced())
        ;

        CurrentStructure.CurrentStructureBuilder currentStructureBuilder = CurrentStructure.builder();
        currentStructureBuilder.strategyShortName(structure.getTicker())
                .dayReturn(ticker.getChange24h() != null ? ticker.getChange24h().floatValue() * 100 : null)
                .weekReturn(ticker.getChange7d() != null ? ticker.getChange7d().floatValue() * 100 : null)
                .monthReturn(ticker.getChange1m() != null ? ticker.getChange1m().floatValue() * 100 : null)
                .yearReturn(ticker.getChange12m() != null ? ticker.getChange12m().floatValue() * 100 : null)
                .allTimeReturn(ticker.getChangeAll() != null ? ticker.getChangeAll().floatValue() * 100 : null)
                .aum(ticker.getAum().doubleValue())
                .lastChange(ConverterHelper.epochSecondToLocalDateTime(structure.getLastRebalanced()))
                .numberOfChangesInLast30Days(structure.getMonthlyRebalancedCount());

        List<StructureElement> elements = structure.getValues().stream()
                .map(value -> StructureElement.builder()
                    .asset(value.getAssetTicker() != null ?
                            Asset.builder()
                            .ccy(value.getAssetTicker())
                            .name(value.getAssetName())
                            .build()
                            :
                            Asset.builder()
                                    .ccy(OTHER_ASSETS_CCY)
                                    .name(OTHER_ASSETS_NAME)
                                    .build()
                    )
                    .percent(value.getTargetWeight().floatValue() * 100)
                    .build())
                .collect(Collectors.toList());

        currentStructureBuilder.elements(elements);
        strategyBuilder.currentStructure(currentStructureBuilder.build());
        return strategyBuilder.build();
    }
}
