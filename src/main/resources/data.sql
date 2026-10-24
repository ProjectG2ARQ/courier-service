-- Desactivar temporalmente chequeo de claves foráneas para truncar tablas
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE repartidores;
SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO repartidores (id, nombre, tipo_vehiculo, estado, fecha_creacion, fecha_ultima_modificacion) VALUES
    (UUID_TO_BIN('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 1), 'Carlos Gomez', 'MOTO', 'ACTIVO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (UUID_TO_BIN('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 1), 'Lucia Fernandez', 'UTILITARIO', 'ACTIVO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (UUID_TO_BIN('cccccccc-cccc-cccc-cccc-cccccccccccc', 1), 'Pedro Diaz', 'BICICLETA', 'INACTIVO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
