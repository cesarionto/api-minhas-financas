CREATE TABLE financas.usuario(
	id bigserial NOT NULL PRIMARY KEY,
	nome VARCHAR(150),
	email VARCHAR (100),
	senha VARCHAR (20),
	data_cadastro date DEFAULT NOW()
);
CREATE TABLE financas.lancamento(
	id bigserial NOT NULL PRIMARY KEY,
	descricao VARCHAR (100) NOT NULL,
	mes integer NOT NULL,
	ano integer NOT NULL,
	valor numeric(16,2) NOT NULL,
	tipo VARCHAR (20) CHECK (tipo in('RECEITA', 'DESPESA')) NOT NULL,
	status VARCHAR (20) CHECK	(tipo in('PENDENTE','CANCELADO', 'EFETIVADO')) NOT NULL,
	id_usuario bigint REFERENCES financas.usuario (id) NOT NULL,
	data_cadastro date DEFAULT NOW()
	)