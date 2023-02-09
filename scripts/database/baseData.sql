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

-- insert comics
INSERT INTO comics (id, active_link_prefix, active_link_suffix, curr_chapter, main_link, title, user_id) 
VALUES ((select nextval('comics_id_seq')),
        'https://www.webtoons.com/en/fantasy/the-lazy-lord-masters-the-sword/ep-1-an-old-mans-diary/viewer?title_no=3349&episode_no=', NULL,
        1, 'https://www.webtoons.com/en/fantasy/the-lazy-lord-masters-the-sword/list?title_no=3349',
        'The Lazy Lord Masters the Sword', (select id from user_account where email='demo@demo.com')
        );

INSERT INTO comics (id, active_link_prefix, active_link_suffix, curr_chapter, main_link, title, user_id) 
VALUES ((select nextval('comics_id_seq')),
        NULL, NULL, 1, 'https://tapas.io/series/tbate-comic/info',
        'The Beginning After the End', (select id from user_account where email='demo@demo.com')
        );

INSERT INTO comics (id, active_link_prefix, active_link_suffix, curr_chapter, main_link, title, user_id) 
VALUES ((select nextval('comics_id_seq')),
        'https://www.asurascans.com/1672760368-i-am-the-fated-villain-chapter-', '/',
        30, 'https://www.asurascans.com/manga/1672760368-i-am-the-fated-villain/',
        'I Am The Fated Villain', (select id from user_account where email='test@test.com')
        );

INSERT INTO comics (id, active_link_prefix, active_link_suffix, curr_chapter, main_link, title, user_id) 
VALUES ((select nextval('comics_id_seq')),
        'https://toonily.net/manga/reaper-of-the-drifting-moon/chapter-', '/',
        48, 'https://toonily.net/manga/reaper-of-the-drifting-moon/',
        'Reaper of the Drifting Moon', (select id from user_account where email='test@test.com')
        );