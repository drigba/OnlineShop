create table products
(
    id           serial
        primary key,
    name         varchar(255) not null,
    price        integer,
    product_type varchar(255),
    description  varchar(255),
    popularity   integer
);

alter table products
    owner to postgres;

create table customers
(
    id      serial
        primary key,
    name    varchar(255) not null,
    email   varchar(255) not null,
    address varchar(255)
);

alter table customers
    owner to postgres;

create table carts
(
    id        serial
        primary key,
    sum_price integer
);

alter table carts
    owner to postgres;

create table products_carts
(
    cart_id    integer
        references carts
            on update cascade on delete cascade,
    product_id integer
        references products
            on update cascade on delete cascade
);

alter table products_carts
    owner to postgres;

create table orders
(
    id           serial
        primary key,
    from_address varchar(255) not null,
    address      varchar(255) not null,
    price        integer      not null,
    order_status varchar(255) not null
);

alter table orders
    owner to postgres;

create table orders_products
(
    order_id    integer
        references orders
            on delete cascade,
    products_id integer
        references products
            on delete cascade
);

alter table orders_products
    owner to postgres;


