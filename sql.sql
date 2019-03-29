CREATE DATABASE IF NOT EXISTS prova;
USE prova;
CREATE TABLE IF NOT EXISTS vendas(
id int not null primary key auto_increment,
codigoProduto int not null,
nomeProduto varchar(45) not null,
valor float not null,
quantidade int(3) not null,
nomeCliente varchar(45) not null
);

select * from vendas