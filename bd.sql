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

create table jogo(
id int primary key not null auto_increment,
adm varchar(36) not null,
plataforma int not null,
genero int not null,
nome_jogo varchar(50) not null
);

create table genero(
id int primary key auto_increment not null,
nome_gen varchar(50) not null
);

create table plataforma(
id int primary key not null auto_increment,
nome_plataforma varchar(50) not null
);

create table evento(
id int primary key not null auto_increment,
nome_evt varchar(100),
data_inicio datetime,
data_fim datetime,
premiacao double,
exclusivo_arena boolean,
cod_jogo int,
cod_adm varchar(36)
);

alter table pessoa_roles
add foreign key fk_id_pessoa(id_pessoa) references administrador(id);

alter table pessoa_roles
add foreign key fk_id_role(id_role) references roles(role_id);

alter table jogo
add foreign key FK_ADM(adm) references administrador(id);

alter table jogo
add foreign key FK_plataforma(plataforma) references plataforma(id);

alter table jogo
add foreign key FK_gen(genero) references genero(id);

alter table evento
add foreign key fk_jogo(cod_jogo) references jogo(id);

alter table evento
add foreign key fk_adm(cod_adm) references administrador(id);

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
insert into genero (id, nome_gen) values (default, 'FPS'), (default, 'Battle Royale'),(default, 'Moba'),
(default, 'RPG'), (default, 'MMORPG');

insert into plataforma (id, nome_plataforma) values (default, 'Mobile'), (default, 'Console'), (default, 'Desktop');

insert into jogo (id, nome_jogo, adm, plataforma, genero) values (default,'PUBG', 'a10afdbf-75ba-45aa-bdc7-94e354773979', 3, 2),
 (default,'CS:GO', 'a10afdbf-75ba-45aa-bdc7-94e354773979', 3, 1),
(default,'Free Fire', 'c27e27a0-58c1-4614-a9cf-5bab4077b7a3', 1, 2), (default,'League of Legends', '6e95a5f4-d63e-40e5-a407-5481991247da', 3, 3),
 (default,'Call of Duty: Warzone', 'a10afdbf-75ba-45aa-bdc7-94e354773979', 2, 2), (default,'Overwatch', 'c27e27a0-58c1-4614-a9cf-5bab4077b7a3', 3,1 ),
 (default,'Fortnite','a10afdbf-75ba-45aa-bdc7-94e354773979', 1, 2), (default,'League of Legends: Wild Rift', 'a10afdbf-75ba-45aa-bdc7-94e354773979', 1, 3),
(default,'PUBG: New States', 'a10afdbf-75ba-45aa-bdc7-94e354773979', 1, 2), (default,'Rainbow Six Siege', 'a10afdbf-75ba-45aa-bdc7-94e354773979', 2, 1);

insert into evento (id, nome_evt, data_inicio, data_fim, premiacao, exclusivo_arena, cod_adm, cod_jogo) values
(default, '3º edição do Counter Strike para amadores', '2021-11-29 15:00:00', '2021-12-03 22:00:00', 1500.00, true, 'a10afdbf-75ba-45aa-bdc7-94e354773979', 2),
(default, 'PUBG para todos', '2021-11-15 18:00', '2021-11-15 23:59', 5000.00, false, 'c27e27a0-58c1-4614-a9cf-5bab4077b7a3',1),
(default, 'Tiozão também joga', '2021-12-04 13:00', '2021-12-04 23:59', 50000.00, false, '6e95a5f4-d63e-40e5-a407-5481991247da', 5),
(default, 'LOL também é jogo', '2021-12-10 23:00', '2021-12-13 01:59', 1000.00, true, 'a10afdbf-75ba-45aa-bdc7-94e354773979', 3),
(default, 'Campeonato Profissional de Overwatch', '2021-11-21 11:00', '2021-11-23 15:59', 100000.00, false, 'c27e27a0-58c1-4614-a9cf-5bab4077b7a3', 6),
(default, '1º edição do campeonato de talentos do PUBG: New States', '2021-12-10 16:00', '2021-12-15 23:59', 20000.00, false, 'a10afdbf-75ba-45aa-bdc7-94e354773979', 1);