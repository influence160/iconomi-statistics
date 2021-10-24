package com.ott.iconomi.statistics.data.entity;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "price_history",
        uniqueConstraints = {
            @UniqueConstraint(name = "unique_ccy_snapshot_id", columnNames = {"ccy", "snapshot_id"})
        }
)
public class PriceHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pricehistorysec")
    @SequenceGenerator(name = "pricehistorysec", sequenceName = "pricehistorysec", allocationSize = 1)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ccy", nullable = false)
    private AssetEntity asset;

//    @OneToMany(mappedBy = "price")
//    private List<StructureElementEntity> structureElements;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "snapshot_id", nullable = false)
    private SnapshotEntity snapshot;

    @Column(name = "usd_price")
    private double usdPrice;

    @Column(name = "btc_price")
    private Double btcPrice;

    private LocalDateTime dateCreated;

    @PrePersist
    private void preInsert() {
        this.dateCreated = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AssetEntity getAsset() {
        return asset;
    }

    public void setAsset(AssetEntity asset) {
        this.asset = asset;
    }

    public SnapshotEntity getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(SnapshotEntity snapshot) {
        this.snapshot = snapshot;
    }

    public double getUsdPrice() {
        return usdPrice;
    }

    public void setUsdPrice(double usdPrice) {
        this.usdPrice = usdPrice;
    }

    public Double getBtcPrice() {
        return btcPrice;
    }

    public void setBtcPrice(Double btcPrice) {
        this.btcPrice = btcPrice;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
