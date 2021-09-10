CREATE TABLE task (
	id bigint primary key auto_increment,
	title VARCHAR(200),
	description VARCHAR(200),
	points int
);

CREATE TABLE user (
	id bigint primary key auto_increment,
	name VARCHAR(200),
	email VARCHAR(200),
	password VARCHAR(200)
);

INSERT INTO task(title, description, points) VALUES ('banco de dados', 'teste teste', 100);
INSERT INTO task(title, description, points) VALUES ('prototipo', 'teste teste', 200);
INSERT INTO task(title, description, points) VALUES ('analise de dados', 'teste teste', 100);
INSERT INTO task(title, description, points) VALUES ('teste', 'teste teste', 100);

INSERT INTO user(name, email, password) VALUES ('Daniel', 'daniel@fiap.com', 'daniel12');
INSERT INTO user(name, email, password) VALUES ('Alex', 'alex@fiap.com', 'alex1234');
INSERT INTO user(name, email, password) VALUES ('Denys', 'denys@fiap.com', 'denys123');
INSERT INTO user(name, email, password) VALUES ('Edson', 'edson@fiap.com', 'edson123');
INSERT INTO user(name, email, password) VALUES ('Ruan', 'ruan@fiap.com', 'ruan1234');