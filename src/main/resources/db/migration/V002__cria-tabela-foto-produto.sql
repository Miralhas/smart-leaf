CREATE TABLE foto_solar_panel (
    solar_panel_id BIGINT NOT NULL,
    nome_arquivo VARCHAR(150) NOT NULL,
    descricao VARCHAR(150),
    content_type VARCHAR(80) NOT NULL,
    tamanho INT NOT NULL,

    PRIMARY KEY (solar_panel_id),
    CONSTRAINT fk_foto_solarpanel_solarpanel FOREIGN KEY (solar_panel_id) REFERENCES solar_panel(id)
)ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;