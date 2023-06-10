create table armarios(
	id bigint not null auto_increment,
	tipo_armario int,
	numero_armario int,
	numero_janela int,
	codigo_armario varchar(6),
	ocupado tinyint,
	ativo tinyint,
	primary key(id)
);