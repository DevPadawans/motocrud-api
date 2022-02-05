CREATE SEQUENCE membros_seq;

CREATE TABLE IF NOT EXISTS membros (
    id          INTEGER NOT NULL PRIMARY KEY,
    apelido     VARCHAR(100) NOT NULL,
    nome        VARCHAR(100) NOT NULL,
    padrinho    VARCHAR(100) NOT NULL,
    inicio      TIMESTAMP NOT NULL,
    nascimento  TIMESTAMP
);
