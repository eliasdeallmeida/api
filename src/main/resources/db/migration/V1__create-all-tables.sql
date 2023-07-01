create table admins(
	id bigint not null auto_increment,
	nome varchar(50) not null,
	email varchar(50),
	senha varchar(255) not null,
	ativo tinyint not null,
	primary key(id)
);

create table alunos(
	id bigint not null auto_increment,
	nome varchar(50) not null, 
	email varchar(50) not null,
	matricula varchar(20) not null,
	ativo tinyint not null,
	primary key(id)
);

create table armarios(
	id bigint not null auto_increment,
	tipo_armario int not null,
	numero_armario int not null,
	ativo tinyint not null,
	primary key(id)
);

create table janelas(
	id bigint not null auto_increment,
	numero_janela int not null,
	codigo_janela varchar(6) not null,
	ocupado tinyint not null,
	ativo tinyint not null,
	primary key(id)
);

create table usuarios(
    id bigint not null auto_increment,
    login varchar(50) not null,
    senha varchar(255) not null,
    primary key(id)
);

insert into usuarios(login, senha) values("admin", "$2a$12$JAF8WBZwIbvbrLnpsRnwX.xq/PI1tn6cM9CBgV7cWMRh413muoiUW");