package com.ott.iconomi.statistics.importer.dataload;

import com.ott.iconomi.statistics.domain.model.Snapshot;
import net.iconomi.api.client.IconomiRestApi;

public class AllDataLoder {

    private IconomiRestApi restApi;

    public AllDataLoder(IconomiRestApi restApi) {
        this.restApi = restApi;
    }

    public Snapshot loadData() {
        Snapshot.SnapshotBuilder builder = Snapshot.builder();


        return builder.build();
    }
}
