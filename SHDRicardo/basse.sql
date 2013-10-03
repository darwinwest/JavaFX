create table Alumno(
idAlumno serial primary key,
nombre varchar (50) not null,
apaterno varchar (50) not null,
amaterno varchar (50) not null,
FechaNac varchar (50) not null,
sexo varchar (10) not null,
carrera int not null,
activo boolean default 'true',
CONSTRAINT carrera FOREIGN KEY (carrera) REFERENCES Carrera (idcarrera)
);

create table Carrera(
idCarrera serial primary key not null,
nombre varchar(50) not null,
siglas varchar(30) not null,
jdc varchar (50) not null,
matricula varchar (30) not null,
acreditada varchar (5) not null,
activo boolean default true
);


SELECT idcarrera, nombre, siglas, jdc, matricula, acreditada FROM carrera where idcarrera=?;

select * from carrera;

UPDATE carrera
   SET nombre=?, siglas=?, jdc=?, matricula=?, acreditada=?
 WHERE idcarrera=?;


INSERT INTO carrera(idcarrera, nombre, siglas, jdc, matricula, acreditada)
    VALUES (default, ?, ?, ?, ?, ?);


INSERT INTO alumno(
            idalumno, nombre, apaterno, amaterno, fechanac, sexo, carrera)
    VALUES (1, 'nombre', 'datos', 'sdf', 'sdfsd', 'ssdsd', 1);

    SELECT idalumno, nombre, apaterno, amaterno, fechanac, sexo, carrera
  FROM alumno;

  UPDATE alumno
   SET idalumno=?, nombre=?, apaterno=?, amaterno=?, fechanac=?, sexo=?, 
       carrera=?
 WHERE <condition>;


SELECT idalumno, nombre, apaterno, amaterno, fechanac, sexo , carrera FROM alumno;
