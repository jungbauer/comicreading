-- insert roles for the role table
INSERT INTO role (id, name) VALUES ((select nextval('role_id_seq')), 'ROLE_ADMIN');
INSERT INTO role (id, name) VALUES ((select nextval('role_id_seq')), 'ROLE_USER');
INSERT INTO role (id, name) VALUES ((select nextval('role_id_seq')), 'ROLE_DEMO');

-- insert test users
INSERT INTO user_account (id, email, first_name, last_name, password) VALUES ((select nextval('user_account_seq')), 'test@test.com', 'Test', 'Test', '$2a$10$JTG2Xk/GnfzE0967f.Yfx..3b7tuxMZTvxUrsKgetPkP5cmbnVJp.');
INSERT INTO user_account (id, email, first_name, last_name, password) VALUES ((select nextval('user_account_seq')), 'demo@demo.com', 'demo', 'demo', '$2a$10$XjCCv.vMfgQUWcp35ZIbCeMn.LW20lsuYQB0Xk1pMPty0HIQWltu6');
INSERT INTO user_account (id, email, first_name, last_name, password) VALUES ((select nextval('user_account_seq')), 'admin@admin.com', 'admin', 'admin', '$2a$10$YIdztY.Aex1fBlnK0Q01/.pIvGklRhjvybj1kP1yocWEypygduWGa');

-- insert user_roles
INSERT INTO users_roles (user_id, role_id) VALUES ((select id from user_account where email='test@test.com'),(select id from role where name='ROLE_USER'));
INSERT INTO users_roles (user_id, role_id) VALUES ((select id from user_account where email='demo@demo.com'),(select id from role where name='ROLE_USER'));
INSERT INTO users_roles (user_id, role_id) VALUES ((select id from user_account where email='demo@demo.com'),(select id from role where name='ROLE_DEMO'));
INSERT INTO users_roles (user_id, role_id) VALUES ((select id from user_account where email='admin@admin.com'),(select id from role where name='ROLE_USER'));
INSERT INTO users_roles (user_id, role_id) VALUES ((select id from user_account where email='admin@admin.com'),(select id from role where name='ROLE_ADMIN'));