CREATE SEQUENCE membro_seq;

CREATE TABLE IF NOT EXISTS membro (
    id          NUMBER DEFAULT membro_seq.NEXTVALL NOT NULL,
    apelido     VARCHAR(100) NOT NULL,
    nome        VARCHAR(100) NOT NULL,
    padrinho    VARCHAR(100) NOT NULL,
    inicio      TIMESTAMP NOT NULL,
    nascimento  TIMESTAMP,
    CONSTRAINT membro_pk PRIMARY KEY (id)
);
