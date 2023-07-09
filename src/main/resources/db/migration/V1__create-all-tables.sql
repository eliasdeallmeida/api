create table usuarios(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) unique,
    senha varchar(255) not null,
    ativo tinyint not null,
    primary key(id)
);

create table alunos(
    id bigint not null auto_increment,
    nome varchar(100) not null, 
    email varchar(100) not null,
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

create table portas(
    id bigint not null auto_increment,
    numero_porta int not null,
    chave_porta varchar(6) not null,
    armario_id bigint not null,
    aluno_id bigint unique,
    ocupado tinyint not null,
    ativo tinyint not null,
    primary key(id),
    foreign key(armario_id) references armarios(id),
    foreign key(aluno_id) references alunos(id)
);

insert into usuarios(nome, senha, ativo) values("admin", "$2a$12$JAF8WBZwIbvbrLnpsRnwX.xq/PI1tn6cM9CBgV7cWMRh413muoiUW", 1);
