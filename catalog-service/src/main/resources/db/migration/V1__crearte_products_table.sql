create  sequence product_id_seq start with 1 increment by 50;

create table products
(
    id bigint default nextval('product_id_seq') not null,
    code  text not null unique,
    name  text not null,
    description  text,
    image_url  text,
    price numeric not null,
    primary key (id)

);

--default: If a value for the id column is not provided during an INSERT operation, the database will automatically
--use the value generated by the nextval('product_id_seq') function.
--nextval('product_id_seq'): This function fetches the next number from the
--sequence product_id_seq (e.g., 1, 51, 101, etc.), and assigns that number to the id field.