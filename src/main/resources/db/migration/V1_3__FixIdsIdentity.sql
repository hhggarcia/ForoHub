ALTER TABLE cursos
    ALTER COLUMN id SET DATA TYPE BIGINT,
    ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;

ALTER TABLE usuarios
    ALTER COLUMN id_usuario SET DATA TYPE BIGINT,
    ALTER COLUMN id_usuario ADD GENERATED ALWAYS AS IDENTITY;

ALTER TABLE perfiles
    ALTER COLUMN id_perfil SET DATA TYPE BIGINT,
    ALTER COLUMN id_perfil ADD GENERATED ALWAYS AS IDENTITY;

ALTER TABLE topicos
    ALTER COLUMN id SET DATA TYPE BIGINT,
    ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;

ALTER TABLE respuestas
    ALTER COLUMN id SET DATA TYPE BIGINT,
    ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;