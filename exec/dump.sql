-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: db-sodam.cj557j0pfgbg.ap-northeast-2.rds.amazonaws.com    Database: sodam
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `certificate`
--

DROP TABLE IF EXISTS `certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `certificate` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `agency` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `serial_num` varchar(30) DEFAULT NULL,
  `counselor_id` varchar(30) DEFAULT NULL,
  `photo` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcusrmwmit1kws5p9qulcupwrf` (`counselor_id`),
  KEY `FK3vsijse2v056vqsdo2w4q2dsh` (`photo`),
  CONSTRAINT `FK3vsijse2v056vqsdo2w4q2dsh` FOREIGN KEY (`photo`) REFERENCES `profile_photo` (`id`),
  CONSTRAINT `FKcusrmwmit1kws5p9qulcupwrf` FOREIGN KEY (`counselor_id`) REFERENCES `counselor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificate`
--

LOCK TABLES `certificate` WRITE;
/*!40000 ALTER TABLE `certificate` DISABLE KEYS */;
INSERT INTO `certificate` VALUES (5,'상담협회','청소년상담','111','counselor01',11);
/*!40000 ALTER TABLE `certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` varchar(30) NOT NULL,
  `common_code_id` varchar(3) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `profile_img` varchar(200) DEFAULT NULL,
  `tel` varchar(50) DEFAULT NULL,
  `groop_code_id` varchar(3) DEFAULT NULL,
  `refresh_token` varchar(200) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `gender` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES ('admin','0','mauv2sky@gmail.com','관리자','$2a$10$JhxLZJdxzJUmivsZd07p0uGRCUo.SbPIN/tmCzhfkBc3WcdtNKUee',NULL,NULL,NULL,NULL,-1,'N'),('admin_coach','0','admin_coach@mail.com','admin_coach','$2a$10$gHbrNKkcq71PauFsCQxY2eKpZ.3ZP9XHvZMAZ1kUrOcQKhkFlFQiW',NULL,'01053502032',NULL,NULL,NULL,NULL),('admin_con','0','dsajklgja@mail.com','binu','$2a$10$wnWbKoWzV7D9A1RfW6a5Kua1KCXUXJYxuyt7kJestEHffbFSkoPoy',NULL,'01053402032',NULL,NULL,NULL,NULL),('client','2','qweqwerqwer','김이현','$2a$10$4zvAnc0QDqijRKjNGaTQi.QqZPrm2wMoAalTzh.Kdgr4iEq8O5YoC','https://storage.googleapis.com/stt-bucket-binu/profile/2023/FEBRUARY/972a02ae-7e6b-4f2e-8823-6d1a6306892a_loginImage.jpg','123412341234',NULL,NULL,-1,'N'),('client_con','2','dsajklsdgaggja@mail.com','client_con','$2a$10$Dt/MM7VZWOvylD6IpKZ/s.06fMrGsKKX9gk9nObcysGA1isQ6SvXm',NULL,'01053402032',NULL,NULL,NULL,NULL),('coach','2','eagfdl@mail.com','coach','$2a$10$MRtt8joKiIq.KI8i84tLZuPE.VRFOJ0mZUvVQKR9k/PW0OnGQAOO2',NULL,'01053402032',NULL,NULL,NULL,NULL),('dkgus','2','dkgus816@naver.com','김안녕','$2a$10$7brSKQTKvLXMx3HvvHtfUuyYWM.3QCGhV2e8Ufn/nCaIVc1VoQR1m',NULL,'010222233333',NULL,NULL,-1,'N'),('id','2','bnds@gmail.com','name','$2a$10$x6Xv2E92uwQYov31f5SVSeCXw52M8c2CdysJRwaELIpopskwT9Boy',NULL,'01053402032',NULL,NULL,3,''),('idd','2','dd@ddd0d.com','김안녕','$2a$10$4/Ht8gsHQVcTBRmP7BLa/.LzkdHxyxJPdlh9UUtPp7eZfo0/F1cQ2',NULL,'7770',NULL,NULL,4,'N'),('iiiiiiiiiii0','2','dasgadfggnhdfgg2dfd','제ㅔ발','$2a$10$Qj1MaFEIKcUAxj4ol4BLDeO0RnefHWy8kvDWPzyZfQB648LlNU1ZO',NULL,'3423423234',NULL,NULL,-1,'N'),('jh01','2','wogns8123@naver.com','장재훈','$2a$10$uodbTDCj7I3U18Lnhh1xiOBUDGiCjTXYkRcE353JlZBDBBlcB46VC',NULL,'0103333333',NULL,NULL,NULL,NULL),('kimah','2','pooha16@gmail.com',NULL,'$2a$10$7Q7arrqsxefAGyWAqSzey.1KXgrikAR8ONbPhvJ0c/IyVmK2udYaS',NULL,'01022222222',NULL,NULL,-1,'N'),('kimkim2','2','eemmail@main.com','김비누','$2a$10$GfocfMAMQ6NOVuDlq1INzO8zB53KkLuR2TzrVXlyGKOLlUXOy1E2W',NULL,'01022223333',NULL,NULL,NULL,NULL),('newClient','2','emdfghhail@mail.com','binu','$2a$10$lFtL1k5t8g1MrrH24SdbA.LV6uFjQ4UkaL24Uw.OI0fu4q3FD1lHW',NULL,'01053402032',NULL,NULL,NULL,NULL),('newClient2','2','emdf33ghhail@mail.com','binu','$2a$10$xOQ.SdVbDOPCBMZO900Gjuz.rWTrWbet673YBsL3YVV06GwgPkNe6',NULL,'01053402032',NULL,NULL,1,''),('newClient33','2','emdflkkgsdghhail@mail.com','binu','$2a$10$.y9Md2KTLoo6TM3IeUIm1eNpMvwVeqvamAVGw1QLMjtSJGgypJhgC',NULL,'01053402032',NULL,NULL,1,'M'),('qweqweqwe','2','qweqweqwe','qweqweqwe','$2a$10$GkBwb0ntVDzaQbqkp54Jfu.eDi1eVMk/XlJl2pyD2marWXdgX7jUW',NULL,'123123123',NULL,NULL,-1,'N'),('sdf','2','asddf@sdagcd.ds','sdafg','$2a$10$d0Ptgkr6vQ2kln5y6BuvsOg3xkQdJG6wk.TKA2ZavFq6hwN3cbjOC',NULL,'235425',NULL,NULL,NULL,NULL),('test2','2','asd','test2','$2a$10$XiR9eZ2sbXmtxAGk//lrwOWXI78M.NemsywzIuR78AZpNdaDWFk26',NULL,NULL,NULL,NULL,NULL,NULL),('user11','2','test1230@naver.com','유저이름','$2a$10$FsXk/0UXEolspn0YI7ZoGuPNOkdPSQncx2ATX5YDHJzK32rp2DSyW','string','01023234545',NULL,NULL,NULL,NULL),('yaya','2','string@naver.com','string','$2a$10$gZZ2ZDwK6Ae9V2R1DD6OnuvRqRpb0/lkaLOKX3zRZswp7gwpNUgw2','string','string',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consult_applicant`
--

DROP TABLE IF EXISTS `consult_applicant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consult_applicant` (
  `session_id` int NOT NULL,
  `age` int DEFAULT NULL,
  `consult_type` int DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_date_time` datetime DEFAULT NULL,
  `due_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `is_consult` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `state` int DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `turn` int DEFAULT NULL,
  `apply_datetime` datetime DEFAULT NULL,
  `day_time` datetime DEFAULT NULL,
  `first_day_time` datetime DEFAULT NULL,
  PRIMARY KEY (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consult_applicant`
--

LOCK TABLES `consult_applicant` WRITE;
/*!40000 ALTER TABLE `consult_applicant` DISABLE KEYS */;
INSERT INTO `consult_applicant` VALUES (1,0,11,'string','2023-02-01 01:47:36','2023-01-30 23:02:39','string','M','N','string',0,'string',0,NULL,NULL,NULL),(10,0,5,'string','2023-02-02 07:19:37','2023-02-01 21:45:56','string','M','N','string',0,'string',0,NULL,NULL,NULL),(21,0,5,'string','2023-02-03 07:46:28','2023-02-02 22:16:53','string','M','N','string',1,'string',0,NULL,NULL,NULL),(22,0,5,'string','2023-02-03 07:47:00','2023-02-02 22:16:53','string','M','N','string',1,'string',0,NULL,NULL,NULL),(23,123,3,'123','2023-02-03 07:47:42','2023-02-02 19:37:27','정재훈','M','N','정재훈',0,'123',0,NULL,NULL,NULL),(24,29,1,'AXIOS','2023-02-03 07:49:25','2023-02-02 19:37:27','wo','W','N','성공',0,'10',0,NULL,NULL,NULL),(25,3,1,'ad','2023-02-03 08:01:40','2023-02-02 19:37:27','공통프로젝트','W','N','정재훈',0,'1',0,NULL,NULL,NULL),(26,3,1,'ad','2023-02-03 08:02:30','2023-02-02 19:37:27','공통프로젝트','W','N','정재훈',0,'1',0,NULL,NULL,NULL),(27,0,5,'string','2023-02-03 08:05:50','2023-02-02 22:16:53','string','M','N','string',1,'string',0,NULL,NULL,NULL),(28,0,5,'string','2023-02-03 08:06:39','2023-02-02 22:16:53','string','M','N','string',1,'string',0,NULL,NULL,NULL),(29,123,11,'ㅣㅏ멍ㄹ','2023-02-03 08:08:24','2023-02-02 19:37:27','안됨?','M','N','왜',0,'123',0,NULL,NULL,NULL),(30,12,2,'123','2023-02-03 08:09:39','2023-02-02 19:37:27','정재훈','M','N','정재훈',0,'123',0,NULL,NULL,NULL),(36,23,2,'1qwe','2023-02-04 06:03:28','2023-02-02 19:37:27','wer','M','N','wd',0,'123',0,NULL,NULL,NULL),(37,12,11,'123','2023-02-04 06:06:07','2023-02-02 19:37:27','wog','M','N','뭐',1,'1023',0,NULL,NULL,NULL),(38,12,11,'123','2023-02-04 06:06:08','2023-02-02 19:37:27','wog','M','N','뭐',0,'1023',0,NULL,NULL,NULL),(39,12,11,'123','2023-02-04 06:06:08','2023-02-02 19:37:27','wog','M','N','뭐',0,'1023',0,NULL,NULL,NULL),(40,12,11,'123','2023-02-04 06:06:09','2023-02-02 19:37:27','wog','M','N','뭐',0,'1023',0,NULL,NULL,NULL),(41,12,11,'123','2023-02-04 06:06:09','2023-02-02 19:37:27','wog','M','N','뭐',0,'1023',0,NULL,NULL,NULL),(42,12,11,'123','2023-02-04 06:06:09','2023-02-02 19:37:27','wog','M','N','뭐',0,'1023',0,NULL,NULL,NULL),(43,12,11,'123','2023-02-04 06:06:09','2023-02-02 19:37:27','wog','M','N','뭐',0,'1023',0,NULL,NULL,NULL),(44,12,11,'123','2023-02-04 06:06:09','2023-02-02 19:37:27','wog','M','N','뭐',0,'1023',0,NULL,NULL,NULL),(45,12,11,'123','2023-02-04 06:06:09','2023-02-02 19:37:27','wog','M','N','뭐',0,'1023',0,NULL,NULL,NULL),(56,0,5,'string','2023-02-05 16:18:08','2023-02-05 07:17:17','string','M','N','string',1,'string',0,NULL,NULL,NULL),(57,0,5,'string','2023-02-05 16:19:23','2023-02-05 16:18:08','string','M','N','string',1,'string',0,NULL,NULL,NULL),(58,45,5,'string','2023-02-06 01:16:51','2023-02-05 15:57:25','string','M','N','string',1,'string',0,NULL,NULL,NULL),(59,123,3,'123123123wdsa','2023-02-06 01:22:57','2023-02-05 16:18:08','공통프로젝트','W','N','ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ',0,'123123',0,NULL,NULL,NULL),(73,29,13,'정ㅈ','2023-02-07 01:15:49','2023-02-16 06:00:00','wogns8','M','N','정재훈',0,'1012341234',0,NULL,NULL,NULL),(74,32,1,'간다라ㅏ','2023-02-07 01:17:00','2023-02-24 06:00:00','um','W','N','엄형',0,'1012345678',0,NULL,NULL,NULL),(77,12,6,'ㄴㅁㅇㄹ','2023-02-07 01:32:03','2023-02-14 06:00:00','정재훈','W','N','공통프로젝트',0,'123',0,NULL,NULL,NULL),(90,32,3,'배포','2023-02-08 00:58:41','2023-02-17 15:00:00','um','M','N','엄형의',0,'103393',0,NULL,NULL,NULL),(91,23,11,'qweqwe','2023-02-08 01:51:22','2023-02-24 06:00:00','wogns8123@naver.com','W','N','정재훈',0,'1073830424',0,NULL,NULL,NULL),(92,23,10,'qweqwe','2023-02-08 01:54:10','2023-02-23 06:00:00','wogns8123@naver.com','M','N','정재훈',0,'73830424',0,NULL,NULL,NULL),(93,0,5,'string','2023-02-08 01:58:11','2023-02-07 16:57:58','string','M','N','string',1,'string',0,NULL,NULL,NULL),(94,12,1,'qwe','2023-02-08 02:02:09','2023-02-22 06:00:00','성공','W','N','공통프로젝트',0,'122',0,NULL,NULL,NULL),(95,23,6,'qwe','2023-02-08 02:03:24','2023-02-15 06:00:00','wogns8123@naver.com','M','N','정재훈',1,'1073830424',1,NULL,NULL,NULL),(96,34,5,'신청','2023-02-08 02:06:27','2023-02-17 06:00:00','email','W','N','김미현',1,'1010101010',1,NULL,NULL,NULL),(97,22,2,'가나다라맙사ㅏ아','2023-02-08 02:10:06','2023-02-09 06:00:00','email','W','N','김아현',0,'1020310203',0,NULL,NULL,NULL),(98,123,10,'ㅁㄴㅇ뤼ㅏㄴㅁㅇㄹ','2023-02-08 02:10:28','2023-02-22 06:00:00','email','M','N','김빛누리',0,'10101010100101',0,NULL,NULL,NULL),(101,33,5,'string','2023-02-09 03:38:19','2023-02-08 18:37:42','string','M','N','string',1,'string',0,NULL,NULL,NULL),(106,22,0,'sdfsdf','2023-02-09 06:04:16',NULL,'dsfds','M','N','ddd',0,'222',0,NULL,NULL,NULL),(109,28,1,'노트북이 너무 밝아ㅛ','2023-02-12 07:24:17','2023-02-23 15:00:00','wogns8123@naver.com','M','N','정재훈',0,'1073830424',0,NULL,NULL,NULL),(112,19,1,'상담예약이 뜨나/','2023-02-12 09:36:16',NULL,'agus@nads','W','N','상담예약',0,'101010101',0,NULL,NULL,NULL),(113,1,0,'dd','2023-02-12 09:37:55','2023-02-16 06:00:00','dkgus816@gmail.com','W','N','ddd',0,'1011115555',0,NULL,NULL,NULL),(114,12,1,'qweqwe','2023-02-12 14:20:38','2023-02-23 06:00:00','wogns8123@naver.com','M','N','가',0,'123123',0,NULL,NULL,NULL),(115,23,1,'123','2023-02-13 02:43:40','2023-02-17 15:00:00','don@don.com','M','N','e202',0,'1011111111',0,NULL,NULL,NULL),(116,11,0,'진로상담','2023-02-13 22:39:54','2023-02-17 06:00:00','dkgus816@gmail.com','M','N','김아현',0,'1011111111',0,NULL,NULL,NULL),(117,22,1,'재난입니다','2023-02-13 22:44:00','2023-02-17 06:00:00','dkgus816@gmail.com','W','N','김재난',0,'11111',0,NULL,NULL,NULL),(118,15,0,'계속 잠이 와요','2023-02-15 00:11:17','2023-02-17 06:00:00','hi@mail.com','M','N','김안녕',0,'1022223333',0,NULL,NULL,NULL),(119,14,0,'진로상담','2023-02-15 01:51:22','2023-02-17 06:00:00','hi@mail.com','W','N','김안녕',0,'1022223333',0,NULL,NULL,NULL),(120,32,2,'부부 상담 신청합니다.','2023-02-15 02:26:06','2023-02-18 06:00:00','hi@mail.com','W','N','김부부',0,'1033334444',0,NULL,NULL,NULL),(123,17,0,'진로 상담 신청합니다.','2023-02-15 05:11:11','2023-02-17 06:00:00','hi@mail.com','W','N','김안녕',0,'1011118888',0,NULL,NULL,NULL),(128,14,0,'힘들어요오오','2023-02-15 12:21:42','2023-02-14 15:00:00','bnurik104@gmail.com','W','N','비누',0,NULL,0,NULL,NULL,NULL),(129,14,0,'힘들어요오오','2023-02-15 12:21:46','2023-02-14 15:00:00','bnurik104@gmail.com','W','N','비누',0,'101111',0,NULL,NULL,NULL),(130,2,1,'ddddd','2023-02-15 12:23:22','2023-02-14 15:00:00','dd@ddd0d.com','W','N','binu',0,'2213',0,NULL,NULL,NULL),(140,33,0,'ddd','2023-02-16 00:19:35','2023-02-16 06:00:00','bnurik104@gmail.com','M','N','ddd',0,'333',0,NULL,NULL,NULL),(141,33,0,'ddddd','2023-02-16 00:20:34','2023-02-15 06:00:00','ddd','M','N','ddddd',0,'22',0,NULL,NULL,NULL),(142,33,0,'ddddd','2023-02-16 00:20:45','2023-02-15 06:00:00','gegfasdfasdddd@dd.com','M','N','ddddd',0,'22',0,NULL,NULL,NULL),(170,33,2,'부부 갈등이 심해요.','2023-02-16 11:17:55','2023-02-18 15:00:00','hi@mail.com','W','N','김부부',0,'1022229999',0,NULL,NULL,NULL),(171,44,2,'고민입니다.....','2023-02-16 11:19:36','2023-02-17 15:00:00','hi@mail.com','W','N','김부부',0,'1022222222',0,NULL,NULL,NULL),(172,17,8,'ㅗㅑㅗㅑㅗㅑ','2023-02-16 12:11:20','2023-02-15 15:00:00','hi@mail.com','M','N','김고딩',0,'1011111111',0,NULL,NULL,NULL),(177,17,0,'진로고민','2023-02-17 00:00:46','2023-02-18 06:00:00','hi@mail.com','W','N','김안녕',0,'1022223333',0,NULL,NULL,NULL),(178,45,5,'게임 중독','2023-02-17 00:30:28','2023-02-17 06:00:00','hi@mail.com','M','N','김중도',0,'1022223333',0,NULL,NULL,NULL),(181,14,8,'진로 고민입니다 ㅜ','2023-02-17 00:44:55','2023-02-17 06:00:00','hi@mail.com','M','N','김학생',0,'1029997777',0,NULL,NULL,NULL),(182,54,12,'운동하고 시펑요','2023-02-17 00:50:46','2023-02-17 06:00:00','hi@mail.com','M','N','김부부',0,'1022221111',0,NULL,NULL,NULL),(183,25,6,'정신이 피폐해요','2023-02-17 00:55:39','2023-02-17 06:00:00','hi@mail.com','W','N','김정신',0,'1055559999',0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `consult_applicant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consult_schedule`
--

DROP TABLE IF EXISTS `consult_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consult_schedule` (
  `id` bigint NOT NULL,
  `consult_type` int DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `day_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_day_time` datetime DEFAULT NULL,
  `is_consult` varchar(255) DEFAULT NULL,
  `session_id` int DEFAULT NULL,
  `state` int DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `turn` int DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  `consult_result` varchar(255) DEFAULT NULL,
  `stt_status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consult_schedule`
--

LOCK TABLES `consult_schedule` WRITE;
/*!40000 ALTER TABLE `consult_schedule` DISABLE KEYS */;
INSERT INTO `consult_schedule` VALUES (107,5,'신청',NULL,'email','2023-02-08 02:06:27','N',96,NULL,'1010101010',0,'2023-02-20 07:34:14',NULL,2),(126,5,'신청',NULL,'email','2023-02-08 02:06:27','N',96,NULL,'1010101010',0,'2023-02-15 08:55:26',NULL,2),(127,6,'qwe',NULL,'wogns8123@naver.com','2023-02-08 02:03:24','N',95,NULL,'1073830424',0,'2023-02-15 08:58:36',NULL,0),(176,5,'신청',NULL,'email','2023-02-08 02:06:27','N',96,NULL,'1010101010',0,'2023-02-16 15:50:00',NULL,0);
/*!40000 ALTER TABLE `consult_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consult_session`
--

DROP TABLE IF EXISTS `consult_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consult_session` (
  `id` int NOT NULL AUTO_INCREMENT,
  `client_id` varchar(255) DEFAULT NULL,
  `counselor_id` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `openvidu_id` varchar(255) DEFAULT NULL,
  `stt_status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consult_session`
--

LOCK TABLES `consult_session` WRITE;
/*!40000 ALTER TABLE `consult_session` DISABLE KEYS */;
INSERT INTO `consult_session` VALUES (1,'user1','string',0,NULL,NULL),(10,'yaya','string',0,NULL,NULL),(21,'client','counselor2',3,NULL,2),(30,'client','counselor2',0,NULL,3),(31,'client','counselor01',0,NULL,-1),(32,'client','counselor01',0,NULL,3),(37,'jh01','counselor01',0,NULL,NULL),(38,'jh01','counselor01',0,NULL,NULL),(39,'jh01','counselor01',0,NULL,NULL),(40,'jh01','counselor01',0,NULL,NULL),(41,'jh01','counselor01',0,NULL,NULL),(42,'jh01','counselor01',0,NULL,NULL),(43,'jh01','counselor01',0,NULL,NULL),(44,'jh01','counselor01',0,NULL,NULL),(45,'jh01','counselor01',0,NULL,NULL),(56,'string','counselor01',0,NULL,NULL),(57,'string','counselor01',0,NULL,NULL),(58,'client','counselor23',0,NULL,NULL),(79,'client','counselor01',0,NULL,NULL),(93,'yaya','string',0,NULL,NULL),(94,'client','counselor01',0,NULL,NULL),(95,'client','counselor01',1,NULL,NULL),(96,'client','counselor01',0,NULL,NULL),(97,'jh01','counselor01',0,NULL,NULL),(98,'jh01','counselor01',1,NULL,NULL),(99,'client','counselor01',0,NULL,NULL),(100,'client','counselor01',0,NULL,NULL),(101,'string','string',0,NULL,NULL),(102,'client_con','counselor23',0,NULL,NULL),(106,'idd','으악',0,NULL,NULL),(109,'client','counselor01',0,NULL,NULL),(112,'client','counselor01',0,NULL,NULL),(113,'kimkim2','ahhohh',0,NULL,-1),(114,'client','counselor01',0,NULL,-1),(115,'coach','testcouns',0,NULL,2),(116,'kimkim2','counselor01',0,NULL,0),(117,'kimkim2','counselor01',0,NULL,0),(118,'kimkim2','counselor01',0,NULL,0),(119,'hihi','gggo',0,NULL,0),(120,'kimkim2','gggo',0,NULL,0),(123,'idd','gggo',0,NULL,0),(128,'idd','gggo',0,NULL,0),(129,'idd','gggo',0,NULL,0),(130,'idd','gggo',0,NULL,0),(140,'idd','counselor23',0,NULL,NULL),(141,'idd','counselor01',0,NULL,NULL),(142,'idd','counselor01',0,NULL,NULL),(170,'kimkim2','hihi',0,NULL,NULL),(171,'kimkim2','hihi',0,NULL,NULL),(172,'kimkim2','hihi',0,NULL,NULL),(177,'kimkim2','hihi',0,NULL,NULL),(178,'kimkim2','hihi',0,NULL,NULL),(181,'kimkim2','hihi',0,NULL,NULL),(182,'kimkim2','hihi',0,NULL,NULL),(183,'kimkim2','hihi',0,NULL,NULL);
/*!40000 ALTER TABLE `consult_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `counselor`
--

DROP TABLE IF EXISTS `counselor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `counselor` (
  `id` varchar(30) NOT NULL,
  `career` varchar(300) DEFAULT NULL,
  `common_code_id` varchar(3) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gender` varchar(3) DEFAULT NULL,
  `introduce` varchar(150) DEFAULT NULL,
  `major` varchar(30) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `profile_img` varchar(200) DEFAULT NULL,
  `qualification` tinyint(1) DEFAULT '0',
  `routine` varchar(28) DEFAULT NULL,
  `tel` varchar(50) DEFAULT NULL,
  `enterprise_id` int DEFAULT NULL,
  `groop_code_id` varchar(3) DEFAULT NULL,
  `consult_type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsc0tjmlj27clk6f6t1po29svm` (`enterprise_id`),
  CONSTRAINT `FKsc0tjmlj27clk6f6t1po29svm` FOREIGN KEY (`enterprise_id`) REFERENCES `enterprise` (`enterprise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `counselor`
--

LOCK TABLES `counselor` WRITE;
/*!40000 ALTER TABLE `counselor` DISABLE KEYS */;
INSERT INTO `counselor` VALUES ('counselor01','부산^강서상담센터^3년#부산^따뜻한마음센터^1년 3개월','1','bnurik104@gmail.com','M','안녕하세요! 따뜻한 마음으로 상담합니다','심리상담','정재훈','$2a$10$Y5q04yK4GIT.fTfdu2H8VeiuEXlNulYV0cROQY5CusN27qq5VsY/G','https://storage.googleapis.com/stt-bucket-binu/profile/2023/FEBRUARY/jjh.jpg',0,'[string]','01023459876',1,NULL,'[0,1]'),('counselor2','울산^상큼상담센터^2년','1','counselor2@naver.com','M','인생을 즐겁게 살아요','심리상담','황호선','$2a$10$RTtyBBNjUNkvRu5h7m0KOuejfg7A3lbMpyojpPK/6/Kyhzf1nJulS','https://storage.googleapis.com/stt-bucket-binu/profile/2023/FEBRUARY/hhs.jpg',0,'[string]','01023235656',1,NULL,'[0,1]'),('counselor23','울산^민트초코상담센터 센터장^1년 2개월','1','counselor23@naver.com','W','안녕하세요','심리상담','김아현','$2a$10$q7.O36J6FI/IH3Dj6450mOrs0f0FfDGTZt8kUc0vlhNiy7LSNdJz6','https://storage.googleapis.com/stt-bucket-binu/profile/2023/FEBRUARY/a.jpg',0,'[string]','01023235656',1,NULL,'[2,8]'),('counselorCon','싸피^컨설턴트^~2023','1','counselor_con@mail.com','M','상담 잘합니다',NULL,'서성수','$2a$10$NaQPpN5oVKs7xDnorQQXvObMGtPF632e2c/zqIrpr1NpjQB/GjRQW','https://storage.googleapis.com/stt-bucket-binu/profile/2023/FEBRUARY/l.jpg',NULL,'null','01099990000',1,NULL,'[1,3,4]'),('ddd','서울^마시멜로마음안정센터^2년반','1','ddffdd@mail.com','M','화이팅',NULL,'김미현','$2a$10$YEpnhak0yL4g.KA9EZY6v.U541v/gk9mGVtfiJPhFFjPIt7KR4feS','https://storage.googleapis.com/stt-bucket-binu/profile/2023/FEBRUARY/llll.jpg',0,'null','null',1,NULL,'[2,5]'),('fsdffs','서울^대장상담사^3년','1','sdsf','W','매일매일 즐겁게!',NULL,'엄형규','$2a$10$nVj0ff9ErrkwDzny4JxzV.PCtqflIN1AzM9BzjHPljuffz9g5J5p.','https://storage.googleapis.com/stt-bucket-binu/profile/2023/FEBRUARY/lll.jpg',NULL,'null','32343',1,NULL,'[1,9]'),('gggo','대전^인턴상담사^2년','1','cmzlsho@gmail.com','M',NULL,NULL,'김빛누리','$2a$10$0pSqsgXjsX/Zuho14D5uUuRaPn854DCmnrF1Cpi5uIbFb4JeorgTu','https://storage.googleapis.com/stt-bucket-binu/profile/2023/FEBRUARY/llll.jpg',NULL,NULL,'324355451',1,NULL,'[0,1]'),('hihi','부산^상담^1년','1','hihi@mail.com','W',NULL,NULL,'이다솜','$2a$10$PGkF1cSXO0xU04vjMMoc2u3wsl6diUJlLZGLKaJ.aL0ZBvKcxakUS','https://storage.googleapis.com/stt-bucket-binu/profile/2023/FEBRUARY/en.jpg',NULL,'null','01022223333',1,NULL,'[0,1,3]'),('ididid',NULL,'1',NULL,'M',NULL,NULL,'이채은','$2a$10$0pSqsgXjsX/Zuho14D5uUuRaPn854DCmnrF1Cpi5uIbFb4JeorgTu','https://storage.googleapis.com/stt-bucket-binu/profile/2023/FEBRUARY/sun.jpg',NULL,NULL,NULL,1,NULL,'[0,1]');
/*!40000 ALTER TABLE `counselor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `counselor_consult_type_list`
--

DROP TABLE IF EXISTS `counselor_consult_type_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `counselor_consult_type_list` (
  `counselor_id` varchar(30) NOT NULL,
  `consult_type` varchar(255) DEFAULT NULL,
  KEY `FKqg49e6rgfrqsro87btkteqjgn` (`counselor_id`),
  CONSTRAINT `FKqg49e6rgfrqsro87btkteqjgn` FOREIGN KEY (`counselor_id`) REFERENCES `counselor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `counselor_consult_type_list`
--

LOCK TABLES `counselor_consult_type_list` WRITE;
/*!40000 ALTER TABLE `counselor_consult_type_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `counselor_consult_type_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education`
--

DROP TABLE IF EXISTS `education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `education` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `degree` varchar(50) DEFAULT NULL,
  `is_graduate` varchar(30) DEFAULT NULL,
  `major` varchar(100) DEFAULT NULL,
  `school` varchar(50) DEFAULT NULL,
  `counselor_id` varchar(30) DEFAULT NULL,
  `photo` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbrl4xvfk8q291ywvdsvld10l5` (`counselor_id`),
  KEY `FKcscesklv77m3p17b7qygr0oay` (`photo`),
  CONSTRAINT `FKbrl4xvfk8q291ywvdsvld10l5` FOREIGN KEY (`counselor_id`) REFERENCES `counselor` (`id`),
  CONSTRAINT `FKcscesklv77m3p17b7qygr0oay` FOREIGN KEY (`photo`) REFERENCES `profile_photo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education`
--

LOCK TABLES `education` WRITE;
/*!40000 ALTER TABLE `education` DISABLE KEYS */;
INSERT INTO `education` VALUES (8,'학사','졸업','심리상담','고려대학교','counselor01',12),(9,'학사','재학','전공','컨설턴트대','counselorCon',13);
/*!40000 ALTER TABLE `education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enterprise`
--

DROP TABLE IF EXISTS `enterprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enterprise` (
  `enterprise_id` int NOT NULL AUTO_INCREMENT,
  `corporate_code` varchar(30) DEFAULT NULL,
  `enterprise` varchar(30) DEFAULT NULL,
  `manager_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`enterprise_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enterprise`
--

LOCK TABLES `enterprise` WRITE;
/*!40000 ALTER TABLE `enterprise` DISABLE KEYS */;
INSERT INTO `enterprise` VALUES (1,'0','enterprise1','manager');
/*!40000 ALTER TABLE `enterprise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_counselor`
--

DROP TABLE IF EXISTS `favorite_counselor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite_counselor` (
  `counselor_id` varchar(255) NOT NULL,
  `client_id` varchar(255) NOT NULL,
  PRIMARY KEY (`counselor_id`,`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_counselor`
--

LOCK TABLES `favorite_counselor` WRITE;
/*!40000 ALTER TABLE `favorite_counselor` DISABLE KEYS */;
INSERT INTO `favorite_counselor` VALUES ('counselor01','hihi'),('counselor01','idd'),('counselor01','jh01'),('counselor01','kimkim2'),('counselor2','idd'),('counselor23','idd'),('dddddsf','testclient'),('string','id'),('testcouns','coach'),('undefined','counselor01'),('whw222','id'),('으악','idd');
/*!40000 ALTER TABLE `favorite_counselor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (189);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `admin_id` varchar(255) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `is_fixed` bit(1) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `views` int DEFAULT '0',
  `fixed` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (7,'2023-02-02 03:08:04','2023-02-02 03:08:04','id','업데이트 입니다.',NULL,'세번째 업데이트 안내',NULL,2,_binary '\0'),(9,'2023-02-02 10:54:26','2023-02-02 10:54:26','id','업데이트 공지',NULL,'공지입니다',NULL,0,_binary '\0'),(10,'2023-02-02 10:54:38','2023-02-02 10:54:38','id','업데이트 공지',NULL,'공지공지',NULL,1,_binary '\0'),(17,'2023-02-07 03:01:42','2023-02-07 03:01:42',NULL,'ss',NULL,'ss',NULL,1,_binary '\0'),(18,'2023-02-07 03:01:50','2023-02-07 03:01:50',NULL,'11',NULL,'11',NULL,1,_binary '\0'),(19,'2023-02-07 06:20:03','2023-02-07 06:20:03',NULL,'qq',NULL,'qq',NULL,2,_binary '\0'),(20,'2023-02-07 07:50:48','2023-02-07 07:50:48',NULL,'ㅂㅂ',NULL,'ㅂㅂ',NULL,8,_binary '\0'),(24,'2023-02-08 02:15:08','2023-02-08 02:15:08',NULL,'dddddddddddd',NULL,'dddddddddd',NULL,200,_binary '\0'),(25,'2023-02-16 15:17:48','2023-02-16 15:17:48','string','string',NULL,'string',NULL,29,_binary ''),(27,'2023-02-16 16:08:33','2023-02-16 16:08:33','admin','ㅂㅈㄷㅂㅈㄷ',NULL,'ㅂㅈㄷ',NULL,0,_binary '\0'),(28,'2023-02-16 16:08:41','2023-02-16 16:08:41','admin','작성중\n',NULL,'공지사항',NULL,4,_binary '\0'),(29,'2023-02-16 16:18:54','2023-02-16 16:18:54','admin','4번째 업데이트',NULL,'4번째 업데이트',NULL,6,_binary '\0');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `type` int DEFAULT NULL,
  `receiver_id` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `is_read` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'2023-02-05 13:55:35','2023-02-05 13:55:35','고민게시글에 댓글이 등록되었습니다.',2,'kimkim2','/api/trouble/143',_binary '','dd'),(2,'2023-02-05 13:56:34','2023-02-05 13:56:34','고민게시글에 댓글이 등록되었습니다.',2,'kimkim2','/api/trouble/143',_binary '\0','dd'),(3,'2023-02-05 13:59:49','2023-02-05 13:59:49','고민게시글에 댓글이 등록되었습니다.',2,'kimkim2','/api/trouble/143',_binary '\0','dd'),(4,'2023-02-05 14:18:22','2023-02-05 14:18:22','고민게시글에 댓글이 등록되었습니다.',2,'kimkim2','/api/trouble/143',_binary '\0','dd'),(5,'2023-02-05 14:21:10','2023-02-05 14:21:10','고민게시글에 댓글이 등록되었습니다.',2,'kimkim2','/api/trouble/143',_binary '\0','dd'),(6,'2023-02-05 21:02:02','2023-02-05 21:02:02','고민게시글에 댓글이 등록되었습니다.',2,'kimkim2','/api/trouble/143',_binary '\0','dd'),(7,'2023-02-05 21:03:07','2023-02-05 21:03:07','고민게시글에 댓글이 등록되었습니다.',2,'kimkim2','/api/trouble/143',_binary '\0','dd'),(8,'2023-02-05 21:06:15','2023-02-05 21:06:15','고민게시글에 댓글이 등록되었습니다.',2,'kimkim2','/api/trouble/143',_binary '','dd'),(9,'2023-02-05 21:08:42','2023-02-05 21:08:42','고민게시글에 댓글이 등록되었습니다.',2,'kimkim2','/api/trouble/143',_binary '','dd'),(22,'2023-02-06 08:58:21','2023-02-06 08:58:21','',2,'id','/api/trouble/1',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(23,'2023-02-06 08:59:25','2023-02-06 08:59:25','',2,'kimkim2','/api/trouble/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(25,'2023-02-06 11:03:45','2023-02-06 11:03:45','',2,'kimkim2','/api/trouble/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(26,'2023-02-06 11:04:48','2023-02-06 11:04:48','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(27,'2023-02-06 11:05:38','2023-02-06 11:05:38','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(28,'2023-02-06 11:06:21','2023-02-06 11:06:21','',2,'kimkim2','/api/trouble/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(29,'2023-02-06 11:07:00','2023-02-06 11:07:00','',2,'kimkim2','/api/trouble/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(30,'2023-02-06 11:08:31','2023-02-06 11:08:31','',2,'kimkim2','/api/trouble/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(31,'2023-02-06 11:09:07','2023-02-06 11:09:07','',2,'kimkim2','/api/trouble/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(32,'2023-02-06 11:10:02','2023-02-06 11:10:02','',2,'kimkim2','/api/trouble/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(33,'2023-02-06 11:50:07','2023-02-06 11:50:07','',2,'kimkim2','/api/trouble/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(34,'2023-02-06 11:54:17','2023-02-06 11:54:17','',2,'kimkim2','/api/trouble/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(35,'2023-02-06 12:14:31','2023-02-06 12:14:31','',2,'kimkim2','/api/trouble/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(43,'2023-02-07 00:19:58','2023-02-07 00:19:58','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(44,'2023-02-07 00:20:27','2023-02-07 00:20:27','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(45,'2023-02-07 00:20:44','2023-02-07 00:20:44','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(46,'2023-02-07 00:22:14','2023-02-07 00:22:14','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(49,'2023-02-07 00:39:21','2023-02-07 00:39:21','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(50,'2023-02-07 00:40:05','2023-02-07 00:40:05','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(51,'2023-02-07 00:40:55','2023-02-07 00:40:55','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(52,'2023-02-07 00:41:42','2023-02-07 00:41:42','',2,'kimkim2','/api/trouble/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(53,'2023-02-07 00:45:38','2023-02-07 00:45:38','',2,'kimkim2','/api/trouble/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(54,'2023-02-07 00:47:19','2023-02-07 00:47:19','',2,'kimkim2','/api/trouble/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(55,'2023-02-07 00:47:46','2023-02-07 00:47:46','',2,'kimkim2','/api/trouble/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(56,'2023-02-07 00:56:06','2023-02-07 00:56:06','',2,'kimkim2','/api/trouble/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(57,'2023-02-07 01:09:51','2023-02-07 01:09:51','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(58,'2023-02-07 01:13:19','2023-02-07 01:13:19','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(59,'2023-02-07 01:17:38','2023-02-07 01:17:38','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(60,'2023-02-07 01:19:21','2023-02-07 01:19:21','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(61,'2023-02-07 01:29:05','2023-02-07 01:29:05','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(63,'2023-02-07 01:34:06','2023-02-07 01:34:06','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(64,'2023-02-07 01:35:54','2023-02-07 01:35:54','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(65,'2023-02-07 01:36:47','2023-02-07 01:36:47','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(66,'2023-02-07 01:38:23','2023-02-07 01:38:23','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(67,'2023-02-07 01:38:32','2023-02-07 01:38:32','',2,'kimkim2','/api/trouble/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(68,'2023-02-07 05:45:43','2023-02-07 05:45:43','',2,'kimkim2','/KidBoardDetail/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(69,'2023-02-07 05:55:14','2023-02-07 05:55:14','',2,'kimkim2','/KidBoardDetail/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(70,'2023-02-07 05:56:37','2023-02-07 05:56:37','',2,'kimkim2','/KidBoardDetail/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(71,'2023-02-07 06:01:45','2023-02-07 06:01:45','',2,'kimkim2','/KidBoardDetail/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(72,'2023-02-07 06:09:07','2023-02-07 06:09:07','',2,'kimkim2','/KidBoardDetail/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(73,'2023-02-07 06:09:19','2023-02-07 06:09:19','',2,'kimkim2','/KidBoardDetail/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(74,'2023-02-07 07:03:30','2023-02-07 07:03:30','',2,'kimkim2','/KidBoardDetail/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(75,'2023-02-07 07:32:06','2023-02-07 07:32:06','',2,'kimkim2','/KidBoardDetail/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(76,'2023-02-07 07:33:12','2023-02-07 07:33:12','',2,'kimkim2','/KidBoardDetail/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(77,'2023-02-07 07:34:59','2023-02-07 07:34:59','',2,'kimkim2','/KidBoardDetail/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(78,'2023-02-07 07:35:56','2023-02-07 07:35:56','',2,'kimkim2','/KidBoardDetail/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(79,'2023-02-07 07:41:06','2023-02-07 07:41:06','',2,'kimkim2','/KidBoardDetail/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(80,'2023-02-07 07:45:26','2023-02-07 07:45:26','',2,'kimkim2','/KidBoardDetail/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(81,'2023-02-07 07:46:51','2023-02-07 07:46:51','',2,'kimkim2','/KidBoardDetail/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(82,'2023-02-07 07:48:21','2023-02-07 07:48:21','',2,'kimkim2','/KidBoardDetail/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(83,'2023-02-07 07:49:40','2023-02-07 07:49:40','',2,'kimkim2','/KidBoardDetail/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(84,'2023-02-07 07:52:06','2023-02-07 07:52:06','',2,'kimkim2','/KidBoardDetail/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(85,'2023-02-07 07:53:23','2023-02-07 07:53:23','',2,'kimkim2','/KidBoardDetail/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(86,'2023-02-07 07:54:21','2023-02-07 07:54:21','',2,'kimkim2','/KidBoardDetail/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(87,'2023-02-07 07:57:01','2023-02-07 07:57:01','',2,'kimkim2','/KidBoardDetail/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(88,'2023-02-07 08:00:21','2023-02-07 08:00:21','',2,'kimkim2','/KidBoardDetail/143',_binary '','고민게시글에 댓글이 등록되었습니다.'),(89,'2023-02-09 04:08:56','2023-02-09 04:08:56','',2,'idd','/KidBoardDetail/170',_binary '','고민게시글에 댓글이 등록되었습니다.'),(90,'2023-02-09 05:42:57','2023-02-09 05:42:57','',2,'idd','/KidBoardDetail/170',_binary '','고민게시글에 댓글이 등록되었습니다.'),(91,'2023-02-12 06:53:10','2023-02-12 06:53:10','',2,'kimkim2','/KidBoardDetail/143',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(92,'2023-02-13 22:39:54','2023-02-13 22:39:54','상담사로부터 전화가 갈 예정입니다. 초기 상담 및 일정 픽스가 진행됩니다.',1,'kimkim2','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(93,'2023-02-13 22:39:54','2023-02-13 22:39:54','',1,'counselor01','/AlarmView',_binary '','새로운 상담 신청이 있습니다.'),(94,'2023-02-13 22:44:00','2023-02-13 22:44:00','상담사로부터 전화가 갈 예정입니다. 초기 상담 및 일정 픽스가 진행됩니다.',1,'kimkim2','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(96,'2023-02-15 00:11:17','2023-02-15 00:11:17','상담사로부터 전화가 갈 예정입니다. 초기 상담 및 일정 픽스가 진행됩니다.',1,'kimkim2','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(97,'2023-02-15 00:11:17','2023-02-15 00:11:17','',1,'counselor01','/AlarmView',_binary '','새로운 상담 신청이 있습니다.'),(98,'2023-02-15 01:41:52','2023-02-15 01:41:52','',2,'idd','/AlarmView',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(99,'2023-02-15 01:43:12','2023-02-15 01:43:12','',2,'idd','/AlarmView',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(101,'2023-02-15 01:51:22','2023-02-15 01:51:22','',1,'gggo','/AlarmView',_binary '\0','새로운 상담 신청이 있습니다.'),(102,'2023-02-15 02:06:48','2023-02-15 02:06:48','',2,'idd','/AlarmView',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(103,'2023-02-15 02:26:06','2023-02-15 02:26:06','상담사로부터 전화가 갈 예정입니다. 초기 상담 및 일정 픽스가 진행됩니다.',1,'kimkim2','/AlarmView',_binary '','상담신청이 정상적으로 접수되었습니다.'),(104,'2023-02-15 02:26:06','2023-02-15 02:26:06','',1,'gggo','/AlarmView',_binary '\0','새로운 상담 신청이 있습니다.'),(105,'2023-02-15 05:11:11','2023-02-15 05:11:11','상담사로부터 전화가 갈 예정입니다. 초기 상담 및 일정 픽스가 진행됩니다.',1,'idd','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(106,'2023-02-15 05:11:11','2023-02-15 05:11:11','',1,'gggo','/AlarmView',_binary '\0','새로운 상담 신청이 있습니다.'),(107,'2023-02-15 08:55:26','2023-02-15 08:55:26','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(108,'2023-02-15 08:58:36','2023-02-15 08:58:36','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(109,'2023-02-15 12:21:42','2023-02-15 12:21:42','상담사로부터 전화가 갈 예정입니다. 초기 상담 및 일정 픽스가 진행됩니다.',1,'idd','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(110,'2023-02-15 12:21:42','2023-02-15 12:21:42','',1,'gggo','/AlarmView',_binary '\0','새로운 상담 신청이 있습니다.'),(111,'2023-02-15 12:21:46','2023-02-15 12:21:46','상담사로부터 전화가 갈 예정입니다. 초기 상담 및 일정 픽스가 진행됩니다.',1,'idd','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(112,'2023-02-15 12:21:46','2023-02-15 12:21:46','',1,'gggo','/AlarmView',_binary '\0','새로운 상담 신청이 있습니다.'),(113,'2023-02-15 12:23:22','2023-02-15 12:23:22','상담사로부터 전화가 갈 예정입니다. 초기 상담 및 일정 픽스가 진행됩니다.',1,'idd','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(114,'2023-02-15 12:23:22','2023-02-15 12:23:22','',1,'gggo','/AlarmView',_binary '\0','새로운 상담 신청이 있습니다.'),(115,'2023-02-15 13:41:15','2023-02-15 13:41:15','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(116,'2023-02-15 15:00:04','2023-02-15 15:00:04','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(117,'2023-02-15 15:42:03','2023-02-15 15:42:03','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(118,'2023-02-15 15:42:03','2023-02-15 15:42:03','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(119,'2023-02-15 15:42:12','2023-02-15 15:42:12','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(120,'2023-02-15 15:42:12','2023-02-15 15:42:12','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(121,'2023-02-15 15:42:29','2023-02-15 15:42:29','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(122,'2023-02-15 15:42:29','2023-02-15 15:42:29','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(124,'2023-02-16 00:19:35','2023-02-16 00:19:35','상담사로부터 전화가 갈 예정입니다. 초기 상담 및 일정 픽스가 진행됩니다.',1,'idd','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(125,'2023-02-16 00:19:35','2023-02-16 00:19:35','',1,'counselor23','/AlarmView',_binary '\0','새로운 상담 신청이 있습니다.'),(126,'2023-02-16 00:20:34','2023-02-16 00:20:34','상담사로부터 전화가 갈 예정입니다. 초기 상담 및 일정 픽스가 진행됩니다.',1,'idd','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(127,'2023-02-16 00:20:34','2023-02-16 00:20:34','',1,'counselor01','/AlarmView',_binary '','새로운 상담 신청이 있습니다.'),(129,'2023-02-16 00:20:45','2023-02-16 00:20:45','',1,'counselor01','/AlarmView',_binary '','새로운 상담 신청이 있습니다.'),(130,'2023-02-16 00:20:47','2023-02-16 00:20:47','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(131,'2023-02-16 00:23:26','2023-02-16 00:23:26','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(132,'2023-02-16 00:25:47','2023-02-16 00:25:47','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(133,'2023-02-16 00:26:54','2023-02-16 00:26:54','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(134,'2023-02-16 00:28:10','2023-02-16 00:28:10','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(135,'2023-02-16 00:32:01','2023-02-16 00:32:01','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(137,'2023-02-16 00:33:02','2023-02-16 00:33:02','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(138,'2023-02-16 00:34:16','2023-02-16 00:34:16','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(139,'2023-02-16 00:34:16','2023-02-16 00:34:16','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(140,'2023-02-16 00:42:36','2023-02-16 00:42:36','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(141,'2023-02-16 00:44:48','2023-02-16 00:44:48','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(142,'2023-02-16 00:48:01','2023-02-16 00:48:01','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(143,'2023-02-16 00:52:13','2023-02-16 00:52:13','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(144,'2023-02-16 01:18:03','2023-02-16 01:18:03','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(146,'2023-02-16 01:20:42','2023-02-16 01:20:42','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(147,'2023-02-16 01:22:42','2023-02-16 01:22:42','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(148,'2023-02-16 01:23:23','2023-02-16 01:23:23','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(149,'2023-02-16 01:27:43','2023-02-16 01:27:43','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(150,'2023-02-16 01:28:22','2023-02-16 01:28:22','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(151,'2023-02-16 01:30:10','2023-02-16 01:30:10','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(152,'2023-02-16 01:33:23','2023-02-16 01:33:23','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(153,'2023-02-16 01:40:08','2023-02-16 01:40:08','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(154,'2023-02-16 01:40:08','2023-02-16 01:40:08','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(155,'2023-02-16 05:58:50','2023-02-16 05:58:50','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(156,'2023-02-16 07:37:09','2023-02-16 07:37:09','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(157,'2023-02-16 08:55:42','2023-02-16 08:55:42','',2,'idd','/AlarmView',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(158,'2023-02-16 11:17:55','2023-02-16 11:17:55','상담사로부터 전화가 갈 예정입니다. 초기 상담 및 일정 픽스가 진행됩니다.',1,'kimkim2','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(160,'2023-02-16 11:19:36','2023-02-16 11:19:36','상담사로부터 전화가 갈 예정입니다. 초기 상담 및 일정 픽스가 진행됩니다.',1,'kimkim2','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(161,'2023-02-16 11:19:36','2023-02-16 11:19:36','',1,'hihi','/AlarmView',_binary '','새로운 상담 신청이 있습니다.'),(162,'2023-02-16 12:11:20','2023-02-16 12:11:20','상담사로부터 전화가 갈 예정입니다. 초기 상담 및 일정 픽스가 진행됩니다.',1,'kimkim2','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(163,'2023-02-16 12:11:20','2023-02-16 12:11:20','',1,'hihi','/AlarmView',_binary '','새로운 상담 신청이 있습니다.'),(164,'2023-02-16 17:29:33','2023-02-16 17:29:33','',3,'client','/api/qna/15',_binary '\0','문의글에 댓글이 등록되었습니다.'),(165,'2023-02-16 17:32:06','2023-02-16 17:32:06','',3,'client','/api/qna/15',_binary '\0','문의글에 댓글이 등록되었습니다.'),(166,'2023-02-16 17:49:30','2023-02-16 17:49:30','',3,'client','/api/qna/15',_binary '\0','문의글에 댓글이 등록되었습니다.'),(167,'2023-02-16 17:49:36','2023-02-16 17:49:36','',3,'client','/api/qna/15',_binary '\0','문의글에 댓글이 등록되었습니다.'),(169,'2023-02-16 18:18:24','2023-02-16 18:18:24','',3,'client','/api/qna/15',_binary '\0','문의글에 댓글이 등록되었습니다.'),(170,'2023-02-16 22:13:50','2023-02-16 22:13:50','',2,'idd','/AlarmView',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(171,'2023-02-16 22:18:44','2023-02-16 22:18:44','',2,'idd','/AlarmView',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(172,'2023-02-16 22:18:47','2023-02-16 22:18:47','',2,'idd','/AlarmView',_binary '\0','고민게시글에 댓글이 등록되었습니다.'),(180,'2023-02-16 22:22:14','2023-02-16 22:22:14','',3,'client','/api/qna/15',_binary '\0','문의글에 댓글이 등록되었습니다.'),(181,'2023-02-16 23:54:54','2023-02-16 23:54:54','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(182,'2023-02-16 23:55:17','2023-02-16 23:55:17','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(183,'2023-02-16 23:56:45','2023-02-16 23:56:45','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(184,'2023-02-17 00:00:16','2023-02-17 00:00:16','상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.',1,'client','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(185,'2023-02-17 00:00:47','2023-02-17 00:00:47','',1,'hihi','/AlarmView',_binary '','새로운 상담 신청이 있습니다.'),(186,'2023-02-17 00:00:47','2023-02-17 00:00:47','상담사로부터 전화가 갈 예정입니다. 초기 상담 및 일정 픽스가 진행됩니다.',1,'kimkim2','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(187,'2023-02-17 00:14:01','2023-02-17 00:14:01','',3,'kimkim2','/api/qna/17',_binary '\0','문의글에 댓글이 등록되었습니다.'),(188,'2023-02-17 00:15:31','2023-02-17 00:15:31','',3,'kimkim2','/api/qna/17',_binary '\0','문의글에 댓글이 등록되었습니다.'),(189,'2023-02-17 00:30:28','2023-02-17 00:30:28',NULL,1,'hihi','/AlarmView',_binary '','새로운 상담 신청이 있습니다.'),(190,'2023-02-17 00:30:28','2023-02-17 00:30:28','상담사로부터 전화가 갈 예정입니다. 초기 상담 및 일정 픽스가 진행됩니다.',1,'kimkim2','/AlarmView',_binary '','상담신청이 정상적으로 접수되었습니다.'),(191,'2023-02-17 00:44:55','2023-02-17 00:44:55',NULL,1,'hihi','/AlarmView',_binary '','새로운 상담 신청이 있습니다.'),(192,'2023-02-17 00:44:55','2023-02-17 00:44:55','상담사로부터 전화가 갈 예정입니다. 초기 상담 및 일정 픽스가 진행됩니다.',1,'kimkim2','/AlarmView',_binary '','상담신청이 정상적으로 접수되었습니다.'),(193,'2023-02-17 00:50:46','2023-02-17 00:50:46',NULL,1,'hihi','/AlarmView',_binary '\0','새로운 상담 신청이 있습니다.'),(194,'2023-02-17 00:50:46','2023-02-17 00:50:46','상담사로부터 전화가 갈 예정입니다. 초기 상담 및 일정 픽스가 진행됩니다.',1,'kimkim2','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(195,'2023-02-17 00:55:39','2023-02-17 00:55:39',NULL,1,'hihi','/AlarmView',_binary '','새로운 상담 신청이 있습니다.'),(196,'2023-02-17 00:55:39','2023-02-17 00:55:39','상담사로부터 전화가 갈 예정입니다. 초기 상담 및 일정 픽스가 진행됩니다.',1,'kimkim2','/AlarmView',_binary '\0','상담신청이 정상적으로 접수되었습니다.'),(197,'2023-02-17 01:34:44','2023-02-17 01:34:44','',2,'kimkim2','/AlarmView',_binary '','고민게시글에 댓글이 등록되었습니다.');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile_photo`
--

DROP TABLE IF EXISTS `profile_photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile_photo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile_photo`
--

LOCK TABLES `profile_photo` WRITE;
/*!40000 ALTER TABLE `profile_photo` DISABLE KEYS */;
INSERT INTO `profile_photo` VALUES (1,NULL,NULL,'ㅇㅇ','ㅇㅇ'),(4,'2023-02-09 05:29:38','2023-02-09 05:29:38','결혼.jpg','https://storage.googleapis.com/stt-bucket-binu/edu/2023/FEBRUARY/6d627dc2-0753-4cef-afe5-2872d8e829d3_%EA%B2%B0%ED%98%BC.jpg'),(5,'2023-02-09 05:31:10','2023-02-09 05:31:10','결혼.jpg','https://storage.googleapis.com/stt-bucket-binu/edu/2023/FEBRUARY/125a0deb-4156-4556-aa77-c9f71f7971a1_%EA%B2%B0%ED%98%BC.jpg'),(6,'2023-02-09 07:45:28','2023-02-09 07:45:28','결혼.jpg','https://storage.googleapis.com/stt-bucket-binu/cert/2023/FEBRUARY/3e82c545-1f47-433c-8ad6-de0c605f4675_%EA%B2%B0%ED%98%BC.jpg'),(7,'2023-02-13 22:09:23','2023-02-13 22:09:23','faviconalarm2.png','https://storage.googleapis.com/stt-bucket-binu/cert/2023/FEBRUARY/8b206030-1bb2-4e82-a155-305a164b27b0_faviconalarm2.png'),(8,'2023-02-13 22:09:24','2023-02-13 22:09:24','faviconalarm.png','https://storage.googleapis.com/stt-bucket-binu/edu/2023/FEBRUARY/5d52c58e-31bb-41f3-85fa-1e6579a286a7_faviconalarm.png'),(9,'2023-02-14 02:56:28','2023-02-14 02:56:28','faviconalarm2.png','https://storage.googleapis.com/stt-bucket-binu/cert/2023/FEBRUARY/c32d04fb-2e9b-40f5-80f0-d4a1c9e4a0c7_faviconalarm2.png'),(10,'2023-02-14 02:56:28','2023-02-14 02:56:28','faviconalarm.png','https://storage.googleapis.com/stt-bucket-binu/edu/2023/FEBRUARY/aea84503-6cfe-43e9-9f82-585b8ffb8768_faviconalarm.png'),(11,'2023-02-15 01:09:31','2023-02-15 01:09:31','faviconalarm2.png','https://storage.googleapis.com/stt-bucket-binu/cert/2023/FEBRUARY/090e68dd-01c8-45a6-8c40-ab8ba2e8f486_faviconalarm2.png'),(12,'2023-02-15 01:09:31','2023-02-15 01:09:31','faviconalarm.png','https://storage.googleapis.com/stt-bucket-binu/edu/2023/FEBRUARY/d8a858e9-9c32-484e-a149-dd75d4bc7db6_faviconalarm.png'),(13,'2023-02-16 08:53:11','2023-02-16 08:53:11','고소장.jpg','https://storage.googleapis.com/stt-bucket-binu/edu/2023/FEBRUARY/edb9b926-7cce-422d-b532-238fed5af6bb_%EA%B3%A0%EC%86%8C%EC%9E%A5.jpg');
/*!40000 ALTER TABLE `profile_photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qnaanswer`
--

DROP TABLE IF EXISTS `qnaanswer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qnaanswer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `admin_id` varchar(255) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `board_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3uqybl1jeh75umdrx4x0xatsx` (`board_id`),
  CONSTRAINT `FK3uqybl1jeh75umdrx4x0xatsx` FOREIGN KEY (`board_id`) REFERENCES `qnaboard` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qnaanswer`
--

LOCK TABLES `qnaanswer` WRITE;
/*!40000 ALTER TABLE `qnaanswer` DISABLE KEYS */;
INSERT INTO `qnaanswer` VALUES (4,'2023-02-16 17:29:33','2023-02-16 17:29:33',NULL,'댓글 수정!',15),(5,'2023-02-16 17:32:06','2023-02-16 17:32:06','clent','ㅁㄴㅇㅁㄴㅇ',15),(9,'2023-02-16 18:18:24','2023-02-16 18:18:24',NULL,'qwe123',15),(17,'2023-02-16 22:22:14','2023-02-16 22:22:14',NULL,'qweqwe',15),(18,'2023-02-17 00:14:01','2023-02-17 00:14:01',NULL,'댓글 작성',17),(19,'2023-02-17 00:15:31','2023-02-17 00:15:31',NULL,'댓글 생성',17);
/*!40000 ALTER TABLE `qnaanswer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qnaboard`
--

DROP TABLE IF EXISTS `qnaboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qnaboard` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `fixed` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int DEFAULT NULL,
  `secret` bit(1) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `writer_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qnaboard`
--

LOCK TABLES `qnaboard` WRITE;
/*!40000 ALTER TABLE `qnaboard` DISABLE KEYS */;
INSERT INTO `qnaboard` VALUES (2,'2023-02-02 20:34:31','2023-02-02 20:34:31','23.02.02',_binary '\0',NULL,NULL,_binary '\0','신고처리결과 궁금합니다.','id'),(4,'2023-02-03 02:16:04','2023-02-03 02:16:04','문의있어요',_binary '\0',NULL,NULL,_binary '\0','문의 있습니다.','id'),(5,'2023-02-07 02:04:00','2023-02-07 02:04:00','문의있어요',_binary '\0',NULL,NULL,_binary '\0','문의 있습니다.','kimkim2'),(6,'2023-02-07 05:42:36','2023-02-07 05:42:36','문의있어요',_binary '\0',NULL,NULL,_binary '\0','문의 있습니다.','kimkim2'),(7,'2023-02-07 05:43:13','2023-02-07 05:43:13','문의있어요',_binary '\0',NULL,NULL,_binary '\0','문의 있습니다.','kimkim2'),(8,'2023-02-07 05:44:07','2023-02-07 05:44:07','문의있어요',_binary '\0',NULL,NULL,_binary '\0','문의 있습니다.','kimkim2'),(9,'2023-02-07 06:08:40','2023-02-07 06:08:40','ㄴㄴ',_binary '\0',NULL,NULL,_binary '\0','ㄴㄴ',NULL),(10,'2023-02-07 06:13:56','2023-02-07 06:13:56','11111111111111111111',_binary '\0',NULL,NULL,_binary '\0','111111111111111111111',NULL),(11,'2023-02-07 06:20:26','2023-02-07 06:20:26','gggggggggggggggg',_binary '\0',NULL,NULL,_binary '\0','ggggggggggggg',NULL),(12,'2023-02-07 06:22:11','2023-02-07 06:22:11','s',_binary '\0',NULL,NULL,_binary '\0','s',NULL),(13,'2023-02-07 06:25:36','2023-02-07 06:25:36','hihi',_binary '\0',NULL,NULL,_binary '\0','문의 있습니다.','kimkim2'),(14,'2023-02-07 06:40:44','2023-02-07 06:40:44','asdasdasdasdasda',_binary '\0',NULL,NULL,_binary '\0','adsdadasdasd',NULL),(15,'2023-02-16 16:24:41','2023-02-16 16:24:41','qweeeeeeeeeeeeeeeeeee',_binary '\0',NULL,NULL,_binary '\0','qweqweqweqwe','client'),(17,'2023-02-16 20:18:29','2023-02-16 20:18:29','testtest',_binary '\0',NULL,NULL,_binary '\0','test','kimkim2');
/*!40000 ALTER TABLE `qnaboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `review_id` bigint NOT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `contents` varchar(500) DEFAULT NULL,
  `counselor_id` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `past_count` int DEFAULT NULL,
  `starts` double DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `stars` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`review_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (13,'yaya','상담이 별로였어요','string','2023-02-02 21:58:34',0,3,'CALAMITY',NULL,NULL),(51,'string','string','string','2023-02-02 21:58:34',0,0,'string',NULL,NULL),(52,'string','string','string','2023-02-02 21:58:34',0,0,'',NULL,NULL),(53,'counselor01','상담을 하고 많은 변화가 생겼습니다.','counselor01','2023-02-02 21:58:34',0,NULL,'REHABILITATION',4,'상담 효과 있습니다.'),(54,'client','제가 받은 가족상담 중 제일 잘 맞았어요 !!','counselor01','2023-02-02 21:58:34',0,NULL,'COUPLE_FAMILY',1,'가족상담 받았어요'),(68,'string','string','string','2023-02-05 17:12:13',0,NULL,'string',0,'string'),(80,'client','많은 도움을 주셨습니다.','counselor01','2023-02-02 21:58:34',0,NULL,'SEXUAL_VIOLENCY',3,'감사합니다'),(82,'client','부부 갈등이 줄어들었어요','counselor01','2023-02-02 21:58:34',0,NULL,'COUPLE_FAMILY',3,'사이가 좋아졌어요'),(89,'counselor01','도움되었습니다.','counselor01','2023-02-02 21:58:34',0,NULL,'COURSE',3.5,'상담 후기 !'),(103,'idd','ddd','으악','2023-02-02 21:58:34',0,NULL,'CHILD_TEENAGER',3.5,'ddd'),(104,'idd','ddd','으악','2023-02-02 21:58:34',0,NULL,'CHILD_TEENAGER',3.5,'ddd'),(108,'counselor01','ㅁㄴㅇㅁ','counselor2','2023-02-02 21:58:34',0,NULL,'CHILD_TEENAGER',3,'ㅁㄴㅇ'),(111,'client','좋았어요 !!!!','counselor01','2023-02-02 21:58:34',0,NULL,'CHILD_TEENAGER',1,'진로 상담'),(125,'kimkim2','감사합니다 선생님 ! 다음 회차 때 또 뵈어요 !','counselor01','2023-02-02 21:58:34',0,NULL,'CHILD_TEENAGER',2.5,'진로 상담 받았습니다.'),(179,'client','재밋었어요','counselor01',NULL,0,NULL,'COUPLE_FAMILY',1,'후기작성'),(180,'client','재밌었어요','counselor01',NULL,0,NULL,'CALAMITY',4.5,'재밌었어요'),(188,'client','qwqwqwe','counselor01',NULL,0,NULL,'COUPLE_FAMILY',1,'qw');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stt_data`
--

DROP TABLE IF EXISTS `stt_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stt_data` (
  `id` bigint NOT NULL,
  `file_name` varchar(100) DEFAULT NULL,
  `gcs_directory` varchar(100) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `schedule_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stt_data`
--

LOCK TABLES `stt_data` WRITE;
/*!40000 ALTER TABLE `stt_data` DISABLE KEYS */;
INSERT INTO `stt_data` VALUES (0,'book_1m30s_2','recording/2023/FEBRUARY/','https://storage.cloud.google.com/stt-bucket-binu/recording/2023/FEBRUARY/book_1m30s_2',107);
/*!40000 ALTER TABLE `stt_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trouble_board`
--

DROP TABLE IF EXISTS `trouble_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trouble_board` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `category` int DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `views` int DEFAULT '0',
  `type` varchar(30) DEFAULT NULL,
  `comments` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpe750k4vmtxlmp6874lfwa5pt` (`client_id`),
  CONSTRAINT `FKpe750k4vmtxlmp6874lfwa5pt` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trouble_board`
--

LOCK TABLES `trouble_board` WRITE;
/*!40000 ALTER TABLE `trouble_board` DISABLE KEYS */;
INSERT INTO `trouble_board` VALUES (1,'2023-02-01 02:34:22','2023-02-01 02:34:22',0,'id','놀이방법 추천부탁드립니다','아이랑 어떻게 놀아주는게 좋을까요',NULL,NULL,NULL),(2,'2023-02-01 02:34:39','2023-02-01 02:34:39',0,'id','괜찮은건가요','아이가 산만합니다.',NULL,NULL,NULL),(67,'2023-02-02 06:27:31','2023-02-02 06:27:31',1,'id','가나다라','가나다라',NULL,NULL,NULL),(101,'2023-02-03 00:13:01','2023-02-03 00:13:01',3,'id','되나요??','되나?',1,NULL,NULL),(102,'2023-02-03 01:53:06','2023-02-03 01:53:06',2,'id','된다!!!','되나?',3,NULL,NULL),(107,'2023-02-03 02:47:56','2023-02-03 02:47:56',0,'id','ㅁㄴㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ','ㅁㄴㅇ',4,NULL,NULL),(109,'2023-02-03 02:52:45','2023-02-03 02:52:45',0,'id','ㅏㅏㅏ','ㅏㅏㅏ',1,NULL,NULL),(110,'2023-02-03 02:54:07','2023-02-03 02:54:07',0,'id','ㅇ','ㅇ',1,NULL,NULL),(111,'2023-02-03 02:54:14','2023-02-03 02:54:14',0,'id','ㅁㄴㅇㅁㄴㅇ','ㅁㄴㅇㅁㄴㅇㅁㄴ',87,NULL,NULL),(124,'2023-02-04 06:05:49','2023-02-04 06:05:49',0,'id','lllllllllllllllllllllllllllllllllll','llllllllllllllllllllllllllllllllllll',20,NULL,NULL),(125,'2023-02-04 07:02:43','2023-02-04 07:02:43',0,'id','ㅠㅠ','ㅠㅠ',1,NULL,NULL),(126,'2023-02-04 08:08:44','2023-02-04 08:08:44',0,'id','ㅊ','ㅊ',5,NULL,NULL),(127,'2023-02-04 08:08:59','2023-02-04 08:08:59',0,'id','ㅊ','ㅊ',6,NULL,NULL),(143,'2023-02-05 13:31:37','2023-02-05 13:31:37',3,'kimkim2','되나요??','되나?',7,NULL,NULL),(149,'2023-02-08 01:29:11','2023-02-08 01:29:11',3,'id','sddsf','dds',0,NULL,NULL),(150,'2023-02-08 01:45:37','2023-02-08 01:45:37',3,'id','sddsf','dds',0,NULL,NULL),(151,'2023-02-08 01:46:23','2023-02-08 01:46:23',3,'id','ㅇㅇ','부부ㅜ우',0,NULL,NULL),(152,'2023-02-08 01:52:46','2023-02-08 01:52:46',3,'id','ㅇㅇ','부부ㅜ우',0,NULL,NULL),(153,'2023-02-08 01:53:03','2023-02-08 01:53:03',2,'id','ㅇㄴㄹㄴㅇ','ㅇㄴㅇㄹ',0,NULL,NULL),(154,'2023-02-08 02:07:44','2023-02-08 02:07:44',0,'idd','ddddd','ddddd',3,NULL,NULL),(155,'2023-02-08 02:08:19','2023-02-08 02:08:19',0,'idd','ddddddddddddddddddddddddddd','ddddddddddddddddddddddddddd',0,NULL,NULL),(156,'2023-02-08 05:04:35','2023-02-08 05:04:35',0,'idd','ㅌㅌㅌㅌ','ㅌㅌㅌㅌ',3,NULL,NULL),(159,'2023-02-08 18:50:42','2023-02-08 18:50:42',0,'idd','ㅁ','ㅁ',0,NULL,NULL),(160,'2023-02-08 18:54:01','2023-02-08 18:54:01',0,'idd','ㅇㅇㅇ','ㅇㅇㅇ',1,NULL,NULL),(161,'2023-02-08 18:54:09','2023-02-08 18:54:09',4,'idd','ㄴㄴㄴㄴ','ㄴㄴㄴ',0,NULL,NULL),(162,'2023-02-08 18:57:40','2023-02-08 18:57:40',2,'idd','ㅅㅅ','ㅅㅅ',99,NULL,NULL),(163,'2023-02-08 21:49:52','2023-02-08 21:49:52',1,'idd','ㄴㄴㄴ','ㄴㄴㄴ',1,NULL,NULL),(164,'2023-02-08 21:50:01','2023-02-08 21:50:01',4,'idd','ㅁㅁㅁ','ㅁㅁㅁ',3,NULL,NULL),(170,'2023-02-09 03:58:54','2023-02-09 03:58:54',1,'idd','아ㅏㅏㅏ','가ㅏㅏ',80,NULL,NULL),(171,'2023-02-14 08:04:05','2023-02-14 08:04:05',2,'idd','제  마음을 알아줄 사람은 아무도 없는 걸까요?','너무 슬퍼요',33,NULL,NULL),(173,'2023-02-15 01:31:35','2023-02-15 01:31:35',3,'idd','jkln','klj',16,NULL,NULL),(174,'2023-02-15 13:41:31','2023-02-15 13:41:31',2,'idd','왜케 밀리냐구!','이거 왜케 밀려',5,NULL,NULL),(177,'2023-02-15 13:54:44','2023-02-15 13:54:44',2,'idd','되는건가','이건',9,NULL,NULL),(180,'2023-02-16 00:18:42','2023-02-16 00:18:42',0,'idd','gggggd가나다라','ggg',308,NULL,NULL),(182,'2023-02-17 01:33:08','2023-02-17 01:33:08',1,'kimkim2','힘들어요 ㅜㅜ 살려주세용','호선이가 자꾸 고백으로 혼내요 ;',7,NULL,NULL),(184,'2023-02-17 01:45:14','2023-02-17 01:45:14',2,'client','qweqwe','qweqwe',2,NULL,NULL);
/*!40000 ALTER TABLE `trouble_board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trouble_comment`
--

DROP TABLE IF EXISTS `trouble_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trouble_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `counselor_id` varchar(30) DEFAULT NULL,
  `board_id` bigint DEFAULT NULL,
  `likes` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2k32lnexr62tbdn71nygjie1a` (`counselor_id`),
  KEY `FKcihulpqo7gq5a0niy2qiyvepq` (`board_id`),
  CONSTRAINT `FK2k32lnexr62tbdn71nygjie1a` FOREIGN KEY (`counselor_id`) REFERENCES `counselor` (`id`),
  CONSTRAINT `FKcihulpqo7gq5a0niy2qiyvepq` FOREIGN KEY (`board_id`) REFERENCES `trouble_board` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trouble_comment`
--

LOCK TABLES `trouble_comment` WRITE;
/*!40000 ALTER TABLE `trouble_comment` DISABLE KEYS */;
INSERT INTO `trouble_comment` VALUES (1,'2023-02-03 01:55:12','2023-02-03 01:55:12','댓글 수정','counselor2',102,NULL),(2,'2023-02-03 01:55:31','2023-02-03 01:55:31','댓글 등록!','counselor2',101,NULL),(7,'2023-02-03 03:13:57','2023-02-03 03:13:57','아현님 멋있어요 ㅎㅎㅎ','counselor2',111,NULL),(8,'2023-02-03 03:14:10','2023-02-03 03:14:10',NULL,'counselor2',111,NULL),(35,'2023-02-05 13:55:35','2023-02-05 13:55:35','댓글 등록!','ididid',143,NULL),(36,'2023-02-05 13:56:34','2023-02-05 13:56:34','댓글 등록!','ididid',143,NULL),(37,'2023-02-05 13:59:49','2023-02-05 13:59:49','댓글 등록!','ididid',143,NULL),(38,'2023-02-05 14:18:22','2023-02-05 14:18:22','댓글 등록!','ididid',143,NULL),(39,'2023-02-05 14:21:10','2023-02-05 14:21:10','댓글 등록!','ididid',143,NULL),(40,'2023-02-05 21:02:02','2023-02-05 21:02:02','test','ididid',143,NULL),(41,'2023-02-05 21:03:07','2023-02-05 21:03:07','test','ididid',143,NULL),(42,'2023-02-05 21:06:15','2023-02-05 21:06:15','test','ididid',143,NULL),(43,'2023-02-05 21:08:42','2023-02-05 21:08:42','test','ididid',143,NULL),(44,'2023-02-05 21:09:59','2023-02-05 21:09:59','test','ididid',143,NULL),(45,'2023-02-05 21:28:05','2023-02-05 21:28:05','test','ididid',143,NULL),(46,'2023-02-05 21:28:22','2023-02-05 21:28:22','test','ididid',143,NULL),(47,'2023-02-05 21:30:11','2023-02-05 21:30:11','test','ididid',143,NULL),(48,'2023-02-06 00:04:41','2023-02-06 00:04:41','test','ididid',143,NULL),(49,'2023-02-06 00:13:06','2023-02-06 00:13:06','test','ididid',143,NULL),(50,'2023-02-06 00:59:22','2023-02-06 00:59:22','test','ididid',143,NULL),(51,'2023-02-06 01:05:29','2023-02-06 01:05:29','test','ididid',143,NULL),(52,'2023-02-06 01:09:48','2023-02-06 01:09:48','test','ididid',143,NULL),(53,'2023-02-06 01:18:33','2023-02-06 01:18:33','test','ididid',143,NULL),(54,'2023-02-06 01:21:42','2023-02-06 01:21:42','test','ididid',143,NULL),(55,'2023-02-06 01:36:35','2023-02-06 01:36:35','test','ididid',143,NULL),(56,'2023-02-06 07:19:39','2023-02-06 07:19:39','ㄴ','counselor2',124,NULL),(57,'2023-02-06 08:58:21','2023-02-06 08:58:21','댓글 등록!','ididid',1,NULL),(58,'2023-02-06 08:59:25','2023-02-06 08:59:25','댓글 등록!','ididid',143,NULL),(60,'2023-02-06 11:03:45','2023-02-06 11:03:45','댓글 등록!','ididid',143,NULL),(61,'2023-02-06 11:04:48','2023-02-06 11:04:48','댓글 등록!','ididid',143,NULL),(62,'2023-02-06 11:05:38','2023-02-06 11:05:38','댓글 등록!','ididid',143,NULL),(63,'2023-02-06 11:06:21','2023-02-06 11:06:21','댓글 등록!','ididid',143,NULL),(64,'2023-02-06 11:07:00','2023-02-06 11:07:00','댓글 등록!','ididid',143,NULL),(65,'2023-02-06 11:08:31','2023-02-06 11:08:31','댓글 등록!','ididid',143,NULL),(66,'2023-02-06 11:09:07','2023-02-06 11:09:07','댓글 등록!','ididid',143,NULL),(67,'2023-02-06 11:10:02','2023-02-06 11:10:02','댓글 등록!','ididid',143,NULL),(68,'2023-02-06 11:50:07','2023-02-06 11:50:07','댓글 등록!','ididid',143,NULL),(69,'2023-02-06 11:54:16','2023-02-06 11:54:16','댓글 등록!','ididid',143,NULL),(70,'2023-02-06 12:14:31','2023-02-06 12:14:31','댓글 등록!','ididid',143,NULL),(71,'2023-02-06 12:14:50','2023-02-06 12:14:50','댓글 등록!','ididid',143,NULL),(72,'2023-02-06 12:31:59','2023-02-06 12:31:59','댓글 등록!','ididid',143,NULL),(73,'2023-02-06 12:39:53','2023-02-06 12:39:53','댓글 등록!','ididid',143,NULL),(74,'2023-02-06 13:18:10','2023-02-06 13:18:10','댓글 등록!','ididid',143,NULL),(75,'2023-02-06 13:19:42','2023-02-06 13:19:42','댓글 등록!','ididid',143,NULL),(76,'2023-02-06 13:20:31','2023-02-06 13:20:31','댓글 등록!','ididid',143,NULL),(77,'2023-02-06 13:40:01','2023-02-06 13:40:01','댓글 등록!','ididid',143,NULL),(78,'2023-02-07 00:19:58','2023-02-07 00:19:58','댓글 등록!','ididid',143,NULL),(79,'2023-02-07 00:20:27','2023-02-07 00:20:27','댓글 등록!','ididid',143,NULL),(80,'2023-02-07 00:20:44','2023-02-07 00:20:44','댓글 등록!','ididid',143,NULL),(81,'2023-02-07 00:22:13','2023-02-07 00:22:13','댓글 등록!','ididid',143,NULL),(82,'2023-02-07 00:22:44','2023-02-07 00:22:44','댓글 등록!','ididid',143,NULL),(83,'2023-02-07 00:23:57','2023-02-07 00:23:57','댓글 등록!','ididid',143,NULL),(84,'2023-02-07 00:39:21','2023-02-07 00:39:21','댓글 등록!','ididid',143,NULL),(85,'2023-02-07 00:40:05','2023-02-07 00:40:05','댓글 등록!','ididid',143,NULL),(86,'2023-02-07 00:40:55','2023-02-07 00:40:55','댓글 등록!','ididid',143,NULL),(87,'2023-02-07 00:41:42','2023-02-07 00:41:42','댓글 등록!','ididid',143,NULL),(88,'2023-02-07 00:45:38','2023-02-07 00:45:38','댓글 등록!','ididid',143,NULL),(89,'2023-02-07 00:47:19','2023-02-07 00:47:19','댓글 등록!','ididid',143,NULL),(90,'2023-02-07 00:47:46','2023-02-07 00:47:46','댓글 등록!','ididid',143,NULL),(91,'2023-02-07 00:56:06','2023-02-07 00:56:06','댓글 등록!','ididid',143,NULL),(92,'2023-02-07 01:09:51','2023-02-07 01:09:51','댓글 등록!','ididid',143,NULL),(93,'2023-02-07 01:13:19','2023-02-07 01:13:19','댓글 등록!','ididid',143,NULL),(94,'2023-02-07 01:17:38','2023-02-07 01:17:38','댓글 등록!','ididid',143,NULL),(95,'2023-02-07 01:19:21','2023-02-07 01:19:21','댓글 등록!','ididid',143,NULL),(96,'2023-02-07 01:29:05','2023-02-07 01:29:05','댓글 등록!','ididid',143,NULL),(97,'2023-02-07 01:29:58','2023-02-07 01:29:58','댓글 등록!','ididid',143,NULL),(98,'2023-02-07 01:34:06','2023-02-07 01:34:06','댓글 등록!','ididid',143,NULL),(99,'2023-02-07 01:35:54','2023-02-07 01:35:54','댓글 등록!','ididid',143,NULL),(100,'2023-02-07 01:36:47','2023-02-07 01:36:47','댓글 등록!','ididid',143,NULL),(101,'2023-02-07 01:38:23','2023-02-07 01:38:23','댓글 등록!','ididid',143,NULL),(102,'2023-02-07 01:38:32','2023-02-07 01:38:32','댓글 등록!','ididid',143,NULL),(103,'2023-02-07 05:45:43','2023-02-07 05:45:43','댓글 등록!','ididid',143,NULL),(104,'2023-02-07 05:55:14','2023-02-07 05:55:14','댓글 등록!','ididid',143,NULL),(105,'2023-02-07 05:56:37','2023-02-07 05:56:37','댓글 등록!','ididid',143,NULL),(106,'2023-02-07 06:01:45','2023-02-07 06:01:45','댓글 등록!','ididid',143,NULL),(107,'2023-02-07 06:09:07','2023-02-07 06:09:07','댓글 등록!','ididid',143,NULL),(108,'2023-02-07 06:09:19','2023-02-07 06:09:19','댓글 등록!','ididid',143,NULL),(109,'2023-02-07 07:03:30','2023-02-07 07:03:30','댓글 등록!','ididid',143,NULL),(110,'2023-02-07 07:32:06','2023-02-07 07:32:06','댓글 등록!','ididid',143,NULL),(111,'2023-02-07 07:33:12','2023-02-07 07:33:12','댓글 등록!','ididid',143,NULL),(112,'2023-02-07 07:34:59','2023-02-07 07:34:59','댓글 등록!','ididid',143,NULL),(113,'2023-02-07 07:35:56','2023-02-07 07:35:56','댓글 등록!','ididid',143,NULL),(114,'2023-02-07 07:41:06','2023-02-07 07:41:06','댓글 등록!','ididid',143,NULL),(115,'2023-02-07 07:45:26','2023-02-07 07:45:26','댓글 등록!','ididid',143,NULL),(116,'2023-02-07 07:46:51','2023-02-07 07:46:51','댓글 등록!','ididid',143,NULL),(117,'2023-02-07 07:48:21','2023-02-07 07:48:21','댓글 등록!','ididid',143,NULL),(118,'2023-02-07 07:49:40','2023-02-07 07:49:40','댓글 등록!','ididid',143,NULL),(119,'2023-02-07 07:52:06','2023-02-07 07:52:06','댓글 등록!','ididid',143,NULL),(120,'2023-02-07 07:53:23','2023-02-07 07:53:23','댓글 등록!','ididid',143,NULL),(121,'2023-02-07 07:54:21','2023-02-07 07:54:21','댓글 등록!','ididid',143,NULL),(122,'2023-02-07 07:57:01','2023-02-07 07:57:01','댓글 등록!','ididid',143,NULL),(123,'2023-02-07 08:00:21','2023-02-07 08:00:21','댓글 등록!','ididid',143,NULL),(126,'2023-02-12 06:53:10','2023-02-12 06:53:10','댓글 등록!','ididid',143,NULL),(127,'2023-02-15 01:41:52','2023-02-15 01:41:52','ㅇㅇㅇㅇㅇ','gggo',173,NULL),(128,'2023-02-15 01:43:12','2023-02-15 01:43:12','ㅂㅂㅂ','gggo',173,NULL),(129,'2023-02-15 02:06:48','2023-02-15 02:06:48','제가 도와드릴게요 연락 주세요','gggo',171,NULL),(131,'2023-02-16 22:13:50','2023-02-16 22:13:50','댓글 수정','counselor01',180,NULL),(132,'2023-02-16 22:18:44','2023-02-16 22:18:44','ㅂㅈㄷㅂㅈㄷ','counselor01',180,NULL),(133,'2023-02-16 22:18:47','2023-02-16 22:18:47','ㅂㅈㄷㅂㅈㄷ','counselor01',180,NULL),(134,'2023-02-17 01:34:44','2023-02-17 01:34:44','기다려라 ~ 내가 갈게','counselor01',182,NULL);
/*!40000 ALTER TABLE `trouble_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trouble_comment_like`
--

DROP TABLE IF EXISTS `trouble_comment_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trouble_comment_like` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `client_id` varchar(255) DEFAULT NULL,
  `trouble_comment_id` bigint DEFAULT NULL,
  `comment_like` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfgeiiko6vxeo793ou2oql1xga` (`trouble_comment_id`),
  KEY `FKsauh6wro18pt4jexve66qlj41` (`comment_like`),
  CONSTRAINT `FKfgeiiko6vxeo793ou2oql1xga` FOREIGN KEY (`trouble_comment_id`) REFERENCES `trouble_comment` (`id`),
  CONSTRAINT `FKsauh6wro18pt4jexve66qlj41` FOREIGN KEY (`comment_like`) REFERENCES `trouble_comment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trouble_comment_like`
--

LOCK TABLES `trouble_comment_like` WRITE;
/*!40000 ALTER TABLE `trouble_comment_like` DISABLE KEYS */;
INSERT INTO `trouble_comment_like` VALUES (1,'id',1,NULL);
/*!40000 ALTER TABLE `trouble_comment_like` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-17 11:17:27
