CREATE TABLE vendas (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data_criacao DATETIME NOT NULL,
    valor_frete DECIMAL(10,2),
    valor_desconto DECIMAL(10,2),
    valor_total DECIMAL(10,2) NOT NULL,
    status VARCHAR(30) NOT NULL,
    observacao VARCHAR(200),
    data_entrega DATETIME,
    codigo_cliente BIGINT(20) NOT NULL,
    codigo_usuario BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_cliente) REFERENCES clientes(codigo),
    FOREIGN KEY (codigo_usuario) REFERENCES usuarios(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE itens_vendas (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    quantidade INTEGER NOT NULL,
    valor_unitario DECIMAL(10,2) NOT NULL,
    codigo_cerveja BIGINT(20) NOT NULL,
    codigo_venda BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_cerveja) REFERENCES cervejas(codigo),
    FOREIGN KEY (codigo_venda) REFERENCES vendas(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;