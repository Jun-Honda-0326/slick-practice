
-- !Ups
CREATE TABLE todo (
    id         BIGINT(20)    NOT NULL AUTO_INCREMENT,
    todo       VARCHAR(120)  NOT NULL,
    created_at TIMESTAMP(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (id)
);

-- sample data
INSERT INTO todo(id, todo) VALUES
(1, 'tweet1'),
(2, 'tweet2'),
(3, 'tweet3'),
(4, 'tweet4'),
(5, 'tweet5');

-- !Downs
DROP TABLE todo;
