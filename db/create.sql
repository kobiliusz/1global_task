-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Wersja serwera:               11.8.2-MariaDB - mariadb.org binary distribution
-- Serwer OS:                    Win64
-- HeidiSQL Wersja:              12.10.0.7000
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Zrzut struktury bazy danych devices
CREATE DATABASE IF NOT EXISTS `devices` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci */;
USE `devices`;

-- Zrzut struktury tabela devices.brand
CREATE TABLE IF NOT EXISTS `brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Zrzucanie danych dla tabeli devices.brand: ~0 rows (około)
DELETE FROM `brand`;

-- Zrzut struktury tabela devices.device
CREATE TABLE IF NOT EXISTS `device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL DEFAULT '',
  `brand` bigint(20) NOT NULL,
  `state` tinyint(4) NOT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_device_brand` (`brand`),
  KEY `FK_device_state` (`state`),
  CONSTRAINT `FK_device_brand` FOREIGN KEY (`brand`) REFERENCES `brand` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_device_state` FOREIGN KEY (`state`) REFERENCES `state` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Zrzucanie danych dla tabeli devices.device: ~0 rows (około)
DELETE FROM `device`;

-- Zrzut struktury tabela devices.state
CREATE TABLE IF NOT EXISTS `state` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Zrzucanie danych dla tabeli devices.state: ~3 rows (około)
DELETE FROM `state`;
INSERT INTO `state` (`id`, `name`) VALUES
	(1, 'available'),
	(2, 'in-use'),
	(3, 'inactive');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
