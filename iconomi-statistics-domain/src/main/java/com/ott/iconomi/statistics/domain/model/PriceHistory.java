package com.ott.iconomi.statistics.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE, makeFinal = true)
@ToString(exclude = "snapshot")
public class PriceHistory {

    Asset asset;

    @NonFinal
    protected Snapshot snapshot;

    double usdPrice;

    Double btcPrice;

    LocalDateTime dateCreated;
}
