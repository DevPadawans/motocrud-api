# Motocrud API
<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
[![All Contributors](https://img.shields.io/badge/all_contributors-4-orange.svg?style=flat-square)](#contributors-)
<!-- ALL-CONTRIBUTORS-BADGE:END -->

## Descrição
Backend para o MotoCrud

## Tecnologias utilizadas


> Para o desenvolvimento do projeto foi utilizada as seguintes tecnologias:


::technologist:: 

 - Java 11 
 - Spring-Boot 
 - JPA/Hibernate
 - Endpoint(REST)
 - Gradle
 - Migration
 - PostgreSQL


## Pré-Requisitos
- A definir....

## Instalação e Uso

```
# Clone o repositório
git clone git@github.com:DevPadawans/motocrud-api.git

```

## Executando o Docker e subindo o PostgreSQL

Lembrando que você precisa estar com o Docker e Docker-Composer instalado, se não tiver, acesse o link: [Instalação do Docker](https://docs.docker.com/engine/install/)

## Parâmetros do PostgreSQL
Você pode alterar alguns parâmetros no  `docker-compose.yaml`

| Parâmetro | Descrição |
| ------ | ------ |
| POSTGRES_USER | O usuário do Postgres para conectar **postgres** |
| POSTGRES_PASSWORD | A senha do Postgres para conectar **postgres** |
| POSTGRES_DB | O nome do banco de dados Postgres para conectar **postgres** |
| port | A porta mapeada pelo Postgres é **5432** em seu contêiner. Neste exemplo, use a porta **5438** na máquina host |

## Parâmetros do pgAdmin
Você pode alterar alguns parâmetros no  `docker-compose.yaml`

Para acessar o pgAdmin [http://localhost:16543/](http://localhost:16543/)

| Parâmetro | Descrição |
| ------ | ------ |
| PGADMIN_DEFAULT_EMAIL | O usuário do pgAdmin para conectar **handersonbf** |
| PGADMIN_DEFAULT_PASSWORD | A senha do Postgres para conectar **1234** |

## Run

Na RAIZ do projeto rode o comando:

```sh
$ docker-compose up
```
Para rodar em segundo plano:

```sh
$ docker-compose up -d
```






## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><a href="https://github.com/robsonprod"><img src="https://avatars.githubusercontent.com/u/6569461?v=4?s=100" width="100px;" alt=""/><br /><sub><b>Robson Azevedo</b></sub></a><br /><a href="https://github.com/DevPadawans/motocrud-api/commits?author=robsonprod" title="Code">💻</a></td>
    <td align="center"><a href="http://www.handersonfrota.com.br"><img src="https://avatars.githubusercontent.com/u/150206?v=4?s=100" width="100px;" alt=""/><br /><sub><b>Handerson Frota</b></sub></a><br /><a href="https://github.com/DevPadawans/motocrud-api/commits?author=handersonbf" title="Code">💻</a> <a href="#projectManagement-handersonbf" title="Project Management">📆</a></td>
    <td align="center"><a href="https://www.lucasapoena.eti.br/"><img src="https://avatars.githubusercontent.com/u/135553?v=4?s=100" width="100px;" alt=""/><br /><sub><b>Lucas Apoena</b></sub></a><br /><a href="https://github.com/DevPadawans/motocrud-api/commits?author=lucasapoena" title="Documentation">📖</a></td>
    <td align="center"><a href="https://github.com/devictorqroz"><img src="https://avatars.githubusercontent.com/u/97463247?v=4?s=100" width="100px;" alt=""/><br /><sub><b>Victor Queiroz </b></sub></a><br /><a href="#question-devictorqroz" title="Answering Questions">💬</a></td>
  </tr>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!