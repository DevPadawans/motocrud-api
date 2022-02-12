CREATE TABLE IF NOT EXISTS membro (
    id          BIGINT PRIMARY KEY NOT NULL,
    apelido     VARCHAR(100) NOT NULL,
    nome        VARCHAR(100) NOT NULL,
    padrinho    VARCHAR(100) NOT NULL,
    inicio      TIMESTAMP NOT NULL,
    nascimento  TIMESTAMP
);
