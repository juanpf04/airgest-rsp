CREATE DATABASE `MS`;

DROP TABLE Modelo;
DROP TABLE Comercial;
DROP TABLE Privado;
DROP TABLE Avion;
DROP TABLE aerolinea;
DROP TABLE Contrato;
DROP TABLE Hangar;
DROP TABLE Personal_Seguridad;
DROP TABLE Personal_Limpieza;
DROP TABLE Personal;
DROP TABLE Linea_Contrato ;
DROP TABLE Aerolinea_Modelo;
DROP TABLE Personal_Hangar;

CREATE TABLE Modelo(
	Id INT PRIMARY KEY AUTO_INCREMENT,
	Motor VARCHAR(100),
	Nombre VARCHAR(100) UNIQUE NOT NULL,
    Activo BOOLEAN NOT NULL

);

CREATE TABLE Avion(
	Id INT PRIMARY KEY AUTO_INCREMENT, 
	Nombre VARCHAR(100), 
	Num_Asientos INT, 
	Matricula VARCHAR(100) UNIQUE NOT NULL, 
	Fecha_Fabricacion VARCHAR(10),
	Id_Hangar INT, 
	Id_Modelo INT, 
	Id_Aerolinea INT,
    Activo BOOLEAN NOT NULL,
	FOREIGN KEY (Id_Hangar) REFERENCES Hangar(Id),
    FOREIGN KEY (Id_modelo) REFERENCES Modelo(Id),
    FOREIGN KEY (Id_aerolinea) REFERENCES Aerolinea(Id)
);

CREATE TABLE Comercial(
	Id_avion INT PRIMARY KEY REFERENCES Avion(Id),
    Empresa VARCHAR(100)
);

CREATE TABLE Privado(
	Id_avion INT PRIMARY KEY REFERENCES Avion(Id),
    Nombre_Duenyo VARCHAR(100),
    Carnet VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE Aerolinea(
	Id INT PRIMARY KEY AUTO_INCREMENT, 
    Nombre varchar(100) UNIQUE NOT NULL,
    Activo BOOLEAN NOT NULL
);

CREATE TABLE Contrato(
	Id INT PRIMARY KEY AUTO_INCREMENT, 
    Precio NUMERIC(12, 2) NOT NULL,
    Id_Aerolinea INT REFERENCES Aerolinea(ID)
);

CREATE TABLE Hangar(
	Id INT PRIMARY KEY AUTO_INCREMENT, 
    Espacio_Almacenaje INT,
    Direccion varchar(100) UNIQUE NOT NULL,
    Coste_Dia NUMERIC(6, 2),
    Stock INT,
    Activo BOOLEAN NOT NULL
);

CREATE TABLE Personal(
	Id INT PRIMARY KEY AUTO_INCREMENT, 
    DNI varchar(9) UNIQUE NOT NULL,
    Area_Asignada varchar(100),
    Activo BOOLEAN NOT NULL
);

CREATE TABLE Personal_Seguridad(
	Id_Personal INT PRIMARY KEY REFERENCES Personal(Id), 
    Placa varchar(100)
);

CREATE TABLE Personal_Limpieza(
	Id_Personal INT PRIMARY KEY REFERENCES Personal(Id), 
    Rol varchar(100)
);
    
CREATE TABLE Linea_Contrato (
    Id_Hangar INT,
    Id_Contrato INT,
    Fecha_Ini VARCHAR(100) NOT NULL,
    Fecha_Fin VARCHAR(100),
    precio NUMERIC(12, 2) NOT NULL,
    FOREIGN KEY (Id_Hangar) REFERENCES Hangar(Id),
    FOREIGN KEY (Id_Contrato) REFERENCES Contrato(Id),
    PRIMARY KEY(Id_Hangar, Id_Contrato)
);

CREATE TABLE Aerolinea_Modelo(
	Id_Aerolinea INT, 
    Id_Modelo INT,
    FOREIGN KEY (Id_Aerolinea) REFERENCES Aerolinea(Id),
    FOREIGN KEY (Id_Modelo) REFERENCES Modelo(Id),
    PRIMARY KEY(Id_Aerolinea, Id_Modelo)
);

CREATE TABLE Personal_Hangar(
	Id_Personal INT, 
    Id_Hangar INT,
    FOREIGN KEY (Id_Personal) REFERENCES Personal(Id),
    FOREIGN KEY (Id_Hangar) REFERENCES Hangar(Id),
    PRIMARY KEY(Id_Personal, Id_Hangar)
);




