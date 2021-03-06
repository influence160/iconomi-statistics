alter table if exists current_structure drop constraint if exists FKcye1wpnwqpp0ru3s3e2jr4wo5 ;
alter table if exists current_structure drop constraint if exists FKsbdopxbp8m8gea74jiuh0pd7l ;
alter table if exists current_structure_elements drop constraint if exists FKeb94shn4g6okrifq97st5tmkl ;
alter table if exists current_structure_elements drop constraint if exists FK2k4wx4ybdo8qvpqr1j1okm13s ;
alter table if exists structure_element drop constraint if exists FKmxtyjmqnwgal2noq3jf58dirw ;
alter table if exists structure_historical drop constraint if exists FKtc2hdmxb14hfarfbapqifgtdi ;
alter table if exists structure_historical drop constraint if exists FKlfe8m9ewikvyhgy1kmff6khlj ;
alter table if exists structure_historical_elements drop constraint if exists FKjq2llump5pfbwb7j2wq18x837 ;
alter table if exists structure_historical_elements drop constraint if exists FKf2vpdkevop9s17tvmtejkurlv ;
drop table if exists asset cascade ;
drop table if exists current_structure cascade ;
drop table if exists current_structure_elements cascade ;
drop table if exists snapshot cascade ;
drop table if exists strategy cascade ;
drop table if exists structure_element cascade ;
drop table if exists structure_historical cascade ;
drop table if exists structure_historical_elements cascade ;
drop sequence if exists snapshotsec ;
drop sequence if exists structureelementsec ;
drop sequence if exists structuresec ;
create sequence snapshotsec start 1 increment 50 ;
create sequence structureelementsec start 1 increment 50 ;
create sequence structuresec start 1 increment 50 ;
create table asset (ccy varchar(10) not null, name varchar(50) not null, other_assets boolean, stable boolean, primary key (ccy)) ;
create table current_structure (id int4 not null, all_time_return float4, date_created timestamp, date_started date, day_return float4, last_change timestamp, month_return float4, number_of_changes_in_last30days int4 not null, week_return float4, year_return float4, date_updated timestamp, snapshot_id int4 not null, strategy_id varchar(255) not null, primary key (id)) ;
create table current_structure_elements (current_structure_entity_id int4 not null, elements_id int4 not null) ;
create table snapshot (id int4 not null, comment varchar(255), end_time timestamp, fail_message varchar(255), failed boolean not null, start_time timestamp, primary key (id)) ;
create table strategy (short_name varchar(255) not null, date_created timestamp, date_updated timestamp, favorite boolean not null, last_action timestamp, manager varchar(255), name varchar(255), primary key (short_name)) ;
create table structure_element (id int4 not null, percent float4 not null, asset_id varchar(10), primary key (id)) ;
create table structure_historical (id int4 not null, all_time_return float4, date_created timestamp, date_started date, day_return float4, last_change timestamp, month_return float4, number_of_changes_in_last30days int4 not null, week_return float4, year_return float4, snapshot_id int4 not null, current_structure_id int4, primary key (id)) ;
create table structure_historical_elements (structure_historical_entity_id int4 not null, elements_id int4 not null) ;
alter table if exists asset add constraint UK_otknfh1h0k4kcduk3i986nyxp unique (name) ;
alter table if exists current_structure_elements add constraint UK_35pis661mw5tlww94wpl2i02o unique (elements_id) ;
alter table if exists structure_historical_elements add constraint UK_h4t71uk3n2olq6fbyvpnqrg2v unique (elements_id) ;
alter table if exists current_structure add constraint FKcye1wpnwqpp0ru3s3e2jr4wo5 foreign key (snapshot_id) references snapshot ;
alter table if exists current_structure add constraint FKsbdopxbp8m8gea74jiuh0pd7l foreign key (strategy_id) references strategy ;
alter table if exists current_structure_elements add constraint FKeb94shn4g6okrifq97st5tmkl foreign key (elements_id) references structure_element ;
alter table if exists current_structure_elements add constraint FK2k4wx4ybdo8qvpqr1j1okm13s foreign key (current_structure_entity_id) references current_structure ;
alter table if exists structure_element add constraint FKmxtyjmqnwgal2noq3jf58dirw foreign key (asset_id) references asset ;
alter table if exists structure_historical add constraint FKtc2hdmxb14hfarfbapqifgtdi foreign key (snapshot_id) references snapshot ;
alter table if exists structure_historical add constraint FKlfe8m9ewikvyhgy1kmff6khlj foreign key (current_structure_id) references current_structure ;
alter table if exists structure_historical_elements add constraint FKjq2llump5pfbwb7j2wq18x837 foreign key (elements_id) references structure_element ;
alter table if exists structure_historical_elements add constraint FKf2vpdkevop9s17tvmtejkurlv foreign key (structure_historical_entity_id) references structure_historical ;

