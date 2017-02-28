CREATE TABLE PrivateBankCustomer(
  CustomerId INTEGER DEFAULT nextval('bankcustomer_customerid_seq') NOT NULL PRIMARY KEY,
  BonusProgramId INTEGER);





CREATE TABLE RetailBankCustomer(
  CustomerId INTEGER DEFAULT nextval('bankcustomer_customerid_seq') NOT NULL PRIMARY KEY,
  AnnualFees DOUBLE PRECISION);

ALTER TABLE BankCustomer ADD Type TEXT DEFAULT 'Private';
UPDATE BankCustomer SET Type='Retail' WHERE CustomerId NOT IN (3, 4);
UPDATE BankCustomer SET Type='Private' WHERE CustomerId IN (3, 4);

ALTER TABLE BankCustomer ALTER Type SET NOT NULL;
ALTER TABLE RetailBankCustomer ADD FOREIGN KEY(CustomerId) REFERENCES BankCustomer(CustomerId);
ALTER TABLE PrivateBankCustomer ADD FOREIGN KEY(CustomerId) REFERENCES BankCustomer(CustomerId);





INSERT INTO RetailBankCustomer VALUES(1, 27.7);
INSERT INTO RetailBankCustomer VALUES(2, 24.4);
INSERT INTO PrivateBankCustomer VALUES(3, 2);
INSERT INTO PrivateBankCustomer VALUES(4, 18);
INSERT INTO RetailBankCustomer VALUES(5, 30.2);
INSERT INTO RetailBankCustomer VALUES(6, 33.5);