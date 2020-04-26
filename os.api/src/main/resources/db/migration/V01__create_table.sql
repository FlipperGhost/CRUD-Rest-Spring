

Create table cliente (

id bigint not null auto_increment,
name varchar(150) not null,
email varchar(255),
fone varchar(20),
primary key (id)

);



create table ordem_servico (
id bigint not null auto_increment,
cliente_id bigint not null,
descricao varchar(150),
preco decimal(10,2) not null,
status varchar(20) not null,
data_abertura datetime not null,
data_finalizacao datetime,
primary key(id)
);

Alter table ordem_servico add constraint fk_ordem_servico_cliente 
foreign key (cliente_id) references cliente(id);