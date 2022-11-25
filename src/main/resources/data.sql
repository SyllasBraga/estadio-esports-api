drop database if exists estadio_esports_spring;
create database estadio_esports_spring;
use estadio_esports_spring;

create table administrador(
	id varchar(36) primary key not null,
	nome varchar(40) not null,
	sobrenome varchar(40) not null,
	data_nascimento date not null,
	cpf varchar(15) not null unique,
	salario double(10, 2) not null,
	login varchar(255) not null unique,
	senha varchar(255) not null
);

create table roles(
	role_id int primary key not null auto_increment,
	role_name varchar(30) not null
);

create table pessoa_roles(
	id_adm varchar(36),
	id_espectador varchar(36),
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

create table espectador(
	id varchar(36) primary key not null,
	nome varchar(40) not null,
	sobrenome varchar(40) not null,
	data_nascimento date not null,
	cpf varchar(15) not null unique,
	login varchar(255) not null unique,
	senha varchar(255) not null
);

create table ingresso(
	id int primary key not null auto_increment,
	valor double not null,
	estoque int not null,
	validade date not null,
	evento int not null,
    valor_desconto double(10,2)
);

create table carrinho(
	id int primary key not null auto_increment,
	data_abertura date not null,
	espectador varchar(36) not null
);

create table itens_carrinho(
	ingresso int not null,
	carrinho int not null,
	quantidade int not null,
	valor_total double(10, 2)
);

alter table
	itens_carrinho
add
	foreign key fk_ingresso(ingresso) references ingresso(id);

alter table
	itens_carrinho
add
	foreign key fk_carrinho(carrinho) references carrinho(id);

alter table
	carrinho
add
	foreign key fk_espectador(espectador) references espectador(id);

alter table
	ingresso
add
	foreign key fk_id_evt(evento) references ingresso(id);

alter table
	pessoa_roles
add
	foreign key fk_id_adm(id_adm) references administrador(id);

alter table
	pessoa_roles
add
	foreign key fk_id_role(id_role) references roles(role_id);

alter table
	jogo
add
	foreign key FK_ADM(adm) references administrador(id);

alter table
	jogo
add
	foreign key FK_plataforma(plataforma) references plataforma(id);

alter table
	jogo
add
	foreign key FK_gen(genero) references genero(id);

alter table
	evento
add
	foreign key fk_jogo(cod_jogo) references jogo(id);

alter table
	evento
add
	foreign key fk_adm(cod_adm) references administrador(id);

alter table
	pessoa_roles
add
	foreign key fk_id_espectador(id_espectador) references espectador(id);

#View para mostrar todos os carrinhos montados excluindo os ingressos já expirados
create view vw_carrinho as
select evt.nome_evt as "Evento", ic.quantidade as "Quantidade", ic.valor_total as "Valor total", car.data_abertura as "Data de abertura", espec.nome as "Responsável"
from evento evt 
inner join ingresso ing on evt.id = ing.evento
inner join itens_carrinho ic on ing.id = ic.ingresso
inner join carrinho car on ic.carrinho = car.id
inner join espectador espec on car.espectador = espec.id
where ing.validade >= now();

#View para mostrar todos os eventos cadastrados mostrando o nome do jogo e responsável pelo cadastrado     
create view vw_evts as
select evt.id as "Id", evt.nome_evt as "Nome do evento",
evt.data_inicio as "Início",
evt.data_fim as "Fim",
evt.premiacao as "Premiação",
evt.exclusivo_arena as "Exclusivo",
jg.nome_jogo as "Jogo disputado",
adm.nome as "Cadastrado por" from evento evt
inner join jogo jg on evt.cod_jogo = jg.id
inner join administrador adm on evt.cod_adm = adm.id;

#View para mostrar todos os jogos cadastrados mostrando o nome do gênero, plataforma e responsável pelo cadastrado 
create view vw_jogos as
select jg.id as "Id", jg.nome_jogo as
"Nome do jogo",
gen.nome_gen as "Gênero",
plat.nome_plataforma as "Plataforma",
adm.nome as "Cadastrado por" from
jogo jg
inner join genero gen on jg.genero =
gen.id
inner join plataforma plat on
jg.plataforma = plat.id
inner join administrador adm on
jg.adm = adm.id;

#View para mostrar todos as pessoas cadastradas e as suas regras
create view vw_pessoas as
select adm.nome, rl.role_name
from pessoa_roles pr
inner join administrador adm on pr.id_adm = adm.id
inner join roles rl on pr.id_role = rl.role_id;

#Trigger para atribuir uma regra de administrador após um cadastro na tabela de administradores
delimiter $ 
create trigger tr_insert_role_adm
after insert on administrador for each row begin
insert into
	pessoa_roles(id_adm, id_role)
values
	(new.id, 1);
end $ 

delimiter $ 
create trigger tr_insert_role_espec
after insert
	on espectador for each row begin
insert into
	pessoa_roles(id_espectador, id_role)
values
	(new.id, 3);
end $ 

#Trigger para calcular o valor total de uma lista de compras
delimiter $ 
create trigger tr_insert_itens_carrinho 
before insert
	on itens_carrinho for each row begin
set @valor_ingresso = (
		select ing.valor from ingresso ing where id = new.ingresso
	);
set
	new.valor_total = new.quantidade * @valor_ingresso;
end $ 

#Trigger para diminuir a quantidade de ingressos disponíveis após um novo cadastro em um carrinho
delimiter $ 
create trigger tr_atualiza_estoque before
insert on itens_carrinho for each row begin
update ingresso ing set ing.estoque = estoque - new.quantidade
where
	ing.id = new.ingresso;
end $

#Trigger para excluir o tipo de acesso ao sistema antes de excluir um adm
delimiter $
create trigger tr_exclui_pessoa_roles before
delete on administrador
for each row 
begin
	delete from pessoa_roles
where
	id_adm = old.id;
end $

#Procedure para cadastrar um novo ingresso adicionando um valor com desconto 
DELIMITER $ 
create procedure pr_cad_ingresso(pr_valor double(10,2), pr_evento varchar(36), pr_estoque int, pr_validade date) BEGIN
insert into
	ingresso(valor, evento, estoque, validade, valor_desconto)
values (pr_valor, pr_evento, pr_estoque, pr_validade, pr_valor * 0.9);
END $

#Procedure para casdastrar administradores
DELIMITER $ 
create procedure pr_cad_adm(
	pr_id varchar(36),
	pr_nome varchar(40),
	pr_sobrenome varchar(40),
	pr_data_nascimento date,
	pr_cpf varchar(15),
	pr_salario double(10, 2),
	pr_login varchar(255),
	pr_senha varchar(255)
) BEGIN
insert into
	administrador(id, nome, sobrenome, data_nascimento, cpf, salario, login, senha)
values
	(pr_id, pr_nome, pr_sobrenome, pr_data_nascimento, pr_cpf, pr_salario,
	pr_login, pr_senha);
END $ 

#Procedure para deletar um administrador
DELIMITER $ 
create procedure pr_del_adm(pr_id varchar(36))
BEGIN
delete from
	administrador
where
	id = pr_id;
END $

#Procedure para atualizar um administrador
DELIMITER $
create procedure pr_update_adm(
	pr_id varchar(36),
	pr_nome varchar(40),
	pr_sobrenome varchar(40),
	pr_data_nascimento date,
	pr_cpf varchar(15),
	pr_salario double(10, 2),
	pr_login varchar(255),
	pr_senha varchar(255))
BEGIN
update
	administrador
set
	nome = pr_nome, 
	sobrenome = pr_sobrenome, 
	data_nascimento = pr_data_nascimento,
	cpf = pr_cpf, 
	salario =  pr_salario,
	login = pr_login,
	senha = pr_senha
where
	id = pr_id;
END $ 

insert into
	roles(role_id, role_name)
values
	(default, 'administrador'),
	(default, 'tecnico'),
	(default, 'espectador'),
	(default, 'jogador');

insert into
	administrador(
		id,
		nome,
		sobrenome,
		data_nascimento,
		cpf,
		salario,
		login,
		senha
	)
values
	(
		"a10afdbf-75ba-45aa-bdc7-94e354773979",
		"José",
		"Carlos",
		"2000-10-22",
		"535.387.200-29",
		1299,
		"pedro@estadio-esports.com",
		"$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO"
	),
	(
		"c27e27a0-58c1-4614-a9cf-5bab4077b7a3",
		"Maria",
		"Braga",
		"1999-7-12",
		"667.387.150-29",
		1998,
		"maria@estadio-esports.com",
		"$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO"
	),
	(
		"39b5bdd7-0e9b-4149-8d41-2c0d9d50c1bb",
		"João",
		"Barbosa",
		"2004-06-09",
		"466.167.180-50",
		1999,
		"joao@estadio-esports.com",
		"$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO"
	),
	(
		"33e32278-eb27-46c9-bde1-1ad1eca57932",
		"Syllas",
		"Braga",
		"2004-06-09",
		"604.936.240-82",
		9949,
		"syllas",
		"$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO"
	),
	(
		"90ab9ff8-7da7-4345-ad78-9fd4d7041c87",
		"Josefina",
		"Barbosa",
		"2004-06-09",
		"895.416.230-42",
		999,
		"josefina@estadio-esports.com",
		"$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO"
	);

insert into
	genero (id, nome_gen)
values
	(default, 'FPS'),
	(default, 'Battle Royale'),
(default, 'Moba'),
	(default, 'RPG'),
	(default, 'MMORPG');

insert into
	plataforma (id, nome_plataforma)
values
	(default, 'Mobile'),
	(default, 'Console'),
	(default, 'Desktop');

insert into
	jogo (id, nome_jogo, adm, plataforma, genero)
values
	(
		default,
		'PUBG',
		'a10afdbf-75ba-45aa-bdc7-94e354773979',
		3,
		2
	),
	(
		default,
		'CS:GO',
		'a10afdbf-75ba-45aa-bdc7-94e354773979',
		3,
		1
	),
	(
		default,
		'Free Fire',
		'c27e27a0-58c1-4614-a9cf-5bab4077b7a3',
		1,
		2
	),
	(
		default,
		'League of Legends',
		'39b5bdd7-0e9b-4149-8d41-2c0d9d50c1bb',
		3,
		3
	),
	(
		default,
		'Call of Duty: Warzone',
		'a10afdbf-75ba-45aa-bdc7-94e354773979',
		2,
		2
	),
	(
		default,
		'Overwatch',
		'c27e27a0-58c1-4614-a9cf-5bab4077b7a3',
		3,
		1
	),
	(
		default,
		'Fortnite',
		'a10afdbf-75ba-45aa-bdc7-94e354773979',
		1,
		2
	),
	(
		default,
		'League of Legends: Wild Rift',
		'a10afdbf-75ba-45aa-bdc7-94e354773979',
		1,
		3
	),
	(
		default,
		'PUBG: New States',
		'a10afdbf-75ba-45aa-bdc7-94e354773979',
		1,
		2
	),
	(
		default,
		'Rainbow Six Siege',
		'a10afdbf-75ba-45aa-bdc7-94e354773979',
		2,
		1
	);

insert into
	evento (
		id,
		nome_evt,
		data_inicio,
		data_fim,
		premiacao,
		exclusivo_arena,
		cod_adm,
		cod_jogo
	)
values
	(
		default,
		'3º edição do Counter Strike para amadores',
		'2021-11-29 15:00:00',
		'2021-12-03 22:00:00',
		1500.00,
		true,
		'a10afdbf-75ba-45aa-bdc7-94e354773979',
		2
	),
	(
		default,
		'PUBG para todos',
		'2021-11-15 18:00',
		'2021-11-15 23:59',
		5000.00,
		false,
		'c27e27a0-58c1-4614-a9cf-5bab4077b7a3',
		1
	),
	(
		default,
		'Tiozão também joga',
		'2021-12-04 13:00',
		'2021-12-04 23:59',
		50000.00,
		false,
		'a10afdbf-75ba-45aa-bdc7-94e354773979',
		5
	),
	(
		default,
		'LOL também é jogo',
		'2021-12-10 23:00',
		'2021-12-13 01:59',
		1000.00,
		true,
		'a10afdbf-75ba-45aa-bdc7-94e354773979',
		3
	),
	(
		default,
		'Campeonato Profissional de Overwatch',
		'2021-11-21 11:00',
		'2021-11-23 15:59',
		100000.00,
		false,
		'c27e27a0-58c1-4614-a9cf-5bab4077b7a3',
		6
	),
	(
		default,
		'1º edição do campeonato de talentos do PUBG: New States',
		'2021-12-10 16:00',
		'2021-12-15 23:59',
		20000.00,
		false,
		'a10afdbf-75ba-45aa-bdc7-94e354773979',
		1
	);

insert into
	espectador(
		id,
		cpf,
		data_nascimento,
		nome,
		sobrenome,
		login,
		senha
	)
values
	(
		'32f0b7d2-21ef-4226-b0ac-3343eb18be8b',
		'497.970.700-93',
		'2004-01-03',
		'Abraão',
		'Lancaster',
		'abraão.lancaster@gmail.com',
		'$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO'
	),
	(
		"69f03451-5571-4e01-bf4b-86d3c3d7e579",
		'797.936.570-49',
		"1990-09-10",
		'Josefino',
		'Finíssimo',
		"jose",
		"$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO"
	),
	(
		"5cfc95a2-4cb0-4aeb-bd06-be7d5d0c2c84",
		'349.347.630-23',
		"1998-07-07",
		'Gustavo',
		'de Queiroz',
		"gustavo.queiroz@gmail.com",
		"$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO"
	),
	(
		"15a0082f-e39a-4c96-ac32-259959eb7add",
		'353.062.230-36',
		"2000-09-01",
		'Lucas',
		'Andrade',
		"andrade.lucas@gmail.com",
		"$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO"
	),
	(
		"98449261-4833-41db-bdfb-1d0b83900f87",
		'722.472.590-06',
		"2001-02-02",
		'Alfredo',
		'Henrique',
		"alfredo.henrique@gmail.com",
		"$2a$12$U8u4eEJ4nM4I5FtTZgonbuyZ4tYnhhYnjrLLbPR33uxnV5EQ9jqmO"
	);

insert into
	ingresso (id, valor, evento, estoque, validade)
values
	(default, 10, 1, 500, "2022-12-02"),
(default, 20.99, 2, 100, "2023-01-10"),
	(default, 5.99, 3, 200, "2022-11-30"),
	(default, 45.99, 4, 600, "2023-12-10"),
	(default, 11.99, 5, 50, "2022-12-31"),
	(default, 14.99, 6, 60, "2022-01-01");

insert into
	carrinho(data_abertura, espectador)
values
	(
		"2023-01-01",
		"5cfc95a2-4cb0-4aeb-bd06-be7d5d0c2c84"
	),
	(
		"2022-12-02",
		"98449261-4833-41db-bdfb-1d0b83900f87"
	),
	(
		"2023-01-10",
		"15a0082f-e39a-4c96-ac32-259959eb7add"
	),
	(
		"2023-12-10",
		'32f0b7d2-21ef-4226-b0ac-3343eb18be8b'
	),
	(
		"2022-11-30",
		"5cfc95a2-4cb0-4aeb-bd06-be7d5d0c2c84"
	);

insert into
	itens_carrinho(ingresso, carrinho, quantidade)
values
	(1, 1, 3),
	(2, 5, 2),
	(3, 2, 4),
	(4, 4, 6),
	(5, 3, 4),
	(6, 2, 7),
	(1, 5, 8),
	(2, 4, 3),
	(1, 5, 4),
	(4, 4, 5),
	(5, 1, 1);
    