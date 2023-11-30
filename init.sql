-- Create a new database
CREATE DATABASE IF NOT EXISTS ecommerce;

-- Use the newly created database
USE ecommerce;

CREATE TABLE IF NOT EXISTS customer (
   id            BIGINT AUTO_INCREMENT PRIMARY KEY,
   address       VARCHAR(255),
   city          VARCHAR(255),
   password      VARCHAR(255),
   phone         INT NOT NULL,
   postcode      INT NOT NULL,
   customerEmail VARCHAR(255),
   customerName  VARCHAR(255),
   firstName     VARCHAR(255),
   lastName      VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS product (
   id          BIGINT AUTO_INCREMENT PRIMARY KEY,
   name        VARCHAR(255) NOT NULL,
   description TEXT,
   price       DOUBLE NOT NULL,
   stock       INT NOT NULL,
   category    VARCHAR(255)
);
 
 
CREATE TABLE IF NOT EXISTS orders (
   id               INT AUTO_INCREMENT PRIMARY KEY,
   orderId          BIGINT,
   productId        BIGINT,
   customerId       BIGINT,
   productQuantity  INT,
   productPrice     DOUBLE,
   subTotal         DOUBLE,
   purchaseDate     TIMESTAMP,
   shippingDate     TIMESTAMP,
   isDelivered      TINYINT(1),
   FOREIGN KEY (productId) REFERENCES product(id),
   FOREIGN KEY (customerId) REFERENCES customer(id)
);
 
 


