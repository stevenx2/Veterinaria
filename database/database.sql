use veterinaria;

CREATE TABLE propietario(
id_propietario INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(20),
apellido VARCHAR(20),
edad INT,
PRIMARY KEY(id_propietario)
);

INSERT INTO propietario(nombre,apellido,edad) VALUES
("Johan","Mendoza",19),
("José","Rentería",40),
("Rosa","Melano",40);


CREATE TABLE mascotas(
id_mascota INT NOT NULL AUTO_INCREMENT,
id_propietario INT NOT NULL,
tipo VARCHAR(20),
nombre VARCHAR(20),
fecha_ingreso DATE,
PRIMARY KEY(id_mascota),
FOREIGN KEY(id_propietario) REFERENCES propietario(id_propietario)
ON UPDATE CASCADE
ON DELETE CASCADE
);

INSERT INTO mascotas(tipo,nombre,fecha_ingreso,id_propietario) VALUES
("perro","Scott","2024-03-03",1),
("gato","Picinga","2022-04-10",2),
("Loro","Loro","2020-03-03",3)



