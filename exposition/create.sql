create sequence customer_seq start 1 increment 50
create sequence item_seq start 1 increment 50
create sequence order_seq start 1 increment 50
create table customer (id int8 not null, name varchar(255), password varchar(255), version int4 not null, primary key (id))
create table item (id int8 not null, description varchar(255), price int4, version int4 not null, primary key (id))
create table orders (id int8 not null, total int4, version int4 not null, customer_id int8, primary key (id))
create table orders_items (order_id int8 not null, items_id int8 not null)
alter table if exists orders add constraint FK624gtjin3po807j3vix093tlf foreign key (customer_id) references customer
alter table if exists orders_items add constraint FKpend8sfg922gwhn2d3n457khv foreign key (items_id) references item
alter table if exists orders_items add constraint FKij1wwgx6o198ubsx1oulpopem foreign key (order_id) references orders
