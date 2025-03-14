CREATE SEQUENCE seq_roles;

INSERT INTO tb_roles (rl_id, rl_nome, rl_descricao)
VALUES (NEXTVAL('seq_roles'), 'ADMINISTRADOR', 'Administrador do sistema');

INSERT INTO tb_roles (rl_id, rl_nome, rl_descricao)
VALUES (NEXTVAL('seq_roles'), 'USUARIO_COMUM', 'Usuário comum do sistema');