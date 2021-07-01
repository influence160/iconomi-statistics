package com.ott.iconomi.statistics.importer.dataload.converter;

public interface Converter <A, D>{

    D convert(A a);
}
