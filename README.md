# Motocrud API

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





