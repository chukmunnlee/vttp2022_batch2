drop database if exists eshop;

create database eshop;

use eshop;

create table customer (
	name varchar(32) not null,
	address varchar(128) not null,
	email varchar(128) not null,
	primary key(name)
);

create table purchase_order (
	order_id char(8) not null,
	order_date date,
	name varchar(32) not null,
	address varchar(128) not null,
	email varchar(128) not null,

	primary key(order_id),
	constraint fk_name foreign key(name)
		references customer(name)
);

create table line_item (
	id int auto_increment,
	item varchar(64) not null,
	quantity int default '1',
	order_id char(8) not null,

	primary key(id),
	constraint fk_line_item_order_id foreign key(order_id)
		references purchase_order(order_id)
);

create table order_status (
	id int auto_increment,
	order_id char(8) not null,
	delivery_id varchar(128),
	status enum('pending', 'dispatched') default 'pending',
	status_update timestamp default current_timestamp, 

	primary key(id),
	constraint fk_order_status_order_id foreign key(order_id)
		references purchase_order(order_id)
);

insert into customer(name, address, email) values
	('fred', '201 Cobblestone Lane', 'fredflintstone@bedrock.com'),
	('sherlock', '221B Baker Street, London', 'sherlock@consultingdetective.org'),
	('spongebob', '124 Conch Street, Bikini Bottom', 'spongebob@yahoo.com'),
	('jessica', '698 Candlewood Land, Cabot Cove', 'fletcher@gmail.com'),
	('dursley', '4 Privet Drive, Little Whinging Surrey', 'dursley@gmail.com');

grant all privileges on eshop.* to 'fred'@'%';

flush privileges;
