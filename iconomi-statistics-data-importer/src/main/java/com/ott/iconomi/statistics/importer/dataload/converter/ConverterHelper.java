package com.ott.iconomi.statistics.importer.dataload.converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public abstract class ConverterHelper {

    public static LocalDateTime epochSecondToLocalDateTime(int epochSecond) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(1633075455), TimeZone.getDefault().toZoneId());
    }
}
