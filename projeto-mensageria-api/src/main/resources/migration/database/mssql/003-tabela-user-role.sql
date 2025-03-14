CREATE SEQUENCE [seq_user_role]
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE [tb_user_role] (
    [ur_id] INTEGER NOT NULL,
    [us_id] INTEGER NOT NULL,
    [rl_id] INTEGER NOT NULL
);

ALTER TABLE [tb_user_role] ADD CONSTRAINT [pk_ur_id] PRIMARY KEY ([ur_id]);

ALTER TABLE [tb_user_role] ADD CONSTRAINT [fk_us_id] FOREIGN KEY ([us_id]) REFERENCES [tb_usuario] ([us_id]);

ALTER TABLE [tb_user_role] ADD CONSTRAINT [fk_rl_id] FOREIGN KEY ([rl_id]) REFERENCES [tb_roles] ([rl_id]);