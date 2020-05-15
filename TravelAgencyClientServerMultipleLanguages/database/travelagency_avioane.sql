-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: travelagency
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `avioane`
--

DROP TABLE IF EXISTS `avioane`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avioane` (
  `nrAvion` int NOT NULL,
  `nrLocuri` int DEFAULT NULL,
  `tip` varchar(45) DEFAULT NULL,
  `liniaAeriana` varchar(45) DEFAULT NULL,
  `durataZborului` int DEFAULT NULL,
  `plecare` varchar(45) DEFAULT NULL,
  `sosire` varchar(45) DEFAULT NULL,
  `pretBilet` int DEFAULT NULL,
  PRIMARY KEY (`nrAvion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avioane`
--

LOCK TABLES `avioane` WRITE;
/*!40000 ALTER TABLE `avioane` DISABLE KEYS */;
INSERT INTO `avioane` VALUES (1234,119,'Charter','Lufthansa',120,'Cluj-Napoca','Corfu',780),(1235,148,'Low Cost','Wizzair',90,'Cluj-Napoca','Rome',120),(1236,200,'Low Cost','Ryanair',170,'Budapest','London',240),(1237,150,'Low Cost','WizzAir',100,'Cluj-Napoca','Munich',340),(1238,174,'Charter','KLM',170,'Cluj-Napoca','Amsterdam',600),(1239,120,'Charter','Wizzair',100,'Bucharest','Amsterdam',600),(1240,149,'Low Cost','Wizzair',170,'Bucharest','Paris',900);
/*!40000 ALTER TABLE `avioane` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-08  9:44:25
