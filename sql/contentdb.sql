CREATE DATABASE contentdb;

\c contentdb;


CREATE TABLE content_type (
    id SERIAL PRIMARY KEY,
    type_desc VARCHAR
);

CREATE TABLE content_source (
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    path VARCHAR,
    content_type_id INT,
    FOREIGN KEY (content_type_id) REFERENCES content_type(id)
);

INSERT INTO content_type (type_desc) VALUES
    ('Movies'),
    ('Series'),
    ('Music');

SELECT setval('content_type_id_seq', (SELECT MAX(id) FROM content_type));

INSERT INTO content_source (name, path, content_type_id)
VALUES
    ('Movies', 'd:\movies\', (SELECT id FROM content_type WHERE type_desc = 'Movies')),
    ('Animation', 'd:\animation\', (SELECT id FROM content_type WHERE type_desc = 'Movies')),
    ('Series', 'd:\series\', (SELECT id FROM content_type WHERE type_desc = 'Series')),
    ('Music', 'd:\music\', (SELECT id FROM content_type WHERE type_desc = 'Music'));