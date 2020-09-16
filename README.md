# Minhas Finanças - API - By Cesário Pereira Neto

Consiste em uma API REST de controle de finanças de muitos usuários,
onde é possivel cadastrar, listar, atualizar e deletar movimentações financeiras e obter o saldo disponível com base nas transações cadastradas.
Esta API foi desenvolvida durante o curso ["Spring Boot + React JS: Desenvolva Aplicações Web Completas"](https://www.udemy.com/share/102a8mCUYYdFpVRXg=/) do professor [Dougllas Sousa](https://www.linkedin.com/in/dougllassousa/).

# Tecnologias Utilizadas

- Feito em [Java](https://www.oracle.com/java), na versão 1.8.
- Inicializada com [Spring Boot 2.3.3](https://spring.io/projects/spring-boot)
- Mapeamento relacional feito com Spring Data e Jakarta JPA.
- Persistência de dados feito com o SGBD [PostgresSQL](https://www.postgresql.org).
- Escrita do código repetitivo feito com o auxílio do [Lombok](https://www.projectlombok.org).
- Testes de requisição com o [Postman](https://www.postman.com/).
- Testes de unidade com o [JUnit 5](https://junit.org/junit5/).
# Utilizando a API

- **!IMPORTANTE!** Clone o repositório [Minhas Financas - REACT APP - By Cesário Pereira Neto](https://github.com/cesarionto/minhas-financas-app/) pois este trata-se do front-end da API.
- Instale o [JDK 1.8](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)
- Instale o [Eclipse IDE](https://www.eclipse.org/downloads/) e importe o projeto maven.
- Instale o [PostgresSQL](https://www.postgresql.org) de forma que o usuário e senha sejam postgres(ambus) e adicione o caminho (C:\Program Files\PostgreSQL\[versão]\bin) ao path.
- Crie uma base de dados: 
  ```cmd
  psql -U postgres;
  ```
  
  ```cmd
  create database minhasfinancasapi;
  ```
  
  ```cmd
  \c minhasfinancasapi;
  ```
  
  ```cmd
  create schema financas;
  ```
  
PS: O Springdata fará o mapeamento das tabelas.
- Execute o projeto java.
- Agora, Siga as instruções do [README.md](https://github.com/cesarionto/minhas-financas-app/blob/master/README.md) da aplicação REACT.

