-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1:3306
-- Üretim Zamanı: 07 Oca 2024, 18:54:17
-- Sunucu sürümü: 8.0.31
-- PHP Sürümü: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `veritabani`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `books`
--

DROP TABLE IF EXISTS `books`;
CREATE TABLE IF NOT EXISTS `books` (
  `bookId` int NOT NULL AUTO_INCREMENT,
  `bookName` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `bookAuthor` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `bookTheme` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `bookStatus` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `bookExistNum` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`bookId`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `books`
--

INSERT INTO `books` (`bookId`, `bookName`, `bookAuthor`, `bookTheme`, `bookStatus`, `bookExistNum`) VALUES
(1, 'Kuyucaklı Yusuf', 'Sabahattin Ali', 'Türk Klasikleri', 'Rafta', '1'),
(2, 'Sefiller', 'Victor Hugo', 'Dünya Klasikleri', 'Rafta', '1'),
(3, 'Cehennem', 'Dan Brown', 'Polisiye', 'Rafta', '1'),
(5, 'Çalıkuşu', 'Reşat Nuri Güntekin', 'Köy Edebiyatı', 'Rafta', '0');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `details`
--

DROP TABLE IF EXISTS `details`;
CREATE TABLE IF NOT EXISTS `details` (
  `dId` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `surname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `age` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `no` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`dId`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `details`
--

INSERT INTO `details` (`dId`, `userName`, `name`, `surname`, `email`, `age`, `no`) VALUES
(1, 'mehmetemre', 'Mehmet Emre', 'Yeşilyurt', 'mehmet-2014emre@hotmail.com', '20', '5324888580');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  `userRole` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `takenBook` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `existNum` varchar(1) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `users`
--

INSERT INTO `users` (`Id`, `username`, `password`, `userRole`, `takenBook`, `existNum`) VALUES
(1, 'admin', '123321', 'Personel', '-', '1'),
(2, 'mehmetemre', '123321', 'Öğrenci', '-', '1'),
(3, 'fatih', '123321', 'Öğretim Üyesi', '-', '1'),
(4, 'ugur', '123321', 'Öğrenci', '-', '0'),
(5, 'eren', '123321', 'Öğrenci', '-', '0'),
(6, 'yakup', '123321', 'Öğrenci', '-', '0'),
(7, 'musa', '123321', 'Öğrenci', '-', '0');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
