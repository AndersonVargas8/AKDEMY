INSERT INTO role (id,description, name) VALUES
(1,'ROLE_ADMIN', 'ADMIN'),
(2,'ROLE_COORDINADOR','COORDINADOR'),
(3,'ROLE_PROFESOR','PROFESOR'),
(4,'ROLE_ACUDIENTE','ACUDIENTE'),
(5,'ROLE_ESTUDIANTE','ESTUDIANTE');

INSERT INTO public.user(id, password, username) VALUES 
(1,'$2a$04$n6WIRDQlIByVFi.5rtQwEOTAzpzLPzIIG/O6quaxRKY2LlIHG8uty','admin'),
(2,'$2a$04$n6WIRDQlIByVFi.5rtQwEOTAzpzLPzIIG/O6quaxRKY2LlIHG8uty','usuario');

INSERT INTO user_roles(user_id, role_id) VALUES 
(1,1),
(2,2),
(2,4);

INSERT INTO tipo_documento (id, tipo_doc_descripcion) VALUES 
(1, 'Registro civil'), 
(2, 'Tarjeta de identidad'), 
(3, 'Cédula de ciudadanía');

INSERT INTO eps (id, eps_nombre) VALUES
(1, 'Indefinido'),
(2, 'Compensar'),
(3, 'Coomeva'),
(4, 'Famisanar'),
(5, 'Salud Vida'),
(6, 'Sanitas'),
(7, 'Ecoopsos'),
(8, 'ServiSalud'),
(9, 'Sura Seguros'),
(10, 'Salud Fuerzas Militares '),
(11, 'Capital Salud'),
(12, 'Medimás'),
(13, 'Salud Total'),
(14, 'Convida'),
(15, 'Comparta'),
(16, 'Nueva EPS'),
(17, 'Aliansalud'),
(18, 'Cruz Blanca'),
(19, 'Suramericana'),
(20, 'Comfacundi '),
(21, 'Sanidad militar'),
(22, 'Colsanitas');

INSERT INTO grupo_sanguineo_rh(id, gsrh_descripcion) VALUES
(1, 'A+'),
(2, 'A-'),
(3, 'B+'),
(4, 'B-'),
(5, 'AB+'),
(6, 'AB-'),
(7, 'O+'),
(8, 'O-');