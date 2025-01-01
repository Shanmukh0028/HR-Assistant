CREATE DATABASE  IF NOT EXISTS `hr_bot` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hr_bot`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: hr_bot
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `role` enum('HR','EMPLOYEE') NOT NULL,
  `status` enum('ACTIVE','INACTIVE') DEFAULT 'ACTIVE',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'john_doe','$2a$10$HS8wkvSpNiJT3.baNbelAuFBxPZxz3zlLt/Ef4DXIXYvshSNIZXfq','John','Doe','john.doe@example.com','EMPLOYEE','ACTIVE','2024-11-21 13:50:44','2024-11-21 13:50:44'),(5,'john_doe1','$2a$10$mMgD2Kt2NZqSUvaHINo7VOQm2uad1I/yf72qjrto3px.H0h4CdbbW','John','Doe','john.doe1@example.com','EMPLOYEE','ACTIVE','2024-11-21 13:53:01','2024-11-21 13:53:01'),(9,'john_doe3','$2a$10$6Cznc3PA7DPprV9wg4.09erdOeX14Vn2Xmmgq331ZzVNacqFA5hNO','John','Doe','john.doe3@example.com','EMPLOYEE','ACTIVE','2024-11-21 15:05:14','2024-11-21 15:05:14'),(12,'john_doe4','$2a$10$VKLlILKpyBFhdwFSAdkzwe1xY4MWd7.2L3bnvyU130mcZGJktS/IK','John','Doe','john.doe4@example.com','EMPLOYEE','ACTIVE','2024-11-21 15:06:42','2024-11-21 15:06:42'),(13,'john_doe5','$2a$10$ckQNixg/W0EPI1k1wn0fGOdgSusLhVIl005b2t2qzhBc9IVxmCjpu','John','Doe','john.doe5@example.com','EMPLOYEE','ACTIVE','2024-11-21 15:07:03','2024-11-21 15:07:03'),(16,'john_doe6','$2a$10$wuz9TR26k50NZlZ2LwjCJ.aEATJsuSPw/CqM1VO3OGFFcYO83yYYa','John','Doe','john.doe6@example.com','EMPLOYEE','ACTIVE','2024-11-21 15:08:34','2024-11-21 15:08:34'),(18,'john_doe7','$2a$10$/strW59hLNgEGoPLaiVSA.3/47J2zThnExv1kW3BjtbEKuE.vwNQK','John','Doe','john.doe7@example.com','EMPLOYEE','ACTIVE','2024-11-21 15:11:11','2024-11-21 15:11:11'),(19,'john_doe8','$2a$10$NBQih/9LcxGQQCqCAT58Le/33aqFA5EvysaylhABM9fwZD321sSwu','John','Doe','john.doe8@example.com','EMPLOYEE','ACTIVE','2024-11-21 15:11:31','2024-11-21 15:11:31'),(20,'john_doe9','$2a$10$AGz7BjbpIqr8BUCARtFEW.C/F6cnk2PiQ/1sZa5dGQFug84TTClqi','John','Doe','john.doe9@example.com','EMPLOYEE','ACTIVE','2024-11-21 15:27:46','2024-11-21 15:27:46'),(21,'john_doe10','$2a$10$ANgtn1Y7/mIeyyBqxrY7OO3UpyrO0TVte8.vi7W.S8n0pi2807OUO','John','Doe','john.doe10@example.com','EMPLOYEE','ACTIVE','2024-11-21 16:12:53','2024-11-21 16:12:53'),(23,'john_doe11','$2a$10$6ci4bx1ljEx./bLezE1dBeF5RFFrwQ6E/cX/uSIgjtCxkntVavcMi','John','Doe','john.doe11@example.com','EMPLOYEE','ACTIVE','2024-11-21 16:16:01','2024-11-21 16:16:01'),(25,'john_doe12','$2a$10$.0POzSBlU2IEy3p8B3WFcubGqNo.GYy88GGwYTeldTndBkEi8vf8K','John','Doe','john.doe12@example.com','EMPLOYEE','ACTIVE','2024-11-21 16:32:33','2024-11-21 16:32:33'),(26,'john_doe13','$2a$10$c2pjuAwY6ReR9FO2rVU9SuY8QDjTvBDMogJWlH41MaWuRNYn3vLZy','John','Doe','john.doe13@example.com','EMPLOYEE','ACTIVE','2024-11-21 16:34:52','2024-11-21 16:34:52'),(27,'john_doe14','securePassword123','John','Doe','john.doe14@example.com','EMPLOYEE','ACTIVE','2024-11-21 16:51:00','2024-11-21 16:51:00'),(28,'john_doe15','securePassword123','John','Doe','john.doe15@example.com','EMPLOYEE','ACTIVE','2024-11-21 16:52:47','2024-11-21 16:52:47'),(29,'john_doe16','securePassword123','John','Doe','john.doe16@example.com','EMPLOYEE','ACTIVE','2024-11-21 16:55:52','2024-11-21 16:55:52'),(31,'john_doe17','securePassword123','John','Doe','john.doe17@example.com','EMPLOYEE','ACTIVE','2024-11-21 16:59:24','2024-11-21 16:59:24');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-01 15:15:41
