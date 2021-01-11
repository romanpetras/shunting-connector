--liquibase formatted sql

--changeset r.petras:1

create table shuntingconn.locomotors (
        id                     serial not null,
        create_timestamp       timestamp,
        create_user_id         integer,
        create_user            varchar(255),
        update_timestamp       timestamp,
        update_user_id         integer,
        update_user            varchar(255),
        code                   varchar(255),
        description            varchar(255),
        primary key (id)
);


create table shuntingconn.main_maneuvers (
        id  serial                 not null,
        create_timestamp           timestamp,
        create_user_id             integer,
        create_user                varchar(255),
        update_timestamp           timestamp,
        update_user_id             integer,
        update_user                varchar(255),
        eta                        timestamp,
        etp                        timestamp,
        maneuver_type              varchar(255),
        trace_number               varchar(255),
        wagons_number              integer,
        arrival_point_id              integer,
        locomotor_ending_point_id     integer,
        maneuver_park_point_id        integer,
        regression_point_id           integer,
        simulation_request_id      integer,
        terminal_id                integer,
        wagon_type_id              integer,
        primary key (id)
);


create table shuntingconn.mission_tasks (
        id                              serial not null,
        create_timestamp                timestamp,
        create_user_id                  integer,
        create_user                     varchar(255),
        update_timestamp                timestamp,
        update_user_id                  integer,
        update_user                     varchar(255),
        couple                          boolean,
        decouple                        boolean,
        direction                       varchar(255),
        sequence                        integer,
        split                           boolean,
        split_number                    integer,
        split_part                      integer,
        split_position                  varchar(255),
        train_part                      integer,
        arrival_point_id                   integer,
        departure_point_id                 integer,
        main_maneuver_id                integer,
        mission_id                      integer,
        primary key (id)
);


create table shuntingconn.missions (
        id                           serial not null,
        create_timestamp             timestamp,
        create_user_id               integer,
        create_user                  varchar(255),
        update_timestamp             timestamp,
        update_user_id               integer,
        update_user                  varchar(255),
        cut_number                   integer,
        start_date_time              timestamp,
        trace_number                 varchar(255),
        train_split                  boolean,
        locomotor_id                 integer,
        locomotor_starting_point_id     integer,
        simulation_request_id        integer,
        train_ending_point_id           integer,
        train_starting_point_id         integer,
        wagon_cut_ending_point_id       integer,
        primary key (id)
);


create table shuntingconn.points (
        id                         serial not null,
        create_timestamp           timestamp,
        create_user_id             integer,
        create_user                varchar(255),
        update_timestamp           timestamp,
        update_user_id             integer,
        update_user                varchar(255),
        code                       varchar(255),
        description                varchar(255),
        in_maneuver_park           boolean,
        locomotor_end              boolean,
        port_entrance              boolean,
        regression                 boolean,
        stop                       boolean,
        primary key (id)
);


create table shuntingconn.simulation_requests (
        id                    serial not null,
        create_timestamp      timestamp,
        create_user_id        integer,
        create_user           varchar(255),
        update_timestamp      timestamp,
        update_user_id        integer,
        update_user           varchar(255),
        status                integer,
        primary key (id)
);


create table shuntingconn.terminals (
        id                      serial not null,
        create_timestamp        timestamp,
        create_user_id          integer,
        create_user             varchar(255),
        update_timestamp        timestamp,
        update_user_id          integer,
        update_user             varchar(255),
        code                    varchar(255),
        description             varchar(255),
        primary key (id)
);


create table shuntingconn.wagon_types (
        id                        serial not null,
        create_timestamp          timestamp,
        create_user_id            integer,
        create_user               varchar(255),
        update_timestamp          timestamp,
        update_user_id            integer,
        update_user               varchar(255),
        code                      varchar(255),
        description               varchar(255),
        primary key (id)
);

--changeset m.tebaldi:2

alter table shuntingconn.simulation_requests
    alter column status TYPE varchar(32);
