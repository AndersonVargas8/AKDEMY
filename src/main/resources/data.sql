USE akdemy;
INSERT INTO role (description, name) VALUES
('ROLE_ADMIN', 'ADMIN'),
('ROLE_COORDINADOR','COORDINADOR'),
('ROLE_PROFESOR','PROFESOR'),
('ROLE_ACUDIENTE','ACUDIENTE'),
('ROLE_ESTUDIANTE','ESTUDIANTE');

INSERT INTO `user`(`id`, `password`, `username`) VALUES
                                                     (NULL,'$2a$04$n6WIRDQlIByVFi.5rtQwEOTAzpzLPzIIG/O6quaxRKY2LlIHG8uty','admin'),
                                                     (NULL,'$2a$04$n6WIRDQlIByVFi.5rtQwEOTAzpzLPzIIG/O6quaxRKY2LlIHG8uty','usuario');

INSERT INTO `user_roles`(`user_id`, `role_id`) VALUES
                                                   (1,1),
                                                   (2,2),
                                                   (2,4);

INSERT INTO `tipo_documento` (`id`, `tipo_doc_descripcion`) VALUES
                                                                (NULL, 'Registro civil'),
                                                                (NULL, 'Tarjeta de identidad'),
                                                                (NULL, 'Cédula de ciudadanía');
                                                                
INSERT INTO `eps` (`id`, `eps_nombre`) VALUES
(NULL, 'Indefinido'),
(NULL, 'Compensar'),
(NULL, 'Coomeva'),
(NULL, 'Famisanar'),
(NULL, 'Salud Vida'),
(NULL, 'Sanitas'),
(NULL, 'Ecoopsos'),
(NULL, 'ServiSalud'),
(NULL, 'Sura Seguros'),
(NULL, 'Salud Fuerzas Militares '),
(NULL, 'Capital Salud'),
(NULL, 'Medimás'),
(NULL, 'Salud Total'),
(NULL, 'Convida'),
(NULL, 'Comparta'),
(NULL, 'Nueva EPS'),
(NULL, 'Aliansalud'),
(NULL, 'Cruz Blanca'),
(NULL, 'Suramericana'),
(NULL, 'Comfacundi '),
(NULL, 'Sanidad militar'),
(NULL, 'Colsanitas');

INSERT INTO `grupo_sanguineo_rh`(`id`, `gsrh_descripcion`) VALUES
(1, 'A+'),
(2, 'A-'),
(3, 'B+'),
(4, 'B-'),
(5, 'AB+'),
(6, 'AB-'),
(7, 'O+'),
(8, 'O-');

