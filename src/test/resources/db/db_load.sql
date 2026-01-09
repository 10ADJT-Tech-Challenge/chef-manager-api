INSERT INTO TIPO_USUARIO (id, nome, categoria_usuario) VALUES
('e98915ad-2a2f-4463-ac5f-38258d3ffa5d', 'CLIENTE', 'CLIENTE'),
('d4092ccc-98be-4475-8e5a-c02f8bec2044', 'CLIENTE_TESTE', 'CLIENTE'),
('cec64cf0-6dc9-4b4e-b0b8-405870ae1b43', 'ADMIN', 'ADMIN'),
('f6f2a623-b22b-4494-bb35-cff956c86e5c', 'DONO_RESTAURANTE', 'DONO_RESTAURANTE');

INSERT INTO USUARIO (cep, cidade, data_ultima_alteracao, email, login, nome, numero, rua, senha, tipo_usuario_id, uf, id) VALUES
('12345-678', 'São Paulo', '2024-01-01 10:00:00',
 'joaosilva@email.com', 'joaosilva', 'João Silva', '100', 'Rua A',
 '$2a$10$B/WXJb6OA9sCy1T492JDc.JPvjSUTXADCLQGAaJDMYZ5b3JiSq9/.',
 'e98915ad-2a2f-4463-ac5f-38258d3ffa5d', 'SP', 'f6f2a623-b22b-4494-bb35-cff956c86e5c'),
('23456-789', 'Rio de Janeiro', '2024-02-01 11:00:00',
    'mariasilva@email.com', 'mariasilva', 'Maria Silva', '200', 'Avenida B',
    '$2a$10$B/WXJb6OA9sCy1T492JDc.JPvjSUTXADCLQGAaJDMYZ5b3JiSq9/.',
    'cec64cf0-6dc9-4b4e-b0b8-405870ae1b43', 'RJ', 'cec64cf0-6dc9-4b4e-b0b8-405870ae1b43'),
('34567-890', 'Belo Horizonte', '2024-03-01 12:00:00',
    'pedrosilva@email.com', 'pedrosilva', 'Pedro Silva', '300', 'Travessa C',
    '$2a$10$B/WXJb6OA9sCy1T492JDc.JPvjSUTXADCLQGAaJDMYZ5b3JiSq9/.',
    'f6f2a623-b22b-4494-bb35-cff956c86e5c', 'MG', 'e98915ad-2a2f-4463-ac5f-38258d3ffa5d');

