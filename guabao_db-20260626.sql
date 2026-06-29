-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: guabao_db
-- ------------------------------------------------------
-- Server version	8.0.46

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
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `member_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '會員卡號(通常為手機號碼)',
  `total_points` int NOT NULL DEFAULT '0' COMMENT '累積點數',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '註冊時間',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='會員資料表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES ('A01',2,'2026-06-25 06:51:41','2026-06-26 09:05:48'),('A02',7,'2026-06-25 08:08:17','2026-06-26 07:40:38'),('A03',6,'2026-06-25 08:08:57','2026-06-26 09:10:23'),('A04',8,'2026-06-25 09:20:33','2026-06-26 07:41:47');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `detail_id` int NOT NULL AUTO_INCREMENT COMMENT '明細自動編號',
  `order_id` int NOT NULL COMMENT '所屬訂單編號',
  `item_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名稱(如：綜合割包、八寶湯)',
  `unit_price` int NOT NULL COMMENT '單價',
  `quantity` int NOT NULL COMMENT '購買數量',
  `subtotal` int NOT NULL COMMENT '小計金額',
  PRIMARY KEY (`detail_id`),
  KEY `fk_detail_order` (`order_id`),
  CONSTRAINT `fk_detail_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='訂單明細表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (1,1,'綜合割包',70,1,70),(2,1,'赤肉割包',70,1,70),(3,1,'焢肉割包',70,1,70),(4,1,'魚丸湯',55,1,55),(5,1,'貢丸湯',55,1,55),(6,1,'八寶湯',80,1,80),(7,2,'綜合割包',70,1,70),(8,2,'赤肉割包',70,1,70),(9,2,'焢肉割包',70,1,70),(10,2,'魚丸湯',55,1,55),(11,2,'貢丸湯',55,1,55),(12,2,'八寶湯',80,1,80),(13,3,'綜合割包',70,2,140),(14,3,'赤肉割包',70,2,140),(15,3,'魚丸湯',55,2,110),(16,3,'八寶湯',80,2,160),(17,4,'綜合割包',70,1,70),(18,4,'赤肉割包',70,2,140),(19,4,'焢肉割包',70,1,70),(20,4,'魚丸湯',55,1,55),(21,4,'貢丸湯',55,2,110),(22,4,'八寶湯',80,1,80),(23,5,'綜合割包',70,1,70),(24,5,'赤肉割包',70,1,70),(25,5,'焢肉割包',70,1,70),(26,5,'魚丸湯',55,1,55),(27,5,'貢丸湯',55,1,55),(28,5,'八寶湯',80,1,80),(29,6,'綜合割包',70,3,210),(30,6,'赤肉割包',70,3,210),(31,6,'焢肉割包',70,3,210),(32,6,'魚丸湯',55,3,165),(33,6,'貢丸湯',55,3,165),(34,6,'八寶湯',80,3,240),(35,7,'綜合割包',70,2,140),(36,7,'魚丸湯',55,1,55),(37,7,'貢丸湯',55,1,55),(38,8,'綜合割包',70,1,70),(39,8,'焢肉割包',70,1,70),(40,8,'貢丸湯',55,1,55),(41,9,'赤肉割包',70,1,70),(42,9,'魚丸湯',55,1,55),(43,9,'八寶湯',80,1,80),(44,10,'綜合割包',70,1,70),(45,10,'赤肉割包',70,1,70),(46,10,'焢肉割包',70,1,70),(47,10,'魚丸湯',55,1,55),(48,10,'貢丸湯',55,1,55),(49,10,'八寶湯',80,1,80),(50,11,'綜合割包',70,1,70),(51,11,'赤肉割包',70,1,70),(52,11,'焢肉割包',70,1,70),(53,11,'魚丸湯',55,1,55),(54,11,'貢丸湯',55,1,55),(55,11,'八寶湯',80,1,80),(56,12,'綜合割包',70,1,70),(57,12,'赤肉割包',70,1,70),(58,12,'焢肉割包',70,1,70),(59,12,'魚丸湯',55,1,55),(60,12,'貢丸湯',55,1,55),(61,12,'八寶湯',80,1,80),(62,13,'赤肉割包',70,1,70),(63,13,'魚丸湯',55,1,55),(64,13,'八寶湯',80,1,80),(65,14,'綜合割包',70,1,70),(66,14,'焢肉割包',70,1,70),(67,14,'魚丸湯',55,1,55),(68,14,'貢丸湯',55,1,55),(69,15,'綜合割包',70,2,140),(70,15,'赤肉割包',70,2,140),(71,15,'焢肉割包',70,2,140),(72,15,'魚丸湯',55,2,110),(73,15,'貢丸湯',55,2,110),(74,15,'八寶湯',80,2,160),(75,16,'綜合割包',70,1,70),(76,16,'焢肉割包',70,1,70),(77,16,'貢丸湯',55,1,55),(78,17,'綜合割包',70,1,70),(79,18,'綜合割包',70,1,70),(80,18,'赤肉割包',70,1,70),(81,18,'焢肉割包',70,1,70),(82,18,'魚丸湯',55,1,55),(83,18,'貢丸湯',55,1,55),(84,18,'八寶湯',80,1,80),(85,19,'綜合割包',70,1,70),(86,19,'赤肉割包',70,1,70),(87,19,'焢肉割包',70,1,70),(88,19,'魚丸湯',55,1,55),(89,19,'貢丸湯',55,1,55),(90,19,'八寶湯',80,1,80),(91,20,'綜合割包',70,1,70),(92,20,'焢肉割包',70,1,70),(93,20,'魚丸湯',55,1,55),(94,20,'貢丸湯',55,1,55);
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '訂單自動編號',
  `member_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '購買會員卡號(一般顧客則為NULL)',
  `total_amount` int NOT NULL COMMENT '訂單總金額',
  `earned_points` int NOT NULL DEFAULT '0' COMMENT '本次消費獲得點數',
  `gift_count` int NOT NULL DEFAULT '0' COMMENT '本次兌換贈送的割包數量',
  `order_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '結帳時間',
  PRIMARY KEY (`order_id`),
  KEY `fk_order_member` (`member_id`),
  CONSTRAINT `fk_order_member` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='訂單主檔表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'A01',400,4,1,'2026-06-25 06:53:24'),(2,'A01',400,4,0,'2026-06-25 06:56:24'),(3,'A01',550,5,1,'2026-06-25 06:59:29'),(4,'A03',525,5,0,'2026-06-25 08:11:29'),(5,NULL,400,4,0,'2026-06-25 08:27:53'),(6,NULL,1200,12,0,'2026-06-25 08:29:51'),(7,'A02',250,2,0,'2026-06-25 08:35:01'),(8,'A02',195,1,0,'2026-06-25 08:49:14'),(9,'A02',205,2,0,'2026-06-25 08:50:59'),(10,'A03',400,4,1,'2026-06-25 15:24:48'),(11,NULL,400,4,0,'2026-06-26 07:36:11'),(12,'A01',400,4,0,'2026-06-26 07:37:37'),(13,'A02',205,2,0,'2026-06-26 07:40:38'),(14,'A03',250,2,0,'2026-06-26 07:41:15'),(15,'A04',800,8,0,'2026-06-26 07:41:47'),(16,'A01',195,1,0,'2026-06-26 08:03:36'),(17,'A01',70,0,0,'2026-06-26 08:18:35'),(18,NULL,400,4,0,'2026-06-26 09:05:19'),(19,'A01',400,4,1,'2026-06-26 09:05:48'),(20,'A03',250,2,0,'2026-06-26 09:10:23');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-26 18:04:44
