-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-05-2025 a las 21:51:54
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestioneventos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artista_banda`
--

CREATE TABLE `artista_banda` (
  `id_artista` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `genero` enum('Rock','Heavy_metal','Pop','Urbano','Salsa') NOT NULL,
  `integrantes` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `artista_banda`
--

INSERT INTO `artista_banda` (`id_artista`, `nombre`, `genero`, `integrantes`) VALUES
(1, 'Los Fabulosos Cadillacs', 'Rock', 'Vicentico, Flavio Cianciarulo, Mario Siperman, entre otros'),
(2, 'DJ Tiësto', '', 'Tiësto'),
(3, 'Luis Miguel', 'Pop', 'Luis Miguel'),
(4, 'Calle 13', 'Urbano', 'Residente, Visitante'),
(5, 'Marc Anthony', 'Salsa', 'Marc Anthony y su banda'),
(6, 'Metallica', '', 'James Hetfield, Lars Ulrich, Kirk Hammett, Robert Trujillo'),
(7, 'Shakira', 'Pop', 'Shakira, productores, y músicos en vivo'),
(8, 'Bad Bunny', 'Urbano', 'Bad Bunny y productores'),
(9, 'Andrea Bocelli', '', 'Andrea Bocelli, músicos clásicos'),
(10, 'Café Tacvba', 'Rock', 'Emmanuel del Real, Rubén Albarrán, Enrique Rangel, Joselo Rangel');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `id_persona`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),
(16, 16),
(17, 17),
(18, 18),
(19, 19),
(20, 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id_empleado` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `cargo` enum('Administrador','Coordinador','Asistente','') NOT NULL,
  `contrato` enum('Termino _Fijo','Indefinido','Prestacion _Servicios') NOT NULL,
  `eps` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id_empleado`, `id_persona`, `cargo`, `contrato`, `eps`) VALUES
(1, 21, 'Administrador', 'Indefinido', 'Sanitas'),
(2, 22, 'Asistente', '', 'Sura'),
(3, 23, 'Coordinador', 'Indefinido', 'Nueva EPS'),
(4, 24, 'Asistente', '', 'Compensar'),
(5, 25, 'Administrador', 'Indefinido', 'Famisanar'),
(6, 26, 'Coordinador', '', 'Salud Total'),
(7, 27, 'Asistente', '', 'Coomeva'),
(8, 28, 'Administrador', 'Indefinido', 'Sanitas'),
(9, 29, 'Coordinador', '', 'Nueva EPS'),
(10, 30, 'Asistente', '', 'Compensar');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evento`
--

