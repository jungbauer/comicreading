DROP TABLE IF EXISTS comics;

CREATE TABLE comics (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    title VARCHAR(255),
    main_link VARCHAR(255),
    curr_chapter VARCHAR(255),
    active_link_prefix VARCHAR(255),
    active_link_suffix VARCHAR(255),
    PRIMARY KEY (id)
);