-- insert comics into the comics table
-- commented out because these inserts do not have an associated user
-- INSERT INTO comics (title, main_link, curr_chapter, active_link_prefix, active_link_suffix) VALUES ('The Beginning After the End', 'https://toonily.net/manga/the-beginning-after-the-end/', '170', 'https://toonily.net/manga/the-beginning-after-the-end/chapter-','/');
-- INSERT INTO comics (title, main_link, curr_chapter, active_link_prefix, active_link_suffix) VALUES ('Mercenary Enrollment', 'https://toonily.net/manga/mercenary-enrollment/', '113', 'https://toonily.net/manga/mercenary-enrollment/chapter-', '/');
-- INSERT INTO comics (title, main_link, curr_chapter, active_link_prefix, active_link_suffix) VALUES ('Reaper of the Drifting Moon', 'https://toonily.net/manga/reaper-of-the-drifting-moon/', '43', 'https://toonily.net/manga/reaper-of-the-drifting-moon/chapter-', '/');
-- INSERT INTO comics (title, main_link, curr_chapter, active_link_prefix, active_link_suffix) VALUES ('Swordmaster''s Youngest Son','https://toonily.net/manga/swordmasters-youngest-son/', '49', 'https://toonily.net/manga/swordmasters-youngest-son/chapter-', '/');
-- INSERT INTO comics (title, main_link, curr_chapter, active_link_prefix, active_link_suffix) VALUES ('Transmigrating to the Otherworld Once More', 'https://beta.asurascans.com/comics/114-transmigrating-to-the-otherworld-once-more', '40', 'https://beta.asurascans.com/read/114-transmigrating-to-the-otherworld-once-more/chapter-', '');
-- INSERT INTO comics (title, main_link, curr_chapter, active_link_prefix, active_link_suffix) VALUES ('The World After the Fall', 'https://toonily.net/manga/the-world-after-the-fall/', '37', 'https://toonily.net/manga/the-world-after-the-fall/chapter-','/');
-- INSERT INTO comics (title, main_link, curr_chapter, active_link_prefix, active_link_suffix) VALUES ('The Max Level Hero has Returned', 'https://toonily.net/manga/the-max-level-hero-has-returned-manhwa/', '112', 'https://toonily.net/manga/the-max-level-hero-has-returned-manhwa/chapter-', '/');
-- INSERT INTO comics (title, main_link, curr_chapter, active_link_prefix, active_link_suffix) VALUES ('Player who returned 10000 years later', 'https://asura.gg/manga/player-who-returned-10000-years-later/', '36', 'https://asura.gg/player-who-returned-10000-years-later-chapter-', '/');
-- INSERT INTO comics (title, main_link, curr_chapter, active_link_prefix, active_link_suffix) VALUES ('The Hero Returns', 'https://asura.gg/manga/the-hero-returns/', '27', 'https://asura.gg/the-hero-returns-chapter-', '/');
-- INSERT INTO comics (title, main_link, curr_chapter, active_link_prefix, active_link_suffix) VALUES ('Damn Reincarnation', 'https://toonily.net/manga/damn-reincarnation/', '45', 'https://toonily.net/manga/damn-reincarnation/chapter-', '/');
-- INSERT INTO comics (title, main_link, curr_chapter, active_link_prefix, active_link_suffix) VALUES ('I am the Fated Villain', 'https://asura.gg/manga/i-am-the-fated-villain/', '11', 'https://asura.gg/i-am-the-fated-villain-chapter-', '/');
-- INSERT INTO comics (title, main_link, curr_chapter, active_link_prefix, active_link_suffix) VALUES ('Dungeon Odyssey', 'https://asura.gg/manga/dungeon-odyssey/', '30', 'https://asura.gg/dungeon-odyssey-chapter-', '/');


-- insert roles for the role table
INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_DEMO');