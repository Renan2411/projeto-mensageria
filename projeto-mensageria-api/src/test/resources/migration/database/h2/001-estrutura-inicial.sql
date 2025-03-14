--TABELA USUÁRIOS
CREATE TABLE tb_usuario
(
    us_id              INTEGER      NOT NULL,
    us_nome            VARCHAR(255) NOT NULL,
    us_cpf             VARCHAR(255) NOT NULL,
    us_email           VARCHAR(255) NOT NULL,
    us_data_nascimento TIMESTAMP(6)
);

ALTER TABLE tb_usuario
    ADD CONSTRAINT tb_usuario_pk PRIMARY KEY (us_id);

ALTER TABLE tb_usuario
    ADD CONSTRAINT uc_us_email UNIQUE (us_email);

ALTER TABLE tb_usuario
    ADD CONSTRAINT uc_us_cpf UNIQUE (us_cpf);

--TABELA ROLES
CREATE TABLE tb_roles
(
    rl_id        INTEGER                NOT NULL,
    rl_nome      CHARACTER VARYING(255) NOT NULL,
    rl_descricao CHARACTER VARYING(255) NOT NULL
);

ALTER TABLE tb_roles
    ADD CONSTRAINT pk_rl_id PRIMARY KEY (rl_id);

ALTER TABLE tb_roles
    ADD CONSTRAINT uc_rl_nome UNIQUE (rl_nome);

--TABELA USER_ROLE
CREATE TABLE tb_user_role
(
    ur_id INTEGER NOT NULL,
    us_id INTEGER NOT NULL,
    rl_id INTEGER NOT NULL
);

ALTER TABLE tb_user_role
    ADD CONSTRAINT pk_ur_id PRIMARY KEY (ur_id);

ALTER TABLE tb_user_role
    ADD CONSTRAINT fk_us_id FOREIGN KEY (us_id)
        REFERENCES tb_usuario (us_id);

ALTER TABLE tb_user_role
    ADD CONSTRAINT fk_rl_id FOREIGN KEY (rl_id)
        REFERENCES tb_roles (rl_id);