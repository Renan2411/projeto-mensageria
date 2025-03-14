CREATE SEQUENCE [seq_usuario]
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE [tb_usuario] (
    [us_id]              INTEGER NOT NULL,
    [us_nome]            VARCHAR(255) NOT NULL,
    [us_cpf]             VARCHAR(255) NOT NULL,
    [us_email]           VARCHAR(255) NOT NULL,
    [us_senha]           VARCHAR(500) NOT NULL,
    [us_data_nascimento] DATETIME2(6)
);

ALTER TABLE [tb_usuario] ADD CONSTRAINT [pk_us_id] PRIMARY KEY ([us_id]);

ALTER TABLE [tb_usuario] ADD CONSTRAINT [uc_us_email] UNIQUE ([us_email]);

ALTER TABLE [tb_usuario] ADD CONSTRAINT [uc_us_cpf] UNIQUE ([us_cpf]);