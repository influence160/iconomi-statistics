package com.ott.iconomi.statistics.importer.dataload;

import com.ott.iconomi.statistics.importer.dataload.converter.Converter;
import net.iconomi.api.client.IconomiRestApi;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractDataLoader<D, A> implements DataLoader<D> {

    protected Converter<A, D> converter;
    protected IconomiRestApi restApi;

    public AbstractDataLoader(Converter<A, D> converter, IconomiRestApi restApi) {
        this.converter = converter;
        this.restApi = restApi;
    }

    public List<D> loadAll() {
        List<String> tickers = findAllTikers();
        return tickers.stream().map(this::loadOne).collect(Collectors.toList());
    }

    protected abstract List<String> findAllTikers();

    public D loadOne(String ticker) {
        A a = loadIconomiObject(ticker);
        return converter.convert(a);
    }

    protected abstract A loadIconomiObject(String ticker);
}
