create table customer (mobileNo varchar2(10) primary key,name varchar2(15),balance number(20,2));


create table transactions (mobileNo varchar2(10),transactionType varchar2(15),transactionDate date,balance number(20,2));
