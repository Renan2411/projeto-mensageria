INSERT INTO [tb_roles] (rl_id, rl_nome, rl_descricao)
SELECT NEXT VALUE FOR seq_roles, 'ADMINISTRADOR', 'Administrador do sistema';

INSERT INTO [tb_roles] (rl_id, rl_nome, rl_descricao)
SELECT NEXT VALUE FOR seq_roles, 'USUARIO_COMUM', 'Usuário comum do sistema';