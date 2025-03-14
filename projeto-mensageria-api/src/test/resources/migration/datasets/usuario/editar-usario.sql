DROP SEQUENCE IF EXISTS seq_usuario;
CREATE SEQUENCE seq_usuario;

INSERT INTO tb_usuario(us_id, us_nome, us_cpf, us_email, us_data_nascimento)
VALUES (NEXTVAL('seq_usuario'),
        'Teste',
        '80220885443',
        'teste@gmail.com',
        '2024-11-06T14:30:45Z');

INSERT INTO tb_usuario(us_id, us_nome, us_cpf, us_email, us_data_nascimento)
VALUES (NEXTVAL('seq_usuario'),
    'Teste',
    '99153644158',
    'teste2@gmail.com',
    '2024-11-06T14:30:45Z');