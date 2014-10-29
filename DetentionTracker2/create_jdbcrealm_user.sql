create view jdbcrealm_user (username, password) as
select username, password
from login;
