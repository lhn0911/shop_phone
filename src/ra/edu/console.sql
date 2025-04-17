CREATE DATABASE PhoneStore_DB;
USE PhoneStore_DB;
# DROP DATABASE PhoneStore_DB;
CREATE TABLE admin (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) NOT NULL UNIQUE,
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

select count(a.id) into v_count
from admin a
where username = p_username
  and password = p_password;
if v_count > 0 then
        set p_result = 1; -- đăng nhập thành công
else
        set p_result = 0; -- thất bại
end if;
end ;

delimiter //
-- ===
CREATE TABLE product (
                         product_id INT PRIMARY KEY AUTO_INCREMENT,
                         product_name VARCHAR(100) NOT NULL UNIQUE,
                         product_brand VARCHAR(50) NOT NULL,
                         product_price DECIMAL(12,2) NOT NULL,
                         product_stock INT NOT NULL
);
-- ===
delimiter //

create procedure get_all_product()
begin
select * from product;
end ;

delimiter //
-- ==
delimiter //
create procedure get_product_byId(in p_product_id int)
begin
select * from product
where product_id = p_product_id;
end ;
delimiter //
-- ===
delimiter //
create procedure get_product_byName(in p_product_name varchar(150))
begin
select * from product
where product_name = p_product_name;
end ;
delimiter //
-- ===
delimiter //

create procedure add_product(
    in p_product_name varchar(150),
    in p_product_brand varchar(255),
    in p_product_price decimal(12,2),
    in p_product_stock int
)
begin
insert into product(product_name, product_brand, product_price, product_stock)
values (p_product_name, p_product_brand, p_product_price, p_product_stock);
end ;

delimiter //
-- =====
delimiter //

create procedure update_product(
    in p_product_id int,
    in p_product_name varchar(150),
    in p_product_brand varchar(255),
    in p_product_price decimal(12,2),
    in p_product_stock int
)
begin
update product
set product_name = p_product_name,
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
delete from product where product_id = p_product_id;
end ;
delimiter //
-- ===

delimiter //
create procedure search_product_byBrand(in p_product_brand varchar(255))
begin
select * from product where product_brand LIKE CONCAT('%',p_product_brand,'%');
end ;
delimiter //
-- ===
delimiter //
create procedure search_product_byPrice(in p_min double, in p_max double)
begin
select * from product where product_price between p_min and p_max;
end ;
delimiter //
-- ===
delimiter //
create procedure search_product_byStock(in p_min double, in p_max double)
begin
select * from product where product_stock between p_min and p_max;
end ;
delimiter //
-- ===
CREATE TABLE customer (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(100) NOT NULL,
                          phone VARCHAR(20),
                          email VARCHAR(100) UNIQUE,
                          address VARCHAR(255)
);
-- ==
CREATE TABLE invoice (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         customer_id INT,
                         created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                         total_amount DECIMAL(12,2) NOT NULL,
                         FOREIGN KEY (customer_id) REFERENCES customer(id)
                             ON DELETE SET NULL
                             ON UPDATE CASCADE
);
-- ===
CREATE TABLE invoice_details (
                                 id INT PRIMARY KEY AUTO_INCREMENT,
                                 invoice_id INT,
                                 product_id INT,
                                 quantity INT NOT NULL,
                                 unit_price DECIMAL(12,2) NOT NULL,
                                 FOREIGN KEY (invoice_id) REFERENCES invoice(id)
                                     ON DELETE CASCADE
                                     ON UPDATE CASCADE,
                                 FOREIGN KEY (product_id) REFERENCES product(product_id)
                                     ON DELETE SET NULL
                                     ON UPDATE CASCADE
);
-- ===