CREATE SEQUENCE "seq_usuario"
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;

CREATE TABLE "tb_usuario"
(
    "us_id"              INTEGER                NOT NULL,
    "us_nome"            CHARACTER VARYING(255) NOT NULL,
    "us_cpf"             CHARACTER VARYING(255) NOT NULL,
    "us_email"           CHARACTER VARYING(255) NOT NULL,
    "us_senha"           CHARACTER VARYING(500) NOT NULL,
    "us_data_nascimento" TIMESTAMP(6)
);

ALTER TABLE "tb_usuario"
    ADD CONSTRAINT "pk_us_id" PRIMARY KEY ("us_id");

ALTER TABLE "tb_usuario"
    ADD CONSTRAINT "uc_us_email" UNIQUE ("us_email");

ALTER TABLE "tb_usuario"
    ADD CONSTRAINT "uc_us_cpf" UNIQUE ("us_cpf");
