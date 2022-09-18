drop database if exists estadio_esports_spring;
create database estadio_esports_spring;
use estadio_esports_spring;

create table administrador(
id varchar(36) primary key not null,
nome varchar(40) not null,
sobrenome varchar(40) not null,
data_nascimento date not null,
cpf varchar(15) not null unique,
salario double(10,2) not null,
login varchar(255) not null unique,
senha varchar(255) not null
);

create table roles(
role_id int primary key not null auto_increment,
role_name varchar(30) not null
);

create table pessoa_roles(
	id_pessoa varchar(36),
    id_role int
);

alter table pessoa_roles
add foreign key fk_id_pessoa(id_pessoa) references administrador(id);

alter table pessoa_roles
add foreign key fk_id_role(id_role) references roles(role_id);

delimiter $
 create trigger tr_insert_role_adm
 after insert on administrador
 for each row
begin
	insert into pessoa_roles values (new.id, 1);
end
$

insert into roles(role_id, role_name) values (default, 'administrador'), (default, 'tecnico'), (default, 'espectador'), (default, 'jogador');

insert into administrador(id, nome, sobrenome, data_nascimento, cpf, salario, login, senha) values ("a10afdbf-75ba-45aa-bdc7-94e354773979", "José", "Carlos", "2000-10-22", "535.387.200-29", 1299, "josé@estadio-esports.com", "$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO"), 
("c27e27a0-58c1-4614-a9cf-5bab4077b7a3", "Maria", "Braga", "1999-7-12","667.387.150-29", 1998, "maria@estadio-esports.com", "$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO"),
 ("6e95a5f4-d63e-40e5-a407-5481991247da","Pedro", "Barbosa", "2004-06-09","125.187.200-29", 999, "pedro@estadio-esports.com", "$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO");

select * from pessoa_roles;
select * from administrador;
select * from roles;

