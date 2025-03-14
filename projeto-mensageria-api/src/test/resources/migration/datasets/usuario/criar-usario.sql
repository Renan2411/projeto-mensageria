DROP SEQUENCE IF EXISTS seq_usuario;
CREATE SEQUENCE seq_usuario;

DROP SEQUENCE IF EXISTS seq_user_role;
CREATE SEQUENCE seq_user_role;

INSERT INTO tb_usuario(us_id, us_nome, us_cpf, us_email, us_data_nascimento)
VALUES (NEXTVAL('seq_usuario'),
        'Teste',
        '80220885443',
        'teste@gmail.com',
        '2024-11-06T14:30:45Z')