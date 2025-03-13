-- DROP DATABASE IF EXISTS proyecto_v1;
USE proyecto_v1;

-- Insertar usuarios (2 administradores y 2 usuarios)
INSERT INTO usuario (nombre, email, password, rol) VALUES
                                                       ('Admin1', 'admin1@example.com', 'adminpass', 'ADMIN'),
                                                       ('Admin2', 'admin2@example.com', 'adminpass', 'ADMIN'),
                                                       ('User1', 'user1@example.com', 'userpass', 'USER'),
                                                       ('User2', 'user2@example.com', 'userpass', 'USER');

-- Insertar autores (nombres más conocidos)
INSERT INTO autor (nombre, biografia) VALUES
                                          ('Gabriel García Márquez', 'Escritor colombiano, uno de los más destacados del realismo mágico. Autor de Cien años de soledad.'),
                                          ('J.K. Rowling', 'Escritora británica, famosa por la saga de Harry Potter.'),
                                          ('George Orwell', 'Escritor británico conocido por sus obras como 1984 y Rebelión en la granja.'),
                                          ('Jane Austen', 'Novelista inglesa cuyas obras exploran las cuestiones sociales y el matrimonio en la sociedad británica.'),
                                          ('Leo Tolstoy', 'Escritor ruso conocido por obras como Guerra y Paz y Anna Karenina.'),
                                          ('Franz Kafka', 'Escritor checo cuya obra se caracteriza por lo absurdo y lo existencial.'),
                                          ('Haruki Murakami', 'Novelista japonés, conocido por su estilo único y surrealista.'),
                                          ('Mark Twain', 'Escritor y humorista estadounidense, autor de Las aventuras de Tom Sawyer y Huckleberry Finn.'),
                                          ('Agatha Christie', 'Escritora británica, famosa por sus novelas de misterio y por el detective Hércules Poirot.'),
                                          ('Ernest Hemingway', 'Escritor estadounidense, ganador del Premio Nobel de Literatura. Obras famosas como El viejo y el mar.'),
                                          ('Frida Kahlo', 'Pintora mexicana, famosa por sus autorretratos y su arte único.'),
                                          ('Isaac Asimov', 'Escritor y bioquímico estadounidense, conocido por sus libros de ciencia ficción como Fundación.'),
                                          ('C.S. Lewis', 'Escritor británico, famoso por su serie Las crónicas de Narnia.'),
                                          ('Mario Vargas Llosa', 'Escritor peruano, uno de los más grandes exponentes de la literatura en español.'),
                                          ('Charles Dickens', 'Escritor inglés conocido por sus novelas como Oliver Twist y Cuento de Navidad.'),
                                          ('F. Scott Fitzgerald', 'Escritor estadounidense, conocido por El gran Gatsby.'),
                                          ('Gabriel García Márquez', 'Escritor colombiano, uno de los más destacados del realismo mágico. Autor de Cien años de soledad.'),
                                          ('Virginia Woolf', 'Escritora británica, famosa por su enfoque en los aspectos psicológicos de sus personajes.'),
                                          ('J.R.R. Tolkien', 'Escritor y filólogo británico, conocido por ser el creador de El Señor de los Anillos.'),
                                          ('Sylvia Plath', 'Escritora y poeta estadounidense, conocida por su obra poética y su novela La campana de cristal.'),
                                          ('Leo Tolstoy', 'Escritor ruso conocido por obras como Guerra y Paz y Anna Karenina.'),
                                          ('Kurt Vonnegut', 'Escritor estadounidense conocido por su estilo irreverente y su obra más famosa, Matadero cinco.'),
                                          ('H.P. Lovecraft', 'Escritor estadounidense de horror y ciencia ficción.'),
                                          ('Stephen King', 'Escritor estadounidense conocido por sus novelas de terror como Carrie y It.'),
                                          ('Dante Alighieri', 'Poeta italiano, conocido por su obra maestra La divina comedia.'),
                                          ('Emily Dickinson', 'Poetisa estadounidense, conocida por sus poemas breves y profundos.'),
                                          ('Margaret Atwood', 'Escritora canadiense, conocida por su novela El cuento de la criada.'),
                                          ('William Shakespeare', 'Poeta y dramaturgo inglés, considerado uno de los más grandes escritores de todos los tiempos.'),
                                          ('Neil Gaiman', 'Escritor británico, conocido por sus obras de fantasía como American Gods y Coraline.'),
                                          ('Alice Walker', 'Escritora estadounidense, autora de La color púrpura.'),
                                          ('J.D. Salinger', 'Escritor estadounidense conocido por su novela El guardián entre el centeno.'),
                                          ('Toni Morrison', 'Escritora estadounidense, famosa por su obra Beloved.');

