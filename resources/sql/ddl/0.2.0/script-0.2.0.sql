create table price_history (id int4 not null, btc_price float8, date_created timestamp, usd_price float8, ccy varchar(10) not null, snapshot_id int4 not null, primary key (id));
alter table if exists structure_element add column price_id int4;
alter table if exists price_history add constraint unique_ccy_snapshot_id unique (ccy, snapshot_id);
create sequence pricehistorysec start 1 increment 1;
alter table if exists price_history add constraint FK622va25bpefm78c9mfl9qtmkx foreign key (ccy) references asset;
alter table if exists price_history add constraint FKtgafmhr1qb57qmwly6unl4fyv foreign key (snapshot_id) references snapshot;
alter table if exists structure_element add constraint FK34mnbfrsf9n4ox43uikp7ug8n foreign key (price_id) references price_history;
alter table if exists current_structure add column aum float8;
alter table if exists structure_element add column quantity float8;
alter table if exists structure_element add column usd_value float8;

alter sequence snapshotsec increment by 1;
alter sequence structureelementsec increment by 1;
alter sequence structuresec increment by 1;