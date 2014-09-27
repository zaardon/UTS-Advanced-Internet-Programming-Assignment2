CREATE TABLE detentions
(
DetentionID int,
FirstName varchar(255),
LastName varchar(255),
YearGroup int,
DetentionType varchar(255),
Department varchar(255),
Reason varchar(255),
SCHOOLNAME VARCHAR(255) REFERENCES PERMISSIONS(SCHOOLNAME)
);