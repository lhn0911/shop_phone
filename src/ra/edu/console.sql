CREATE DATABASE PhoneStore_DB;
USE PhoneStore_DB;
# DROP DATABASE PhoneStore_DB;
CREATE TABLE admin
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
-- ===
delimiter //

create procedure login(
    in p_username varchar(150),
    in p_password varchar(150),
    out p_result int
)
begin
    declare v_count int;

select count(a.id)
into v_count
from admin a
where username = p_username
  and password = p_password;
if v_count > 0 then
        set p_result = 1; -- đăng nhập thành công
else
        set p_result = 0; -- thất bại
end if;
end;

delimiter //
-- ===
CREATE TABLE product
(
    product_id     INT PRIMARY KEY AUTO_INCREMENT,
    product_name   VARCHAR(100)   NOT NULL UNIQUE,
    product_brand  VARCHAR(50)    NOT NULL,
    product_price  DECIMAL(12, 2) NOT NULL,
    product_stock  INT            NOT NULL,
    product_status boolean default (true)
);

-- ===
delimiter //

create procedure get_all_product()
begin
select * from product where product_status = true;
end;

delimiter //
-- ==
delimiter //
create procedure get_product_byId(in p_product_id int)
begin
select *
from product
where product_id = p_product_id
  and product_status = true;
end;
delimiter //
-- ===
delimiter //
create procedure get_product_byName(in p_product_name varchar(150))
begin
select *
from product
where product_name = p_product_name
  and product_status = true;
end;
delimiter //
-- ===
delimiter //

create procedure add_product(
    in p_product_name varchar(150),
    in p_product_brand varchar(255),
    in p_product_price decimal(12, 2),
    in p_product_stock int
)
begin
insert into product(product_name, product_brand, product_price, product_stock)
values (p_product_name, p_product_brand, p_product_price, p_product_stock);
end;

delimiter //
-- =====
delimiter //

create procedure update_product(
    in p_product_id int,
    in p_product_name varchar(150),
    in p_product_brand varchar(255),
    in p_product_price decimal(12, 2),
    in p_product_stock int
)
begin
update product
set product_name  = p_product_name,
    product_brand = p_product_brand,
    product_price = p_product_price,
    product_stock = p_product_stock
where product_id = p_product_id;
end;

delimiter //
-- ===
delimiter //
create procedure delete_product(in p_product_id int)
begin
update product
set product_status = false
where product_id = p_product_id;
end;
delimiter //
-- ===

delimiter //
create procedure search_product_byBrand(in p_product_brand varchar(255))
begin
select * from product where product_status = true and product_brand LIKE CONCAT('%', p_product_brand, '%');
end;
delimiter //
-- ===
delimiter //
create procedure search_product_byPrice(in p_min double, in p_max double)
begin
select * from product where product_status = true and product_price between p_min and p_max;
end;
delimiter //
-- ===
delimiter //
create procedure search_product_byStock(in p_min double, in p_max double)
begin
select * from product where product_status = true and product_stock between p_min and p_max;
end;
delimiter //
-- ===
CREATE TABLE customer
(
    customer_id      INT PRIMARY KEY AUTO_INCREMENT,
    customer_name    VARCHAR(100) NOT NULL,
    customer_phone   VARCHAR(20) UNIQUE ,
    customer_email   VARCHAR(100) UNIQUE,
    customer_address VARCHAR(255),
    customer_status  boolean default(true)
);
-- ===
delimiter //

create procedure get_all_customer()
begin
select * from customer where customer_status = true;
end;

delimiter //
-- ===
delimiter //
create procedure get_customer_byId(in c_customer_id int)
begin
select *
from customer
where customer_id = c_customer_id
  and customer_status = true;
end;
delimiter //
-- ===
delimiter //
create procedure get_customer_byName(in c_customer_name varchar(100))
begin
select *
from customer
where customer_name = c_customer_name
  and customer_status = true;
end;
delimiter //
-- ===
delimiter //
create procedure add_customer(in c_customer_name varchar(100), in c_customer_phone varchar(20),
                              in c_customer_email varchar(100), in c_customer_address varchar(255))
begin
insert into customer(customer_name, customer_phone, customer_email, customer_address)
values (c_customer_name, c_customer_phone, c_customer_email, c_customer_address);
end;
delimiter //
-- ===
delimiter //
create procedure update_customer(in c_customer_id int, in c_customer_name varchar(100), in c_customer_phone varchar(20),
                                 in c_customer_email varchar(100), in c_customer_address varchar(255))
begin
update customer
set customer_name    = c_customer_name,
    customer_phone   = c_customer_phone,
    customer_email   = c_customer_email,
    customer_address = c_customer_address
where customer_id = c_customer_id;
end;
delimiter //
-- ===
delimiter //
create procedure delete_customer(in c_customer_id int)
begin
update customer
set customer_status = FALSE
where customer_id = c_customer_id;
end;
delimiter //
-- ===
delimiter //
create procedure search_customer_ByName(in c_customer_name varchar(100))
begin
select * from customer where customer_status = true and customer_name = c_customer_name;
end;
delimiter //
-- ===
delimiter //
create procedure find_by_phone(in c_customer_phone varchar(20))
begin
SELECT * FROM customer WHERE customer_phone = c_customer_phone;
end;
delimiter //
-- ===
delimiter //
create procedure find_by_email(in c_customer_email varchar(100))
begin
SELECT * FROM customer WHERE customer_email = c_customer_email;
end;
delimiter //
-- ===
CREATE TABLE invoice
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    customer_id  INT,
    created_at   DATETIME DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(12, 2) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);
-- ===
CREATE TABLE invoice_details
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    invoice_id INT,
    product_id INT,
    quantity   INT            NOT NULL,
    unit_price DECIMAL(12, 2) NOT NULL,
    FOREIGN KEY (invoice_id) REFERENCES invoice (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (product_id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);
-- ===
