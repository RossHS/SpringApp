create sequence hibernate_sequence start 1 increment 1;
create table currency_table
(
    id        int4 generated by default as identity,
    cbr_id    varchar(255),
    char_code varchar(255),
    name      varchar(255),
    nominal   int4,
    num_code  int4,
    primary key (id)
);
create table history_converter
(
    id            int4 generated by default as identity,
    convert_date  date,
    value_from    float8,
    value_to      float8,
    currency_from int4,
    currency_to   int4,
    user_id       int8,
    primary key (id)
);
create table rates
(
    id          int4 generated by default as identity,
    rate_date   date,
    rate_value  float8,
    currency_id int4,
    primary key (id)
);
create table user_role
(
    user_id int8 not null,
    roles   int4
);
create table users
(
    id       int8    not null,
    active   boolean not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
);
alter table if exists currency_table
    add constraint UK_gpuso8qf9s64n47miwwsni3rt unique (cbr_id);
alter table if exists history_converter
    add constraint FK2d2rskyqsqrg9ipkarbuqsjsp foreign key (currency_from) references currency_table;
alter table if exists history_converter
    add constraint FKd8otcfvtgnkkj5q026ffp3gn5 foreign key (currency_to) references currency_table;
alter table if exists history_converter
    add constraint FK5kolydbg94lf0nhyxf547xx7q foreign key (user_id) references users;
alter table if exists rates
    add constraint FKsk8ctkp9bx6n8l1r0tx4fvt3w foreign key (currency_id) references currency_table;