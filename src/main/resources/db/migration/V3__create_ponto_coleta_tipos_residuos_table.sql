CREATE TABLE ponto_coleta_tipos_residuos (
    ponto_coleta_id BIGINT NOT NULL,
    tipo_residuo_id BIGINT NOT NULL,
    CONSTRAINT pk_ponto_coleta_tipos_residuos PRIMARY KEY (ponto_coleta_id, tipo_residuo_id),
    CONSTRAINT fk_pctr_ponto_coleta FOREIGN KEY (ponto_coleta_id)
    REFERENCES ponto_coleta (id)
    ON DELETE CASCADE,
    CONSTRAINT fk_pctr_tipo_residuo FOREIGN KEY (tipo_residuo_id)
    REFERENCES tipo_residuo (id)
    ON DELETE CASCADE
);