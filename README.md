# :woman_technologist: Alura Tech :woman_technologist:

## Sobre o projeto :scroll:
Aplicação back-end desenvolvida para atender aos requisitos estabelecidos pelo desafio proposto pela Alura.A aplicação oferece funcionalidades essenciais, incluindo a criação,
busca e manipulação de usuários e cursos, criação de matrículas nos cursos, juntamente com recursos avançados como filtragem por username, inativação de cursos e geração de relatório NPS (Net Promoter Score). 

## Tecnologias utilizadas :hammer_and_wrench:

- Java (v21)
- Spring Boot (v3.2.3)
- Spring Boot Starter Data JPA (v3.2.3): Utilizado para persistência de dados com JPA.
- Spring Boot Starter Web (v3.2.3): Usado para criar aplicativos da web Spring Boot.
- Spring Boot Starter Validation (v3.2.3): Utilizado para validação de dados.
- Spring Boot DevTools (v3.2.3): Ferramenta de desenvolvimento para reinicialização automática do aplicativo em caso de alterações no código.
- MySQL (v8.0.31): Utilizado para o banco de dados.
- Flyway Core e Flyway MySQL (v8.5.0): Utilizados para controle de migração de banco de dados.
- Project Lombok: Biblioteca para reduzir a quantidade de código boilerplate, como getters/setters e construtores.
- JasperReports (v6.21.2): Utilizado para geração de relatórios.

## Executando projeto :hourglass:

#### Pré-requisitos :desktop_computer:
- Docker
- API Client (ex: Postamn,Insomnia)


```bash
# Clone o projeto 
git clone https://github.com/BrendaStefany/aluraTech.git

# Acesse o diretório do projeto
cd aluraTech

# Execute o docker
docker-compose up -d

```

A API ficará disponível através de http://localhost:8080 ou http://127.0.0.1:8080
**Obs: Se a aplicação for inicializada em qualquer ambiente local, será necessário utilizar o banco Mysql na porta: 3306

## Autor :pencil2:
[Brenda Stefany Lima Cavalcanti] (https://www.linkedin.com/in/brenda-stefany-devjava/)
