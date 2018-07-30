# BankingAPI
Online Banking API

Customers should be able to do the following:
Create: Customers should be able to create an account with the bank, and a customer
who has an account should be able to add additional accounts. For example a typical
customer may have a current account and a savings account.
Lodgement: For the lodgement, a bank customer can specify the amount to lodge with the
credit card that will be debited.
Transfer: For the transfer, the bank customer can specify the amount to transfer and an
account to transfer to.
Withdrawal: For withdrawal, the bank customer can specify the amount to withdraw and
the card that will be credited.
Balance: The customer can request a balance on any account at any time.

In order to set up the database for this project use the following
(The message table is a "pilot/trial" table which can be ignored or deleted)
```
DROP DATABASE IF EXISTS REST1;
CREATE DATABASE REST1;
use rest1;
DROP TABLE IF EXISTS message;
CREATE TABLE Message (
	id INTEGER(4) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	message VARCHAR(15) NOT NULL,
	created DATE NOT NULL,
	author VARCHAR(10)
);
INSERT INTO message VALUES(1, 'Pilot Message','2018-06-30','Javier');
DROP TABLE IF EXISTS account;
CREATE TABLE account (
	id INTEGER(4) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	type VARCHAR(15) NOT NULL,	
	number FLOAT(10) NOT NULL,	
	balance DOUBLE PRECISION(10,2)	
);
INSERT INTO account VALUES(1, 'Pilot','123456789',500.99);
DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
	id INTEGER(4) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name VARCHAR(15) NOT NULL,	
	pin INTEGER(4) NOT NULL	
);
INSERT INTO customer VALUES(1, 'Pilot', 1234);
DROP TABLE IF EXISTS transaction;
CREATE TABLE transaction (
	id INTEGER(4) PRIMARY KEY NOT NULL AUTO_INCREMENT,	
	type VARCHAR(15) NOT NULL,	
	description VARCHAR(20) NOT NULL,	
	amount DOUBLE PRECISION(10,2) NOT NULL	
);
INSERT INTO transaction VALUES(1, 'Pilot', 'Pilot transaction', 150.75);
SHOW tables;
SELECT * FROM account;
SELECT * FROM message;
SELECT * FROM customer;
SELECT * FROM transaction;
```

In order to create, read, delete and update data in the database use the following CURL commands:
(Make sure your tables have at least one row filled each)

CUSTOMER

curl -v -X GET http://localhost:49000/api/customers/1

curl -v -X PUT http://localhost:49000/api/customers/delcus/2

curl -v -X PUT http://localhost:49000/api/customers/editcust/2/4321

curl -v -X PUT http://localhost:49000/api/customers/addNewCustomer/John/1234

ACCOUNT

curl -v -X GET http://localhost:49000/api/accounts/1

curl -v -X PUT http://localhost:49000/api/accounts/delacc/2

curl -v -X PUT http://localhost:49000/api/accounts/editaccount/2/25.50

curl -v -X PUT http://localhost:49000/api/accounts/addNewAccount/Savings

TRANSACTION

curl -v -X GET http://localhost:49000/api/transactions/1

curl -v -X PUT http://localhost:49000/api/transactions/deltran/2

curl -v -X PUT http://localhost:49000/api/transactions/addNewTransaction/Transfer/transferfunds/55.25
