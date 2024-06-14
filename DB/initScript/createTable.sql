CREATE TABLE user (
                          UserID INT AUTO_INCREMENT PRIMARY KEY,
                          Account VARCHAR(20) DEFAULT NULL,
                          Password VARCHAR(20) DEFAULT NULL,
                          Level INT DEFAULT '0',/*0~9 普通會員 10~員工*/
                          Phone VARCHAR(10) DEFAULT NULL
);

CREATE TABLE orders (
                        OrderID INT AUTO_INCREMENT PRIMARY KEY,
                        Dates VARCHAR(50) DEFAULT NULL,
                        Amount INT DEFAULT NULL,
                        Status INT DEFAULT 0
);


CREATE TABLE build (
                       OrderID INT NOT NULL,
                       UserID INT NOT NULL,
                       PRIMARY KEY (OrderID, UserID),
                       FOREIGN KEY (OrderID) REFERENCES orders(OrderID),
                       FOREIGN KEY (UserID) REFERENCES user(UserID)
);

CREATE TABLE product (
                         ProductID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         ProductName VARCHAR(200) DEFAULT NULL,
                         price INT DEFAULT NULL,
                         size CHAR DEFAULT NULL,
                         sugar VARCHAR(2) DEFAULT NULL,
                         ice VARCHAR(2) DEFAULT NULL,
                         type VARCHAR(30) DEFAULT NULL,
                         State INT DEFAULT 1,
                         Stack INT DEFAULT 30

);

CREATE TABLE addCart (
                         OrderID INT NOT NULL,
                         ProductID INT NOT NULL,
                         Quantity INT,
                         PRIMARY KEY (OrderID, ProductID),
                         FOREIGN KEY (OrderID) REFERENCES orders(OrderID),
                         FOREIGN KEY (ProductID) REFERENCES product(ProductID)
);

