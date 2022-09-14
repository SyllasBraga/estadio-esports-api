drop database if exists estadio_esports_spring;
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

create table roles(
role_id int primary key not null auto_increment,
role_name varchar(30) not null
);

create table pessoa_roles(
	id_pessoa int,
    id_role int
);

alter table pessoa_roles
add foreign key fk_id_pessoa(id_pessoa) references administrador(id);

alter table pessoa_roles
add foreign key fk_id_role(id_role) references roles(role_id);

insert into roles(role_id, role_name) values (default, 'administrador'), (default, 'tecnico'), (default, 'espectador'), (default, 'jogador');

insert into administrador(nome, idade, cpf, salario, login, senha) values ("José", 25, "535.387.200-29", 1299, "josé@gmail.com", "$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO"), 
("Maria", 19,"667.387.150-29", 1998, "maria@gmail.com", "$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO"),
 ("Pedro", 34,"125.187.200-29", 999, "pedro@gmail.com", "$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO");

insert into pessoa_roles values (1,1), (2,2), (3,1);

select * from pessoa_roles;
select * from administrador;
select * from roles;