-- Insertar libros (cada uno con un solo autor)
INSERT INTO libro (titulo, descripcion, isbn, autor_id) VALUES
                                                            ('Cien años de soledad', 'La obra más famosa de García Márquez, que narra la historia de la familia Buendía.', '978-0060883287', 1),
                                                            ('Harry Potter y la piedra filosofal', 'El primer libro de la serie de Harry Potter, que introduce al joven mago.', '978-0747532699', 2),
                                                            ('1984', 'Una novela distópica de Orwell sobre un futuro totalitario.', '978-0451524935', 3),
                                                            ('Orgullo y prejuicio', 'Un clásico de Jane Austen que explora las relaciones y las diferencias sociales.', '978-0141439518', 4),
                                                            ('Guerra y paz', 'La famosa novela de Tolstoy que cubre la invasión napoleónica en Rusia.', '978-0143039990', 5),
                                                            ('La metamorfosis', 'Una obra de Kafka que examina la alienación y el absurdo.', '978-0140446285', 6),
                                                            ('Norwegian Wood', 'Una novela de Murakami que explora el amor, la pérdida y la juventud.', '978-0375704024', 7),
                                                            ('Las aventuras de Tom Sawyer', 'Las aventuras de un joven travieso en el río Mississippi.', '978-0486295372', 8),
                                                            ('Asesinato en el Orient Express', 'Una de las más famosas novelas de Agatha Christie con el detective Hercule Poirot.', '978-0062073501', 9),
                                                            ('El viejo y el mar', 'Una historia sobre la lucha entre un hombre y un pez gigante, escrita por Hemingway.', '978-0684801223', 10),
                                                            ('Frida Kahlo: Sus vidas', 'Un vistazo a la vida y el arte de la famosa pintora mexicana.', '978-0061120253', 11),
                                                            ('Fundación', 'Una obra maestra de la ciencia ficción de Isaac Asimov.', '978-0553382570', 12),
                                                            ('El león, la bruja y el armario', 'El primer libro de las Crónicas de Narnia, escrito por C.S. Lewis.', '978-0064404990', 13),
                                                            ('La ciudad y los perros', 'Una novela que narra la vida de un grupo de cadetes militares en Lima.', '978-8437603737', 14),
                                                            ('Cuento de Navidad', 'Una de las más famosas historias de Dickens, que trata sobre la redención.', '978-0141192589', 15),
                                                            ('El gran Gatsby', 'La historia de un amor no correspondido en la América de los años 20.', '978-0743273565', 16),
                                                            ('Mrs Dalloway', 'Una novela de Virginia Woolf que se desarrolla en un solo día.', '978-0156628709', 17),
                                                            ('El Hobbit', 'El primer libro de Tolkien en la Tierra Media, precuela de El Señor de los Anillos.', '978-0547928227', 18),
                                                            ('La campana de cristal', 'Una novela semi-autobiográfica de Sylvia Plath.', '978-0060837029', 19),
                                                            ('Anna Karenina', 'Una obra maestra de Tolstoy sobre amor, obsesión y tragedia.', '978-0375756191', 20),
                                                            ('Matadero cinco', 'Una novela anti-bélica y filosófica de Kurt Vonnegut.', '978-0385333849', 21),
                                                            ('El color de la magia', 'La primera novela de la serie Mundodisco de Terry Pratchett.', '978-0062225747', 22),
                                                            ('Carrie', 'Una novela de terror psicológico escrita por Stephen King.', '978-0307743664', 23),
                                                            ('La divina comedia', 'El poema épico de Dante sobre el viaje de un alma por el infierno, el purgatorio y el paraíso.', '978-0140448952', 24),
                                                            ('En busca del tiempo perdido', 'La obra monumental de Marcel Proust sobre la memoria y el tiempo.', '978-0141180315', 25),
                                                            ('American Gods', 'Una novela de Neil Gaiman que mezcla mitología y lo moderno.', '978-0380789030', 26),
                                                            ('La color púrpura', 'Una novela de Alice Walker sobre las dificultades de una mujer afroamericana en el sur de los EE.UU.', '978-0156028356', 27),
                                                            ('El guardián entre el centeno', 'La famosa novela de Salinger sobre la adolescencia y la alienación.', '978-0316769488', 28),
                                                            ('Beloved', 'Una novela de Toni Morrison sobre la esclavitud y la libertad.', '978-1400033416', 29),
                                                            ('El retrato de Dorian Gray', 'Una novela de Oscar Wilde sobre la belleza, la moral y la decadencia.', '978-0141439570', 30);

