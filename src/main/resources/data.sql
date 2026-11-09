-- Desactivar temporalmente chequeo de claves foráneas para truncar tablas
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE repartidores;
SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO repartidores (id, nombre, tipo_vehiculo, estado, user_id, fecha_creacion, fecha_ultima_modificacion) VALUES
 (UUID_TO_BIN('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 0), 'Jorge Armella', 'MOTO', 'ACTIVO', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
 (UUID_TO_BIN('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 0), 'Laura Fernandez', 'UTILITARIO', 'ACTIVO', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
 (UUID_TO_BIN('cccccccc-cccc-cccc-cccc-cccccccccccc', 0), 'Pedro Diaz', 'BICICLETA', 'INACTIVO', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);