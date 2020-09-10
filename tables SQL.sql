CREATE TABLE Empleados(
    persona Empleado,
    PRIMARY KEY (persona.nombre_usuario)
);

CREATE TABLE Extras(
    extra Extra,
    primary key (extra.id)
);

CREATE TABLE Turismos(
    tur Turismo,
    idExtra SMALLINT,
    FOREIGN KEY (idExtra) REFERENCES Extras,
    primary key (tur.vehi.matricula)
);

CREATE TABLE Camiones(
    cam Camion,
    primary key (cam.vehi.matricula)
);

CREATE TABLE Matriculas(
    matricu varchar2(7) primary key
);

