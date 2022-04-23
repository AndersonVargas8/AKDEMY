USE akdemy;

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
