CREATE TABLE PERMISSIONS
(
SCHOOLNAME varchar(255)not null primary key,
Userpriveliege integer DEFAULT 1
);

CREATE TABLE logins
(
Username varchar(255),
Password varchar(255),
SCHOOLNAME VARCHAR(255) REFERENCES PERMISSIONS(SCHOOLNAME)
);


