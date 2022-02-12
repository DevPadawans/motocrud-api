CREATE TABLE IF NOT EXISTS membro (
    id          BIGINT PRIMARY KEY NOT NULL,
    apelido     VARCHAR(100) NOT NULL,
    nome        VARCHAR(100) NOT NULL,
    padrinho    VARCHAR(100) NOT NULL,
    inicio      TIMESTAMP NOT NULL,
    nascimento  TIMESTAMP,
    created_by  VARCHAR(100),
    updated_by  VARCHAR(100),
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    ativo       BOOLEAN DEFAULT TRUE
);