CREATE TABLE `evento` (
  `id_evento` int(11) NOT NULL,
  `id_lugar` int(11) NOT NULL,
  `nombre_evento` varchar(50) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `evento`
--

INSERT INTO `evento` (`id_evento`, `id_lugar`, `nombre_evento`, `fecha`) VALUES
(12, 2, 'Noche de Rock y Leyendas', '2025-06-01'),
(23, 5, 'Festival de Música Electrónica en Vivo', '2025-07-01'),
(34, 7, 'Concierto Sinfónico: La Magia de la Orquesta', '2026-06-10'),
(67, 9, 'La Fiesta del Reggaetón', '2026-07-01'),
(76, 6, 'Vibes de Salsa y Bachata', '2026-04-15'),
(87, 1, 'El Gran Concierto de Indie & Alternativo', '2025-09-04'),
(89, 4, 'Ritmos del Mundo: Festival Internacional de Música', '2025-11-23'),
(99, 10, 'Noches de Piano y Arte', '2025-10-14'),
(106, 3, 'Festival de Música Latinoamericana', '2025-08-22'),
(145, 8, 'La Ruta del Rock Alternativo', '2026-05-14');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evento_artista`
--

CREATE TABLE `evento_artista` (
  `id_evento` int(11) NOT NULL,
  `id_artista` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `evento_artista`
--

INSERT INTO `evento_artista` (`id_evento`, `id_artista`) VALUES
(12, 1),
(12, 6),
(23, 2),
(67, 4),
(67, 8),
(76, 5),
(76, 7),
(87, 3),
(89, 9),
(106, 7),
(106, 5),
(99, 9),
(99, 3),
(34, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evento_promotor`
--

CREATE TABLE `evento_promotor` (
  `id_evento` int(11) NOT NULL,
  `id_promotor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `evento_promotor`
--

INSERT INTO `evento_promotor` (`id_evento`, `id_promotor`) VALUES
(12, 1),
(23, 2),
(34, 3),
(67, 4),
(76, 5),
(87, 6),
(89, 7),
(99, 8),
(106, 9),
(23, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lugares`
--

CREATE TABLE `lugares` (
  `id_lugar` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `capacidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `lugares`
--

INSERT INTO `lugares` (`id_lugar`, `nombre`, `direccion`, `capacidad`) VALUES
(1, 'Movistar Arena', 'Diagonal 61c #26-36, Bogotá', 14000),
(2, 'Estadio Atanasio Girardot', 'Carrera 74 #48010, Medellín', 40000),
(3, 'Centro de Eventos Autopista Norte', 'Autopista Norte #153-81, Bogotá', 8000),
(4, 'Estadio Metropolitano', 'Calle 45 #1-50, Barranquilla', 46000),
(5, 'Plaza de Toros La Macarena', 'Carrera 63 #44A-65, Medellín', 15000),
(6, 'Estadio Pascual Guerrero', 'Carrera 34 #5-66, Cali', 35000),
(7, 'Centro de Convenciones Cartagena de Indias', 'Calle 24 #8A-344, Cartagena', 2000),
(8, 'Teatro Jorge Eliécer Gaitán', 'Carrera 7 #22-47, Bogotá', 1700),
(9, 'Parque Simón Bolívar', 'Avenida Calle 63 #68-95, Bogotá', 90000),
(10, 'Coliseo El Pueblo', 'Carrera 52 #2-30, Cali', 18000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id_persona` int(20) NOT NULL,
  `num_documento` varchar(20) NOT NULL,
  `tipo_documento` enum('Cedula de ciudadania','Tarjeta de identidad','Cedula extranjeria','Registro civil') NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `celular` varchar(15) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `direccion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id_persona`, `num_documento`, `tipo_documento`, `nombre`, `apellido`, `celular`, `correo`, `fecha_nacimiento`, `direccion`) VALUES
(1, '1100112233', 'Registro civil', 'Luisa', 'Fernández', '3101000001', 'luisa.fernandez@example.com', '2012-03-05', 'Calle 10 #5-20, Bogotá, Colombia'),
(2, '10293847', 'Tarjeta de identidad', 'Camilo', 'Sánchez', '3101000002', 'camilo.sanchez@example.com', '2007-08-19', 'Carrera 45 #12-34, Medellín, Colombia'),
(3, '123456789', 'Cedula de ciudadania', 'Andrés', 'Pérez', '3101000003', 'andres.perez@example.com', '1990-01-10', 'Calle 33 #22-10, Cali, Colombia'),
(4, '90817263', 'Cedula extranjeria', 'Sophie', 'Dubois', '3101000004', 'sophie.dubois@example.com', '1988-06-22', 'Carrera 7 #8-32, Bogotá, Colombia'),
(5, '11445566', 'Registro civil', 'Mateo', 'Romero', '3101000005', 'mateo.romero@example.com', '2011-11-03', 'Calle 20 #6-44, Cartagena, Colombia'),
(6, '19283746', 'Tarjeta de identidad', 'Valentina', 'Torres', '3101000006', 'valentina.torres@example.com', '2006-05-21', 'Carrera 13 #45-60, Cali, Colombia'),
(7, '987654321', 'Cedula de ciudadania', 'Esteban', 'López', '3101000007', 'esteban.lopez@example.com', '1987-07-15', 'Calle 8 #17-70, Barranquilla, Colombia'),
(8, '76543210', 'Cedula extranjeria', 'Michael', 'Johnson', '3101000008', 'michael.johnson@example.com', '1992-04-09', 'Carrera 6 #11-25, Pereira, Colombia'),
(9, '11998877', 'Registro civil', 'Juliana', 'Cano', '3101000009', 'juliana.cano@example.com', '2013-01-30', 'Calle 29 #3-60, Cúcuta, Colombia'),
(10, '29384756', 'Tarjeta de identidad', 'Daniel', 'Mejía', '3101000010', 'daniel.mejia@example.com', '2005-12-11', 'Carrera 9 #15-44, Manizales, Colombia'),
(11, '246813579', 'Cedula de ciudadania', 'Natalia', 'Gómez', '3101000011', 'natalia.gomez@example.com', '1985-02-17', 'Calle 11 #4-22, Santa Marta, Colombia'),
(12, '33445566', 'Cedula extranjeria', 'Emma', 'Müller', '3101000012', 'emma.muller@example.com', '1991-03-05', 'Carrera 33 #21-19, Bogotá, Colombia'),
(13, '1224488', 'Registro civil', 'Samuel', 'Vargas', '3101000013', 'samuel.vargas@example.com', '2010-06-07', 'Calle 12 #7-30, Medellín, Colombia'),
(14, '11122233', 'Tarjeta de identidad', 'Isabela', 'Salazar', '3101000014', 'isabela.salazar@example.com', '2008-09-14', 'Carrera 20 #10-50, Cali, Colombia'),
(15, '135792468', 'Cedula de ciudadania', 'Cristian', 'Castro', '3101000015', 'cristian.castro@example.com', '1983-10-02', 'Calle 55 #18-33, Bucaramanga, Colombia'),
(16, '55667788', 'Cedula extranjeria', 'Liam', 'Walker', '3101000016', 'liam.walker@example.com', '1989-12-22', 'Carrera 10 #6-12, Bogotá, Colombia'),
(17, '1776655', 'Registro civil', 'Sofía', 'Pineda', '3101000017', 'sofia.pineda@example.com', '2014-07-04', 'Calle 48 #5-99, Armenia, Colombia'),
(18, '45678901', 'Tarjeta de identidad', 'Tomás', 'Guerra', '3101000018', 'tomas.guerra@example.com', '2009-04-27', 'Carrera 18 #3-45, Popayán, Colombia'),
(19, '654321987', 'Cedula de ciudadania', 'Gabriela', 'Hernández', '3101000019', 'gabriela.hernandez@example.com', '1994-01-13', 'Calle 37 #8-20, Neiva, Colombia'),
(20, '99887766', 'Cedula extranjeria', 'Anna', 'Petrova', '3101000020', 'anna.petrova@example.com', '1993-05-09', 'Carrera 29 #14-10, Bogotá, Colombia'),
(21, '1332211', 'Registro civil', 'Iván', 'Londoño', '3101000021', 'ivan.londono@example.com', '2012-10-10', 'Calle 41 #5-21, Cali, Colombia'),
(22, '78945612', 'Tarjeta de identidad', 'Mariana', 'Agudelo', '3101000022', 'mariana.agudelo@example.com', '2007-07-18', 'Carrera 31 #16-77, Barranquilla, Colombia'),
(23, '112233445', 'Cedula de ciudadania', 'Felipe', 'Mendoza', '3101000023', 'felipe.mendoza@example.com', '1986-09-25', 'Calle 61 #10-88, Medellín, Colombia'),
(24, '44556677', 'Cedula extranjeria', 'James', 'Lee', '3101000024', 'james.lee@example.com', '1995-06-16', 'Carrera 7 #30-22, Bogotá, Colombia'),
(25, '112244', 'Registro civil', 'Daniela', 'Córdoba', '3101000025', 'daniela.cordoba@example.com', '2011-02-26', 'Calle 5 #9-90, Ibagué, Colombia'),
(26, '20212223', 'Tarjeta de identidad', 'Alejandro', 'Navas', '3101000026', 'alejandro.navas@example.com', '2008-03-09', 'Carrera 19 #12-60, Pasto, Colombia'),
(27, '987321654', 'Cedula de ciudadania', 'Julián', 'Ortiz', '3101000027', 'julian.ortiz@example.com', '1989-04-01', 'Calle 32 #3-33, Montería, Colombia'),
(28, '66778899', 'Cedula extranjeria', 'Mei', 'Chen', '3101000028', 'mei.chen@example.com', '1992-11-08', 'Carrera 28 #9-50, Bogotá, Colombia'),
(29, '889977', 'Registro civil', 'Cristina', 'Reyes', '3101000029', 'cristina.reyes@example.com', '2013-08-03', 'Calle 19 #6-77, Cali, Colombia'),
(30, '12131415', 'Tarjeta de identidad', 'Lucas', 'Guzmán', '3101000030', 'lucas.guzman@example.com', '2006-06-30', 'Carrera 13 #11-11, Medellín, Colombia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promotor`
--

CREATE TABLE `promotor` (
  `id_promotor` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `tipo` enum('Comercial','Patrocinador','Recintos','') NOT NULL,
  `celular` varchar(50) NOT NULL,
  `correo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `promotor`
--

INSERT INTO `promotor` (`id_promotor`, `nombre`, `tipo`, `celular`, `correo`) VALUES
(1, 'Red Bull', 'Patrocinador', '3101234567', 'eventos@redbull.com'),
(2, 'Coca Cola', 'Patrocinador', '3112345678', 'patrocinios@cokecolombia.com'),
(3, 'Budweiser', 'Patrocinador', '3123456789', 'contacto@budweiser.com'),
(4, 'PepsiCo', 'Patrocinador', '3134567890', 'patrocinios@pepsico.com'),
(5, 'Nike', 'Patrocinador', '3145678901', 'marketing@nike.com'),
(6, 'Sony Music', 'Patrocinador', '3156789012', 'soporte@sonymusic.com'),
(7, 'Heineken', 'Patrocinador', '3167890123', 'contacto@heineken.com'),
(8, 'Spotify', 'Patrocinador', '3178901234', 'patrocinios@spotify.com'),
(9, 'Samsung', 'Patrocinador', '3189012345', 'eventos@samsung.com'),
(10, 'Lexus', 'Patrocinador', '3190123456', 'contact@lexus.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ticket`
--

CREATE TABLE `ticket` (
  `id_ticket` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_evento` int(11) NOT NULL,
  `tipo` enum('General','Vip','Platino','') NOT NULL,
  `precio` float NOT NULL,
  `estado` enum('Disponible','Vendido','','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `ticket`
--

INSERT INTO `ticket` (`id_ticket`, `id_cliente`, `id_evento`, `tipo`, `precio`, `estado`) VALUES
(41, 1, 12, 'General', 50000, 'Vendido'),
(42, 2, 12, 'Vip', 100000, 'Vendido'),
(43, 3, 23, 'General', 50000, 'Vendido'),
(44, 4, 34, 'Platino', 200000, 'Vendido'),
(45, 5, 67, 'Vip', 100000, 'Disponible'),
(46, 6, 76, 'General', 50000, 'Vendido'),
(47, 7, 76, 'Platino', 200000, 'Vendido'),
(48, 8, 87, 'General', 50000, 'Vendido'),
(49, 9, 87, 'Vip', 100000, 'Vendido'),
(50, 10, 89, 'Platino', 200000, 'Vendido'),
(51, 11, 99, 'General', 50000, 'Disponible'),
(52, 12, 99, 'Vip', 100000, 'Vendido'),
(53, 13, 106, 'General', 50000, 'Vendido'),
(54, 14, 106, 'Vip', 100000, 'Vendido'),
(55, 15, 145, 'General', 50000, 'Disponible'),
(56, 16, 145, 'Platino', 200000, 'Vendido'),
(57, 17, 145, 'Vip', 100000, 'Disponible'),
(58, 18, 12, 'General', 50000, 'Vendido'),
(59, 19, 23, 'General', 50000, 'Vendido'),
(60, 20, 23, 'Vip', 100000, 'Vendido');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `artista_banda`
--
ALTER TABLE `artista_banda`
  ADD PRIMARY KEY (`id_artista`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `fk_persona` (`id_persona`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id_empleado`),
  ADD KEY `fk_idpersona` (`id_persona`);

--
-- Indices de la tabla `evento`
--
ALTER TABLE `evento`
  ADD PRIMARY KEY (`id_evento`),
  ADD KEY `fk_lugar` (`id_lugar`);

--
-- Indices de la tabla `evento_artista`
--
ALTER TABLE `evento_artista`
  ADD KEY `id_evento` (`id_evento`),
  ADD KEY `fk_artista` (`id_artista`);

--
-- Indices de la tabla `evento_promotor`
--
ALTER TABLE `evento_promotor`
  ADD KEY `fka_evento` (`id_evento`),
  ADD KEY `fk_promotor` (`id_promotor`);

--
-- Indices de la tabla `lugares`
--
ALTER TABLE `lugares`
  ADD PRIMARY KEY (`id_lugar`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id_persona`),
  ADD UNIQUE KEY `num_documento` (`num_documento`),
  ADD UNIQUE KEY `correo` (`correo`),
  ADD UNIQUE KEY `celular` (`celular`);

--
-- Indices de la tabla `promotor`
--
ALTER TABLE `promotor`
  ADD PRIMARY KEY (`id_promotor`),
  ADD UNIQUE KEY `celular` (`celular`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- Indices de la tabla `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id_ticket`),
  ADD KEY `fk_cliente` (`id_cliente`),
  ADD KEY `fk_evento` (`id_evento`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `artista_banda`
--
ALTER TABLE `artista_banda`
  MODIFY `id_artista` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id_empleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `evento`
--
ALTER TABLE `evento`
  MODIFY `id_evento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=146;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id_persona` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT de la tabla `promotor`
--
ALTER TABLE `promotor`
  MODIFY `id_promotor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id_ticket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `fk_persona` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`);

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `fk_idpersona` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`);

--
-- Filtros para la tabla `evento`
--
ALTER TABLE `evento`
  ADD CONSTRAINT `fk_lugar` FOREIGN KEY (`id_lugar`) REFERENCES `lugares` (`id_lugar`);

--
-- Filtros para la tabla `evento_artista`
--
ALTER TABLE `evento_artista`
  ADD CONSTRAINT `evento_artista_ibfk_1` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`id_evento`),
  ADD CONSTRAINT `fk_artista` FOREIGN KEY (`id_artista`) REFERENCES `artista_banda` (`id_artista`);

--
-- Filtros para la tabla `evento_promotor`
--
ALTER TABLE `evento_promotor`
  ADD CONSTRAINT `fk_promotor` FOREIGN KEY (`id_promotor`) REFERENCES `promotor` (`id_promotor`),
  ADD CONSTRAINT `fka_evento` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`id_evento`);

--
-- Filtros para la tabla `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `fk_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  ADD CONSTRAINT `fk_evento` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`id_evento`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
