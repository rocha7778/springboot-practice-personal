INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email,intentos_login) VALUES ('andres','$2a$10$a/gIh2RoaKlKomYPIaVy/.D31TtrE3e4C5tw0q0F2y0sW.nHxNtPS',1, 'Andres', 'Guzman','profesor@bolsadeideas.com',0);
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email,intentos_login) VALUES ('admin','$2a$10$Kuale/n67OjguMwBcLH9LOeFzXCj9Wgg5VwmGWbOqxsDZJAW1VAPi',1, 'John', 'Doe','jhon.doe@bolsadeideas.com',0);


INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `roles_usuario` (role_id,usuario_id) VALUES (1,2);
INSERT INTO `roles_usuario` (role_id,usuario_id) VALUES (2,2);
INSERT INTO `roles_usuario` (role_id,usuario_id) VALUES (1,2);

