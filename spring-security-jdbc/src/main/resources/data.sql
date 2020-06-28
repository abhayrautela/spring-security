insert into users (username, password, enabled) values ('user', 'user', true);
insert into users (username, password, enabled) values ('admin', 'admin', true);
-- ROLE_ is prefixed before actual role name used in SecurityConfiguration class
insert into authorities (username, authority) values ('user', 'ROLE_user');
insert into authorities (username, authority) values ('admin', 'ROLE_admin');