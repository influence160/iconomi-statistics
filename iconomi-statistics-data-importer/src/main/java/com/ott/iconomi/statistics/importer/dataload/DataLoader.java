package com.ott.iconomi.statistics.importer.dataload;

import java.util.List;

public interface DataLoader<D> {

    public List<D> loadAll();

    public D loadOne(String ticker);
}
