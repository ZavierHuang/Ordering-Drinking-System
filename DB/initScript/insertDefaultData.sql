INSERT INTO user(Account,Password,Level,Phone) VALUES ( '111','111',0,'0930333333');
INSERT INTO user(Account,Password,Level,Phone) VALUES ( '222','222',10,'0930333339');
INSERT INTO user(Account,Password,Level,Phone) VALUES ( '333','333',99,'0930333337');
INSERT INTO orders(OrderId,Dates,Amount,Status) VALUES (1,"2024-06-12 14:22:58",210,0);
INSERT INTO orders(OrderId,Status) VALUES (2,1);
INSERT INTO build(OrderId,UserID) VALUES (1,1);
INSERT INTO build(OrderId,UserID) VALUES (2,1);
INSERT INTO addcart(OrderId,ProductId,Quantity) VALUES (1,1,1),(1,25,1),(1,49,1);
INSERT INTO addcart(OrderId,ProductId,Quantity) VALUES (2,3,2),(2,80,1),(2,102,1);
