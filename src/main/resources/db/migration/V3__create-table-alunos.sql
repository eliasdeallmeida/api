create table alunos(
	id bigint not null auto_increment,
	nome varchar(50), 
	email varchar(50),
	matricula varchar(20),
	codigoArmario int,
	primary key(id)
);