-- Insertar reseñas
INSERT INTO reseña (comentario, calificacion, libro_id, usuario_id) VALUES
                                                                        ('Una obra maestra del realismo mágico. Inolvidable.', 5, 1, 3),
                                                                        ('Un comienzo épico de la saga más conocida de todos los tiempos.', 5, 2, 4),
                                                                        ('Una crítica feroz al totalitarismo. Profundamente relevante.', 4, 3, 2),
                                                                        ('Una exploración brillante de la sociedad británica. Imprescindible.', 5, 4, 1),
                                                                        ('Un viaje épico que cubre generaciones. Impresionante.', 5, 5, 3),
                                                                        ('El absurdo llevado al extremo. Fascinante.', 4, 6, 2),
                                                                        ('Una historia de amor triste y melancólica. Hermosa.', 5, 7, 4),
                                                                        ('Divertida y llena de aventura. Un clásico de la literatura.', 5, 8, 1),
                                                                        ('Una de las mejores novelas de misterio que existen.', 5, 9, 3),
                                                                        ('Profunda, filosófica y emotiva. De las mejores de Hemingway.', 5, 10, 4),
                                                                        ('Un vistazo hermoso a la vida de Frida Kahlo.', 4, 11, 1),
                                                                        ('Una trama compleja, pero muy satisfactoria.', 4, 12, 2),
                                                                        ('Un relato mágico que toca el corazón.', 5, 13, 3),
                                                                        ('Una de las novelas más importantes del siglo XX.', 5, 14, 4),
                                                                        ('Una historia triste y conmovedora. Perfecta para reflexionar.', 5, 15, 1),
                                                                        ('Una novela sobre la vida y el amor. Impresionante.', 5, 16, 2),
                                                                        ('Fascinante y psicológica. Genialmente escrita.', 5, 17, 3),
                                                                        ('Una historia fascinante y llena de magia.', 5, 18, 4),
                                                                        ('Una lectura imprescindible para entender el sufrimiento humano.', 4, 19, 1),
                                                                        ('Una novela conmovedora sobre la libertad.', 5, 20, 2);

-- Insertar colecciones (con libros asignados)
INSERT INTO coleccion (nombre, usuario_id) VALUES
                                               ('Colección de Clásicos', 3),
                                               ('Colección de Ciencia Ficción', 3),
                                               ('Colección de Misterio', 3),
                                               ('Colección de Aventuras', 4),
                                               ('Colección de Literatura Contemporánea', 4),
                                               ('Colección de Ensayo', 4);

-- Insertar relaciones entre colecciones y libros
INSERT INTO coleccion_libro (coleccion_id, libro_id) VALUES
                                                         (1, 1), (1, 4), (1, 5),
                                                         (2, 12), (2, 3), (2, 20),
                                                         (3, 9), (3, 15), (3, 16),
                                                         (4, 8), (4, 7), (4, 10),
                                                         (5, 13), (5, 14), (5, 6),
                                                         (6, 19), (6, 18), (6, 17);