ALTER TABLE topicos
    ADD COLUMN autor_id BIGINT,
    ADD COLUMN curso_id BIGINT,
    ADD CONSTRAINT fk_autor
    FOREIGN KEY (autor_id) REFERENCES usuarios(id_usuario),
    ADD CONSTRAINT fk_curso
    FOREIGN KEY (curso_id) REFERENCES cursos(id);