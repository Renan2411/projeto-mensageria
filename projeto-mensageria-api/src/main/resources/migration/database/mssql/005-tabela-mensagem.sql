CREATE SEQUENCE [seq_mensagem]
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE [tb_mensagem] (
    [ms_id]                 INTEGER NOT NULL,
    [us_id_remetente]       INTEGER,
    [us_id_destinatario]    INTEGER,
    [ms_conteudo]           TEXT NOT NULL,
    [ms_log_erro]           TEXT,
    [ms_situacao]           VARCHAR(50) DEFAULT 'PROCESSANDO' NOT NULL,
    [ms_dthr_envio]         DATETIME
);

ALTER TABLE [tb_mensagem] ADD CONSTRAINT [pk_ms_id] PRIMARY KEY ([ms_id]);

ALTER TABLE [tb_mensagem] ADD CONSTRAINT [fk_us_id_remetente] FOREIGN KEY ([us_id_remetente]) REFERENCES [tb_usuario] ([us_id]);

ALTER TABLE [tb_mensagem] ADD CONSTRAINT [fk_us_id_destinatario] FOREIGN KEY ([us_id_destinatario]) REFERENCES [tb_usuario] ([us_id]);