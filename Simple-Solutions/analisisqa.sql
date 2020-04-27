-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-04-2020 a las 22:38:25
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `analisisqa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyecto`
--

CREATE TABLE `proyecto` (
  `code` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `status` int(11) NOT NULL,
  `language` varchar(20) NOT NULL,
  `duration` int(11) NOT NULL,
  `advance` int(11) NOT NULL,
  `effec` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proyecto`
--

INSERT INTO `proyecto` (`code`, `name`, `status`, `language`, `duration`, `advance`, `effec`) VALUES
(1, 'Sistem Plan Empresa', 0, 'PHP', 4, 0, 1170),
(2, 'Cumplimiento web', 2, 'PHP', 20, 4, 0),
(3, 'Simple Solutions', 0, 'PHP', 4, 15, 3648.26),
(4, 'Proyecto', 0, 'PHP', 4, 10, 2646.21);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prueba`
--

CREATE TABLE `prueba` (
  `codeTest` int(11) NOT NULL,
  `description` varchar(30) NOT NULL,
  `typeTest` int(11) NOT NULL,
  `countCases` int(11) NOT NULL,
  `casesFail` double NOT NULL,
  `timeResponse` double NOT NULL,
  `speed` double NOT NULL,
  `efficTest` double NOT NULL,
  `id_version` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `prueba`
--

INSERT INTO `prueba` (`codeTest`, `description`, `typeTest`, `countCases`, `casesFail`, `timeResponse`, `speed`, `efficTest`, `id_version`) VALUES
(1, 'Prueba funcional', 0, 4, 2, 22, 5.5, 430, 1),
(2, 'Visuales', 1, 8, 1, 20, 2.5, 740, 1),
(3, 'Test A', 1, 25, 10, 1.01, 0.04, 895.96, 3),
(4, 'Prueba B', 2, 50, 2, 1.5, 0.03, 977, 3),
(5, 'Test C', 1, 4, 1, 0.5, 0.12, 977.5, 3),
(6, 'Test D', 3, 50, 20, 1.1, 0.02, 797.8, 4),
(7, 'Test A', 0, 100, 0, 1.098, 0.01, 998.9, 5),
(8, 'Test B', 0, 78, 20, 2.1, 0.03, 797.31, 5),
(9, 'Test X', 3, 2, 10, 1, 0.5, 850, 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `version`
--

CREATE TABLE `version` (
  `idVersion` int(11) NOT NULL,
  `nameVersion` varchar(20) NOT NULL,
  `efficVersion` double NOT NULL,
  `acumCases` int(11) NOT NULL,
  `acumFail` double NOT NULL,
  `acumTime` double NOT NULL,
  `contPruebas` int(11) NOT NULL,
  `codeProject` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `version`
--

INSERT INTO `version` (`idVersion`, `nameVersion`, `efficVersion`, `acumCases`, `acumFail`, `acumTime`, `contPruebas`, `codeProject`) VALUES
(1, 'v1.2', 1170, 12, 3, 42, 2, 1),
(2, '34', 0, 0, 0, 0, 0, 2),
(3, 'v0.1', 2850.46, 79, 13, 3.01, 3, 3),
(4, 'v0.2', 797.8, 50, 20, 1.1, 1, 3),
(5, 'v1.0.0', 1796.21, 178, 20, 3.1980000000000004, 2, 4),
(6, 'v0.3', 0, 0, 0, 0, 0, 3),
(7, 'v1.0.1', 850, 2, 10, 1, 1, 4);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `proyecto`
--
ALTER TABLE `proyecto`
  ADD PRIMARY KEY (`code`);

--
-- Indices de la tabla `prueba`
--
ALTER TABLE `prueba`
  ADD PRIMARY KEY (`codeTest`),
  ADD KEY `FK codeProject` (`id_version`);

--
-- Indices de la tabla `version`
--
ALTER TABLE `version`
  ADD PRIMARY KEY (`idVersion`),
  ADD KEY `FK codeProject` (`codeProject`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `proyecto`
--
ALTER TABLE `proyecto`
  MODIFY `code` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `prueba`
--
ALTER TABLE `prueba`
  MODIFY `codeTest` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `version`
--
ALTER TABLE `version`
  MODIFY `idVersion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `prueba`
--
ALTER TABLE `prueba`
  ADD CONSTRAINT `prueba_ibfk_1` FOREIGN KEY (`id_version`) REFERENCES `version` (`idVersion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `version`
--
ALTER TABLE `version`
  ADD CONSTRAINT `version_ibfk_1` FOREIGN KEY (`codeProject`) REFERENCES `proyecto` (`code`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
