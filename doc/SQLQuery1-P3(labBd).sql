CREATE DATABASE hotel;
GO
USE hotel;


CREATE FUNCTION listarQuartosDisponiveis(
    @data_entrada DATE,
    @qtd_dias INT
)
RETURNS @resultado TABLE (
    codigo_quarto INT,
    numero_quarto INT,
    andar_quarto INT,
    descricao VARCHAR(100),
    tipo_quarto VARCHAR(100),
    preco DECIMAL(10,2)
)
AS
BEGIN
    DECLARE @codigo_quarto INT
    DECLARE @numero_quarto INT
    DECLARE @andar_quarto INT
    DECLARE @descricao VARCHAR(100)
    DECLARE @tipo_quarto VARCHAR(100)
    DECLARE @preco DECIMAL(10,2)
    
    DECLARE cur CURSOR FOR
    SELECT 
        q.codigo_quarto,
        q.numero_quarto,
        q.andar_quarto,
        q.descricao,
        tq.nome AS tipo_quarto,
        tq.preco
    FROM Quarto q
    INNER JOIN TipoQuarto tq ON q.tipo = tq.codigo_tipo
    WHERE q.codigo_quarto NOT IN (
        SELECT r.quarto_id 
        FROM Reserva r 
        WHERE 
            (@data_entrada BETWEEN r.dtEntrada AND DATEADD(DAY, r.qtdDias - 1, r.dtEntrada))
            OR 
            (DATEADD(DAY, @qtd_dias - 1, @data_entrada) BETWEEN r.dtEntrada AND DATEADD(DAY, r.qtdDias - 1, r.dtEntrada))
            OR
            (r.dtEntrada BETWEEN @data_entrada AND DATEADD(DAY, @qtd_dias - 1, @data_entrada))
    )
    AND q.ocupado = 0
    
    OPEN cur
    
    FETCH NEXT FROM cur INTO @codigo_quarto, @numero_quarto, @andar_quarto, @descricao, @tipo_quarto, @preco
    
    WHILE @@FETCH_STATUS = 0
    BEGIN
        INSERT INTO @resultado VALUES (@codigo_quarto, @numero_quarto, @andar_quarto, @descricao, @tipo_quarto, @preco)
        FETCH NEXT FROM cur INTO @codigo_quarto, @numero_quarto, @andar_quarto, @descricao, @tipo_quarto, @preco
    END
    
    CLOSE cur
    DEALLOCATE cur
    
    RETURN
END
GO

INSERT INTO tipo_quarto (nome, descricao, preco) VALUES
('Standard', 'Quarto padrão com cama de casal', 150.00),
('Luxo', 'Quarto de luxo com vista para o mar', 300.00),
('Suíte Presidencial', 'Suíte com sala e hidromassagem', 500.00),
('Familiar', 'Quarto com duas camas de casal', 250.00)

INSERT INTO cliente (cpf, nome, telefone, cidade_origem) VALUES
('12345678901', 'João Silva', '11999998888', 'São Paulo'),
('23456789012', 'Maria Santos', '11977776666', 'Rio de Janeiro'),
('34567890123', 'Pedro Oliveira', '11955554444', 'Belo Horizonte'),
('45678901234', 'Ana Costa', '11933332222', 'Porto Alegre'),
('56789012345', 'Carlos Pereira', '11911110000', 'Salvador')
GO

INSERT INTO quarto (andar_quarto, numero_quarto, descricao, tipo, ocupado) VALUES
(1, 101, 'Quarto com vista para o jardim', 'Standard', 0),
(1, 102, 'Quarto próximo à recepção', 'Standard', 0),
(2, 201, 'Quarto com varanda', 'Luxo', 0),
(2, 202, 'Quarto silencioso', 'Luxo', 0),
(3, 301, 'Suíte master', 'Suíte Presidencial', 0),
(3, 302, 'Suíte familiar grande', 'Familiar', 0),
(4, 401, 'Suíte presidencial master','Suíte Presidencial', 0);
GO

INSERT INTO reserva (cliente, codigo_quarto, data_reserva, dias_reservados) VALUES
('12345678901', 1, '2024-01-15', 3),
('23456789012', 3, '2024-01-20', 5),
('34567890123', 5, '2024-02-01', 2)
GO

INSERT INTO estadia (cliente, quarto, codigo_reserva, check_in, check_out, ativa) VALUES
('12345678901', 1, 1, '2024-01-15', '2024-01-18', 0),
('23456789012', 3, 2, '2024-01-20', '2024-01-25', 0),
('34567890123', 5, 3, '2024-02-01', NULL, 1),
('45678901234', 2, NULL, '2024-02-10', NULL, 1);
GO

INSERT INTO servico (nome, descricao, preco) VALUES
('Café da Manhã', 'Buffet completo de café da manhã', 25.00),
('Lavanderia', 'Serviço de lavagem e passagem de roupas', 15.00),
('Massagem', 'Sessão de massagem relaxante', 80.00),
('Almoço', 'Refeição completa no restaurante do hotel', 45.00),
('Translado', 'Serviço de transporte aeroporto-hotel', 30.00);
GO

INSERT INTO servico_solicitado (codigo_servico, codigo_estadia, quantidade, valor_pago) VALUES
(1, 1, 2, 50.00),
(2, 1, 1, 15.00),
(1, 2, 2, 50.00),
(3, 2, 1, 80.00),
(4, 3, 1, 45.00),   
(5, 3, 1, 30.00),
(1, 1, 2, 50.00),
(2, 1, 3, 45.00),
(3, 1, 1, 80.00),
(1, 2, 2, 50.00),
(4, 3, 1, 35.00),
(5, 3, 1, 50.00);

SELECT
    r.codigo_reserva,
    c.cpf,
    c.nome as cliente_nome,
    c.telefone,
    q.numero_quarto,
    q.andar_quarto,
    tq.nome as tipo_quarto,
    tq.preco,
    r.data_reserva,
    r.dias_reservados,
    DATEADD(day, r.dias_reservados, r.data_reserva) as data_saida,
    (r.dias_reservados * tq.preco) as valor_total_reserva
FROM reserva r
INNER JOIN cliente c ON r.cliente = c.cpf
INNER JOIN quarto q ON r.codigo_quarto = q.codigo_quarto
INNER JOIN tipo_quarto tq ON q.tipo = tq.nome
WHERE '2025-11-22' BETWEEN r.data_reserva AND DATEADD(day, r.dias_reservados, r.data_reserva)
ORDER BY r.data_reserva, c.nome