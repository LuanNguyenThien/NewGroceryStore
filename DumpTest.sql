CREATE DATABASE  IF NOT EXISTS `qlchvpp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `qlchvpp`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: qlchvpp
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `MaNV` varchar(10) NOT NULL,
  `HoTen` varchar(255) DEFAULT NULL,
  `NgaySinh` date NOT NULL,
  `Sdt` char(10) DEFAULT NULL,
  `GioiTinh` varchar(3) DEFAULT NULL,
  `DiaChi` varchar(255) DEFAULT NULL,
  `NgayTuyenDung` date DEFAULT NULL,
  `TrangThai` varchar(20) DEFAULT 'Đang làm',
  `MaCV` varchar(10) NOT NULL,
  `TenTK` varchar(20) DEFAULT NULL,
  `HinhAnh` blob,
  `MatKhau` varchar(31) DEFAULT 'password',
  `Quyen` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`MaNV`),
  UNIQUE KEY `TenTK` (`TenTK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES ('NV0001','Nguyễn Thiện Luân','2003-12-08','0123456789','Nam','Lâm Đồng, Việt Nam','2023-02-26','Đang làm','CV0002','thienluan',NULL,'password','Staff');
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'qlchvpp'
--

--
-- Dumping routines for database 'qlchvpp'
--
/*!50003 DROP PROCEDURE IF EXISTS `spThemNhanVien` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `spThemNhanVien`(
    IN p_HOTEN VARCHAR(255),
    IN p_SDT CHAR(10),
    IN p_NGAYSINH DATE,
    IN p_GIOITINH VARCHAR(3),
    IN p_DIACHI VARCHAR(255),
    IN p_NGAYTUYENDUNG DATE,
    IN p_TRANGTHAI VARCHAR(20),
    IN p_MACV VARCHAR(10),
    IN p_TENTK VARCHAR(20),
    IN p_HINHANH BLOB
)
BEGIN
    DECLARE v_MANV VARCHAR(10);
    DECLARE v_Count INT;

    SELECT COUNT(*) INTO v_Count FROM NhanVien WHERE TenTK = p_TENTK;

    IF v_Count > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Tài khoản đã tồn tại rồi';
    ELSE
        SELECT COUNT(*) INTO v_Count FROM NhanVien WHERE Sdt = p_SDT;
        IF v_Count > 0 THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Số điện thoại đã tồn tại rồi';
        ELSE
            SELECT CONCAT('NV', LPAD(COUNT(*) + 1, 4, '0')) INTO v_MANV FROM NhanVien;

            INSERT INTO NhanVien(MaNV, HoTen, Sdt, NgaySinh, GioiTinh, DiaChi, NgayTuyenDung, TrangThai, MaCV, TenTK, HinhAnh, MatKhau, Quyen)
            VALUES(v_MANV, p_HOTEN, p_SDT, p_NGAYSINH, p_GIOITINH, p_DIACHI, p_NGAYTUYENDUNG, p_TRANGTHAI, p_MACV, p_TENTK, p_HINHANH, 'password', IF(p_MACV = 'CV0001', 'Admin', 'Staff'));
        END IF;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-26 22:36:30
