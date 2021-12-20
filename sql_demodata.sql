INSERT INTO users(firstname,lastname,email,password,balance) VALUES('Achille','Deribreux','a@d.be','$2a$10$3LrETGPo8Dj8zmkTDnQ1c.xz2ArZGdZYwYKVEeyyq02/AAbkR7jSK',746.25);
INSERT INTO users(firstname,lastname,email,password,balance) VALUES('Jules','Deribreux','j@d.be','$2a$10$.G0x88R6mhV6qJm1TfmDIeHq7xhnZuzV8QVmFOQ1aAmvSRnLPNMLa',899.50);
INSERT INTO users(firstname,lastname,email,password,balance) VALUES('John','Doe','john@doe.com','$2a$10$oesDccGI9Cu71DoGngOctOs2AboVPBEuFoGkcsSMSQzhsilZcLk2y',597.50);



INSERT INTO transactions(senderid,receiverid,amount,datestamp,description) VALUES(0,1,1000.00,'2021-12-20 09:54:09',"Money added from my account N° 123");
insert into transactions(senderid,receiverid,amount,datestamp,description) values(0,2,1000.00,'2021-12-20 09:55:07' ,"Money added from my account N° 123456");
insert into transactions(senderid,receiverid,amount,datestamp,description) values(0,3,1000.00,'2021-12-20 10:17:38',"Money added from my account N° 12345");

insert into transactions(senderid,receiverid,amount,datestamp,description) values(3,0,250.0,'2021-12-20 10:19:38',"Taxes for transaction of 500.0€ ");
insert into transactions(senderid,receiverid,amount,datestamp,description) values(3,1,500.0,'2021-12-20 10:18:38',"Joyeux noel Achille !");

insert into transactions(senderid,receiverid,amount,datestamp,description) values(2,0,0.5,'2021-12-20 10:20:38',"Taxes for transaction of 100.0€ ");
insert into transactions(senderid,receiverid,amount,datestamp,description) values(2,3,100.0,'2021-12-20 10:19:38',"Joyeux Noel John !");

insert into transactions(senderid,receiverid,amount,datestamp,description) values(1,0,3.75,'2021-12-20 10:20:38',"Taxes for transaction of 750.0€");
insert into transactions(senderid,receiverid,amount,datestamp,description) values(1,0,750.0,'2021-12-20 10:19:38'," Money send to my account N° 123");


insert into connections(userid,friendid) values(1,3);
insert into connections(userid,friendid) values(3,1);
insert into connections(userid,friendid) values(3,2);
insert into connections(userid,friendid) values(2,3);

insert into bankaccount(userid,accountnumber,bank) values(1,123,'CBC');
insert into bankaccount(userid,accountnumber,bank) values(2,123456,'ING');
insert into bankaccount(userid,accountnumber,bank) values(3,12345,'BELFIUS');