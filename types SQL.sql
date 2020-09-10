
CREATE OR REPLACE TYPE Empleado AS OBJECT(
    nombre_usuario varchar2(50),
    contrasena varchar2(15)
);

CREATE OR REPLACE TYPE Extra AS OBJECT(
    id integer ,
    descripcion varchar2(60)
);

CREATE OR REPLACE TYPE Vehiculo AS OBJECT(
    matricula varchar2(7),
    marca varchar2(60),
    modelo varchar2(60),
    color varchar2(15),
    precio number (11,2)
);

CREATE OR REPLACE TYPE Turismo AS OBJECT(
    vehi Vehiculo,
    num_puertas number(1)
);

CREATE OR REPLACE TYPE Camion AS OBJECT(
    vehi Vehiculo,
    capacidad_de_carga number (6)
);