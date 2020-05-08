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

--
-- Table structure for table `bilete`
--

DROP TABLE IF EXISTS `bilete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bilete` (
  `nrBilet` int NOT NULL,
  `plecare` varchar(45) DEFAULT NULL,
  `sosire` varchar(45) DEFAULT NULL,
  `data` varchar(45) DEFAULT NULL,
  `pret` int DEFAULT NULL,
  `isVandut` tinyint DEFAULT NULL,
  PRIMARY KEY (`nrBilet`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bilete`
--

LOCK TABLES `bilete` WRITE;
/*!40000 ALTER TABLE `bilete` DISABLE KEYS */;
INSERT INTO `bilete` VALUES (9871,'Cluj-Napoca','Corfu','15.07.2020',780,1),(9872,'Bucharest','Paris','21.08.2020',600,1),(9873,'Cluj-Napoca','Amsterdam','05.11.2020',600,1),(9874,'Cluj-Napoca','Rome','23.12.2020',120,1),(9875,'Cluj-Napoca','Rome','12.09.2020',120,1),(9876,'Cluj-Napoca','Amsterdam','03.11.2020',600,0);
/*!40000 ALTER TABLE `bilete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calatorbilete`
--

DROP TABLE IF EXISTS `calatorbilete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calatorbilete` (
  `persID` int NOT NULL,
  `nrBilet` int NOT NULL,
  PRIMARY KEY (`persID`,`nrBilet`),
  KEY `nrBilet_FK_idx` (`nrBilet`),
  CONSTRAINT `nrBilet_FK` FOREIGN KEY (`nrBilet`) REFERENCES `bilete` (`nrBilet`),
  CONSTRAINT `persIDcalator_FK` FOREIGN KEY (`persID`) REFERENCES `calatori` (`persID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calatorbilete`
--

LOCK TABLES `calatorbilete` WRITE;
/*!40000 ALTER TABLE `calatorbilete` DISABLE KEYS */;
INSERT INTO `calatorbilete` VALUES (1,9871),(2,9872),(2,9873),(3,9874),(1,9875);
/*!40000 ALTER TABLE `calatorbilete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calatori`
--

DROP TABLE IF EXISTS `calatori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calatori` (
  `persID` int NOT NULL,
  `contBancar` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`persID`),
  CONSTRAINT `persID_FK` FOREIGN KEY (`persID`) REFERENCES `persoane` (`persID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calatori`
--

LOCK TABLES `calatori` WRITE;
/*!40000 ALTER TABLE `calatori` DISABLE KEYS */;
INSERT INTO `calatori` VALUES (1,'4587965214'),(2,'6521357894'),(3,'2546988545');
/*!40000 ALTER TABLE `calatori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conturi`
--

DROP TABLE IF EXISTS `conturi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conturi` (
  `contID` int NOT NULL,
  `userName` varchar(45) NOT NULL,
  `parola` varchar(45) NOT NULL,
  PRIMARY KEY (`contID`),
  UNIQUE KEY `userName_UNIQUE` (`contID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conturi`
--

LOCK TABLES `conturi` WRITE;
/*!40000 ALTER TABLE `conturi` DISABLE KEYS */;
INSERT INTO `conturi` VALUES (1,'first','pass1'),(2,'second','pass2'),(3,'third','pass3'),(4,'fourth','pass4'),(5,'fifth','pass5'),(7,'sad123','tre');
/*!40000 ALTER TABLE `conturi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persoane`
--

DROP TABLE IF EXISTS `persoane`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persoane` (
  `persID` int NOT NULL,
  `nume` varchar(45) NOT NULL,
  `varsta` int NOT NULL,
  `adresa` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `nrTel` varchar(45) NOT NULL,
  PRIMARY KEY (`persID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persoane`
--

LOCK TABLES `persoane` WRITE;
/*!40000 ALTER TABLE `persoane` DISABLE KEYS */;
INSERT INTO `persoane` VALUES (1,'Popa George',22,'New Street 7.','pg@mail.com','0764587895'),(2,'Craciun Bogdan',21,'New Street 8.','cr@mail.com','0745891235'),(3,'Pop Maria',26,'New Street 9.','pm@mail.com','0735879451'),(4,'Pentek Tamas',22,'New Street 2.','email@email.com','0758954123'),(5,'Pop Bogdan',28,'New Street 3.','email2@email.com','0742158964'),(6,'Popescu Ioan',25,'New Street 4.','email3@email.com','0785162669'),(7,'Moldovan Adelina',21,'New Street 5.','email4@email.com','0765134697'),(8,'Muresan Stefan',27,'New Street 6.','email5@email.com','0768951458'),(10,'wqe',54,'New Address','email@email2.com','32456');
/*!40000 ALTER TABLE `persoane` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilizatori`
--

DROP TABLE IF EXISTS `utilizatori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilizatori` (
  `persID` int NOT NULL,
  `tip` varchar(45) DEFAULT NULL,
  `contID` int DEFAULT NULL,
  PRIMARY KEY (`persID`),
  KEY `contID_FK_idx` (`contID`),
  CONSTRAINT `contID_FK` FOREIGN KEY (`contID`) REFERENCES `conturi` (`contID`),
  CONSTRAINT `persIDutilizator_FK` FOREIGN KEY (`persID`) REFERENCES `persoane` (`persID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilizatori`
--

LOCK TABLES `utilizatori` WRITE;
/*!40000 ALTER TABLE `utilizatori` DISABLE KEYS */;
INSERT INTO `utilizatori` VALUES (4,'Administrator',1),(5,'Administrator',2),(6,'Employee',3),(7,'Employee',4),(8,'Employee',5),(10,'Employee',7);
/*!40000 ALTER TABLE `utilizatori` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-08  9:49:10
