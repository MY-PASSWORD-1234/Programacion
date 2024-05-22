CREATE DATABASE  IF NOT EXISTS `SuperCoches` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `SuperCoches`;
-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: SuperCoches
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Coches`
--

DROP TABLE IF EXISTS `Coches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Coches` (
  `idCoches` int NOT NULL AUTO_INCREMENT,
  `Marca` varchar(45) NOT NULL,
  `Modelo` varchar(45) NOT NULL,
  `Puertas` int NOT NULL,
  `Combustible` varchar(45) NOT NULL,
  `Kilometraje` int NOT NULL,
  `Precio` int NOT NULL,
  `CV` int NOT NULL,
  `Año` varchar(45) NOT NULL,
  `Descripcion` varchar(300) NOT NULL,
  `Estado` varchar(45) NOT NULL DEFAULT 'Disponible',
  `Img` varchar(300) DEFAULT NULL,
  `idUsuario` int DEFAULT NULL,
  PRIMARY KEY (`idCoches`),
  UNIQUE KEY `Modelo_UNIQUE` (`Modelo`),
  KEY `fk_Coches_1_idx` (`idUsuario`),
  CONSTRAINT `fk_Coches_1` FOREIGN KEY (`idUsuario`) REFERENCES `Usuarios` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Coches`
--

LOCK TABLES `Coches` WRITE;
/*!40000 ALTER TABLE `Coches` DISABLE KEYS */;
INSERT INTO `Coches` VALUES (1,'Peugeot','308',5,'Diesel',20000,30000,95,'2006','Rapido y veloz','Disponible','src/main/resources/com/ejemplo/img/1200px-Peugeot_308_5-Türer_front-1.png',NULL),(2,'Mercedes-Benz','Clase S',5,'Gasolina',200000,60000,350,'2021','Lujo y tecnología avanzada','Reservado','src/main/resources/com/ejemplo/img/mercedes-benz-clase-s.png',3),(3,'Ferrari','488 GTB',3,'Gasolina',10000,250000,670,'2022','Potencia y estilo italiano','Reservado','src/main/resources/com/ejemplo/img/ferrari-488-gtb-1.png',2),(4,'Lamborghini','Huracán',3,'Gasolina',8000,300000,640,'2022','Potencia y diseño aerodinámico','Disponible','src/main/resources/com/ejemplo/img/lambo.png',NULL),(5,'Porsche','911',3,'Gasolina',12000,180000,450,'2021','Icono deportivo con ingeniería alemana','Reservado','src/main/resources/com/ejemplo/img/porche-911.png',2),(6,'Land Rover','Range Rover Sport',5,'Diesel',30000,70000,300,'2020','SUV de lujo con capacidades todoterreno','Disponible','src/main/resources/com/ejemplo/img/lanrover-sport.png',NULL),(7,'Aston Martin','DB11',3,'Gasolina',10000,250000,600,'2021','Elegancia británica y potencia de motor V12','Disponible','src/main/resources/com/ejemplo/img/astonmartin-DB11.png',NULL),(8,'McLaren','720S',3,'Gasolina',8000,300000,710,'2022','Innovación aerodinámica y rendimiento extremo','Disponible','src/main/resources/com/ejemplo/img/mclaren-720s.png',NULL),(9,'Rolls-Royce','Phantom',5,'Gasolina',5000,400000,563,'2024','Limusina de lujo con artesanía excepcional y comodidades de primera clase.','Reservado','src/main/resources/com/ejemplo/img/rolls-royce-phantom.png',2);
/*!40000 ALTER TABLE `Coches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Recibo`
--

DROP TABLE IF EXISTS `Recibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Recibo` (
  `idRecibo` int NOT NULL AUTO_INCREMENT,
  `idCoche` int NOT NULL,
  `idUsuario` int NOT NULL,
  `Precio_Base` int NOT NULL,
  `Iva` int NOT NULL,
  `Cambio_Nombre` int NOT NULL,
  `Precio_Final` int NOT NULL,
  `Fecha` varchar(45) NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
  PRIMARY KEY (`idRecibo`),
  KEY `fk_Recibo_1_idx` (`idUsuario`),
  KEY `fk_Recibo_2_idx` (`idCoche`),
  CONSTRAINT `fk_Recibo_1` FOREIGN KEY (`idUsuario`) REFERENCES `Usuarios` (`idUsuario`),
  CONSTRAINT `fk_Recibo_2` FOREIGN KEY (`idCoche`) REFERENCES `Coches` (`idCoches`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Recibo`
--

LOCK TABLES `Recibo` WRITE;
/*!40000 ALTER TABLE `Recibo` DISABLE KEYS */;
/*!40000 ALTER TABLE `Recibo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuarios`
--

DROP TABLE IF EXISTS `Usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Usuarios` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Contraseña` varchar(45) NOT NULL,
  `Dni` varchar(9) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `idUsuario_UNIQUE` (`idUsuario`),
  UNIQUE KEY `Dni_UNIQUE` (`Dni`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuarios`
--

LOCK TABLES `Usuarios` WRITE;
/*!40000 ALTER TABLE `Usuarios` DISABLE KEYS */;
INSERT INTO `Usuarios` VALUES (1,'admin','e/YrZHhClJYoL3CFrAftsQ==','75552216C'),(2,'alex','8U8ZdxsyiATvLaJ6eHIq4Q==','12345678C'),(3,'jose','Cggyhq/V0CnKIt0I7YQC3A==','12345678J'),(4,'pepe','Iki79pnDMeqrrWvTaYqjLA==','12345678V'),(5,'tet','8U8ZdxsyiATvLaJ6eHIq4Q==','12345678T');
/*!40000 ALTER TABLE `Usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-22 19:27:17
