drop database estadio_esports_spring;
create database estadio_esports_spring;
use estadio_esports_spring;

create table administrador(
id int primary key not null auto_increment,
nome varchar(40) not null,
idade int not null,
cpf varchar(15) not null,
salario double(10,2) not null,
login varchar(20) not null,
senha varchar(255) not null
);

insert into administrador(nome, idade, cpf, salario, login, senha) values ("José", 25, "535.387.200-29", 1299, "josé@gmail.com", "$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO"), 
("Maria", 19,"667.387.150-29", 1998, "maria@gmail.com", "$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO"),
 ("Pedro", 34,"125.187.200-29", 999, "pedro@gmail.com", "$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO");

select * from administrador;
