CREATE TABLE user_client (
    id SERIAL PRIMARY KEY NOT NULL,
    name varchar(225) NOT NULL,
    password varchar(225) not null
);

INSERT INTO user_client (name, password) VALUES
('Alice' ,'123456'), ('Bob', 'abcdef'), ('Carol', 'qwerty');