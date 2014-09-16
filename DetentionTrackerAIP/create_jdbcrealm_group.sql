create view jdbcrealm_group (username, groupname) as
select username, 'Users'
from logins;

