DROP DATABASE IF EXISTS GuessNumber;

CREATE DATABASE GuessNumber;
USE GuessNumber;

CREATE TABLE players(
	id int auto_increment primary key,
    name varchar(255) not null,
    guesses int not null
);

INSERT INTO players(name, guesses) VALUES ('Nguyen Van a', 10);
INSERT INTO players(name, guesses) VALUES ('Nguyen Van b', 26);
INSERT INTO players(name, guesses) VALUES ('Nguyen Van c', 5);
INSERT INTO players(name, guesses) VALUES ('Nguyen Van d', 6);
INSERT INTO players(name, guesses) VALUES ('Nguyen Van e', 9);
INSERT INTO players(name, guesses) VALUES ('Nguyen Van f', 12);
INSERT INTO players(name, guesses) VALUES ('Nguyen Van g', 16);
INSERT INTO players(name, guesses) VALUES ('Nguyen Van h', 18);


SELECT * FROM players ORDER BY guesses;

