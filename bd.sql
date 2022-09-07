drop database estadio_esports_spring;
create database estadio_esports_spring;
use estadio_esports_spring;

create table administrador(
id int primary key not null auto_increment,
nome varchar(40) not null,
idade int not null,
salario double(10,2) not null,
login varchar(20) not null,
senha varchar(15) not null
);

insert into administrador(nome, idade, salario, login, senha) values ("José", 25, 1299, "josé@gmail.com", "12345678"), 
("Maria", 19, 1998, "maria@gmail.com", "12345678"), ("Pedro", 34, 999, "pedro@gmail.com", "12345678");


