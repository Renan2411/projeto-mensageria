CREATE SEQUENCE [seq_roles]
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE [tb_roles] (
    [rl_id]        INTEGER NOT NULL,
    [rl_nome]      VARCHAR(255) NOT NULL,
    [rl_descricao] VARCHAR(255) NOT NULL
);

ALTER TABLE [tb_roles] ADD CONSTRAINT [pk_rl_id] PRIMARY KEY ([rl_id]);

ALTER TABLE [tb_roles] ADD CONSTRAINT [uc_rl_nome] UNIQUE([rl_nome]);