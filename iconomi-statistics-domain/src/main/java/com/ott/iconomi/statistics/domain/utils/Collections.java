package com.ott.iconomi.statistics.domain.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collections {

    public <E> Set<E> setOf(E ... e) {
        return Stream.of(e).collect(Collectors.toSet());
    }
}
