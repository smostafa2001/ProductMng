CREATE TABLE product_tbl
(
  id bigint NOT NULL, --
  name_product character varying,
  date_manufacture date, --
  price double precision, --
  quantity integer, --
  CONSTRAINT uid PRIMARY KEY (id )
);
insert into product_tbl (id, name_product, date_manufacture, price, quantity) values (1, 'Macbook Air', '12/02/2021', 1200.00, 3);
