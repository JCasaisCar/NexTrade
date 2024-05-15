DROP DATABASE IF EXISTS nextrade;
CREATE DATABASE IF NOT EXISTS nextrade;

USE nextrade;

-- Estructura de tabla para la tabla "cliente"
CREATE TABLE IF NOT EXISTS cliente (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  dni varchar(9),
  nombre varchar(180),
  telefono varchar(15),
  direccion varchar(255)
);

-- Estructura de tabla para la tabla "configuracion"
CREATE TABLE IF NOT EXISTS configuracion (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  ruc varchar(30),
  nombre varchar(255),
  telefono int,
  direccion varchar(255),
  mensaje varchar(255)
);

-- Datos para la tabla "configuracion"
INSERT INTO configuracion (id, ruc, nombre, telefono, direccion, mensaje) VALUES
(1, 71347267, 'ILERNA Sevilla', 954041212, 'Av. de la Innovación, 7-9, Edificio Arena, 2, 41020 Sevilla', 'Centro de FP');

-- Estructura de tabla para la tabla "proveedor"
CREATE TABLE IF NOT EXISTS proveedor (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  ruc varchar(30),
  nombre varchar(255),
  telefono varchar(15),
  direccion varchar(255)
);

-- Estructura de tabla para la tabla "producto"
CREATE TABLE IF NOT EXISTS producto (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  codigo varchar(20),
  nombre varchar(255),
  proveedor int,
  stock int,
  precio decimal(10,2),
  FOREIGN KEY (proveedor) REFERENCES proveedor(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Estructura de tabla para la tabla "venta"
CREATE TABLE IF NOT EXISTS venta (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  cliente int,
  total decimal(10,2),
  fecha varchar(20),
  FOREIGN KEY (cliente) REFERENCES cliente(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Estructura de tabla para la tabla "detalle"
CREATE TABLE IF NOT EXISTS detalle (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nombreProducto varchar(60),
  cantidad int,
  precioProducto decimal(10,2),
  total decimal(10,2),
  id_venta int
);

-- Estructura de tabla para la tabla "usuario"
CREATE TABLE IF NOT EXISTS usuario (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nombre varchar(200),
  correo varchar(200),
  contrasenia varchar(50)
);

-- Datos para la tabla "usuario"
INSERT INTO usuario (id, nombre, correo, contrasenia) VALUES
(1, 'Admin', 'admin@ilerna.com', 'Admin'),
(2, 'Soraya', 'sgalisteo@ilerna.com', 'Soraya'),
(3, 'Jesus', 'jesus@ilerna.com', 'Jesus'),
(4, 'Daniel', 'daniel@ilerna.com', 'Daniel');


