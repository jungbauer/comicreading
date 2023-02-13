-- insert roles for the role table
INSERT INTO role (id, name) VALUES ((select nextval('role_id_seq')), 'ROLE_ADMIN');
INSERT INTO role (id, name) VALUES ((select nextval('role_id_seq')), 'ROLE_USER');
INSERT INTO role (id, name) VALUES ((select nextval('role_id_seq')), 'ROLE_DEMO');

-- insert test users
INSERT INTO user_account (id, email, first_name, last_name, password) VALUES ((select nextval('user_account_seq')), 'test@test.com', 'Test', 'Test', '$2a$10$JTG2Xk/GnfzE0967f.Yfx..3b7tuxMZTvxUrsKgetPkP5cmbnVJp.');

-- insert user_roles
INSERT INTO users_roles (user_id, role_id) VALUES ((select id from user_account where email='test@test.com'),(select id from role where name='ROLE_USER'));

-- comics --
INSERT INTO comics (id, active_link_prefix, active_link_suffix, curr_chapter, main_link, title, user_id) VALUES ((select nextval('comics_id_seq')), NULL, NULL, 1, 'https://tapas.io/series/tbate-comic/info', 'The Beginning After the End', (select id from user_account where email='test@test.com'));
INSERT INTO comics (id, active_link_prefix, active_link_suffix, curr_chapter, main_link, title, user_id) VALUES ((select nextval('comics_id_seq')),'', '', 1, 'https://www.voyce.me/series/demon-defense-agency','Demon Defense Agency ', (select id from user_account where email='test@test.com'));