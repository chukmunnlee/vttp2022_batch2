drop database if exists mystore;

create database mystore;

use mystore;

create table purchase_order (
    order_id char(8) not null,
    name varchar(128) not null,
    order_date date not null,

    primary key(order_id)
);

create table line_item (
    item_id int auto_increment not null,
    description text not null,
    quantity int default '1',
    order_id char(8) not null,

    primary key(item_id),
    constraint fk_order_id
        foreign key(order_id) references purchase_order(order_id)
);