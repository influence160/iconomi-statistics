package com.ott.iconomi.statistics.importer.utils;

import java.io.IOException;

@FunctionalInterface
public interface IOFunction<T, R> {

    R apply(T t) throws IOException;

}
