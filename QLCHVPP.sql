CREATE DATABASE  IF NOT EXISTS `newqlchvpp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `newqlchvpp`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: newqlchvpp
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
-- Table structure for table `chitietdonnhaphang`
--

DROP TABLE IF EXISTS `chitietdonnhaphang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietdonnhaphang` (
  `MaDNH` varchar(10) NOT NULL,
  `MaSP` varchar(10) NOT NULL,
  `SoLuong` int DEFAULT NULL,
  `DonGia` decimal(11,1) DEFAULT NULL,
  PRIMARY KEY (`MaDNH`,`MaSP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietdonnhaphang`
--

LOCK TABLES `chitietdonnhaphang` WRITE;
/*!40000 ALTER TABLE `chitietdonnhaphang` DISABLE KEYS */;
/*!40000 ALTER TABLE `chitietdonnhaphang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitiethoadon`
--

DROP TABLE IF EXISTS `chitiethoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitiethoadon` (
  `MaHD` varchar(10) NOT NULL,
  `MaSP` varchar(10) NOT NULL,
  `Soluong` int NOT NULL,
  `TongTien` decimal(18,1) NOT NULL,
  PRIMARY KEY (`MaHD`,`MaSP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethoadon`
--

LOCK TABLES `chitiethoadon` WRITE;
/*!40000 ALTER TABLE `chitiethoadon` DISABLE KEYS */;
/*!40000 ALTER TABLE `chitiethoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donnhaphang`
--

DROP TABLE IF EXISTS `donnhaphang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donnhaphang` (
  `MaDNH` varchar(10) NOT NULL,
  `MaNV` varchar(10) NOT NULL,
  `MaNSX` varchar(10) NOT NULL,
  `NgayLap` datetime NOT NULL,
  `TrangThai` varchar(20) NOT NULL,
  PRIMARY KEY (`MaDNH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donnhaphang`
--

LOCK TABLES `donnhaphang` WRITE;
/*!40000 ALTER TABLE `donnhaphang` DISABLE KEYS */;
/*!40000 ALTER TABLE `donnhaphang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `fullproductinfo`
--

DROP TABLE IF EXISTS `fullproductinfo`;
/*!50001 DROP VIEW IF EXISTS `fullproductinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `fullproductinfo` AS SELECT 
 1 AS `MaSP`,
 1 AS `MaLoaiSP`,
 1 AS `MaNSX`,
 1 AS `TenSP`,
 1 AS `DonViTinh`,
 1 AS `GiaTien`,
 1 AS `GiaNhap`,
 1 AS `SoLuong`,
 1 AS `LoiNhuan`,
 1 AS `TinhTrang`,
 1 AS `HinhAnh`,
 1 AS `TenLoaiSP`,
 1 AS `TenNSX`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `MaHD` varchar(10) NOT NULL,
  `MaNV` varchar(10) DEFAULT NULL,
  `MaKH` varchar(10) DEFAULT NULL,
  `NgayBanHang` date DEFAULT NULL,
  `TrangThai` varchar(21) NOT NULL DEFAULT 'Chưa thanh toán',
  `TriGiaHoaDon` decimal(18,1) NOT NULL,
  `TienKhachTra` decimal(18,1) DEFAULT NULL,
  `TienThua` decimal(18,1) DEFAULT NULL,
  PRIMARY KEY (`MaHD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `MaKH` varchar(10) NOT NULL,
  `Hoten` varchar(255) DEFAULT NULL,
  `Sdt` varchar(10) DEFAULT NULL,
  `GioiTinh` varchar(10) NOT NULL,
  `NgayDangKi` date NOT NULL,
  `TongChiTieu` decimal(11,1) NOT NULL DEFAULT '0.0',
  PRIMARY KEY (`MaKH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaisanpham`
--

DROP TABLE IF EXISTS `loaisanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loaisanpham` (
  `MaLoaiSP` varchar(10) NOT NULL,
  `TenLoaiSP` varchar(255) NOT NULL,
  PRIMARY KEY (`MaLoaiSP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaisanpham`
--

LOCK TABLES `loaisanpham` WRITE;
/*!40000 ALTER TABLE `loaisanpham` DISABLE KEYS */;
/*!40000 ALTER TABLE `loaisanpham` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `nhanvien` VALUES ('NV0001','Nguyễn Thiện Luân','2003-12-08','0123456789','Nam','Lâm Đồng, Việt Nam','2024-03-27','Đang làm','username',NULL,'password','Admin');
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhasanxuat`
--

DROP TABLE IF EXISTS `nhasanxuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhasanxuat` (
  `MaNSX` varchar(10) NOT NULL,
  `TenNSX` varchar(255) NOT NULL,
  `DiaChi` varchar(255) DEFAULT NULL,
  `Sdt` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`MaNSX`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhasanxuat`
--

LOCK TABLES `nhasanxuat` WRITE;
/*!40000 ALTER TABLE `nhasanxuat` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhasanxuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanpham` (
  `MaSP` varchar(10) NOT NULL,
  `MaLoaiSP` varchar(10) NOT NULL,
  `MaNSX` varchar(10) DEFAULT NULL,
  `TenSP` varchar(255) NOT NULL,
  `DonViTinh` varchar(20) NOT NULL,
  `GiaTien` decimal(10,1) NOT NULL,
  `GiaNhap` decimal(10,1) NOT NULL,
  `SoLuong` int NOT NULL,
  `LoiNhuan` int NOT NULL,
  `TinhTrang` varchar(21) NOT NULL,
  `HinhAnh` blob,
  PRIMARY KEY (`MaSP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'newqlchvpp'
--

--
-- Dumping routines for database 'newqlchvpp'
--
/*!50003 DROP PROCEDURE IF EXISTS `PC_CapNhatKhachHang` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PC_CapNhatKhachHang`(
    IN p_MaKH varchar(10),
    IN p_Hoten varchar(255),
    IN p_Sdt varchar(20),
    IN p_GioiTinh varchar(10)
)
BEGIN
    IF EXISTS (SELECT 1 FROM KhachHang WHERE Sdt = p_Sdt AND MaKH <> p_MaKH) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Số điện thoại đã tồn tại';
    ELSE
        UPDATE KhachHang 
        SET Hoten = p_Hoten, Sdt = p_Sdt, GioiTinh = p_GioiTinh
        WHERE MaKH = p_MaKH;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PC_ThemKhachHang` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PC_ThemKhachHang`(
    IN p_Hoten varchar(255),
    IN p_Sdt varchar(20),
    IN p_GioiTinh varchar(10)
)
BEGIN
    DECLARE v_MaKH varchar(10);

    IF EXISTS (SELECT 1 FROM KhachHang WHERE Sdt = p_Sdt) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Số điện thoại đã tồn tại';
    ELSE
        SELECT CONCAT('KH', LPAD(IFNULL(MAX(CAST(SUBSTRING(MaKH, 3) AS UNSIGNED)), 0) + 1, 3, '0')) INTO v_MaKH
        FROM KhachHang;

        INSERT INTO KhachHang (MaKH, Hoten, Sdt, GioiTinh, NgayDangKi, TongChiTieu)
        VALUES (v_MaKH, p_Hoten, p_Sdt, p_GioiTinh, CURDATE(), 0);
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
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
    IN p_TENTK VARCHAR(20),
    IN p_HINHANH BLOB,
    IN p_QUYEN VARCHAR(30)
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

            INSERT INTO NhanVien(MaNV, HoTen, Sdt, NgaySinh, GioiTinh, DiaChi, NgayTuyenDung, TrangThai, TenTK, HinhAnh, MatKhau, Quyen)
            VALUES(v_MANV, p_HOTEN, p_SDT, p_NGAYSINH, p_GIOITINH, p_DIACHI, p_NGAYTUYENDUNG, p_TRANGTHAI, p_TENTK, p_HINHANH, 'password', p_QUYEN);
        END IF;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ThemDonNhapHang` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ThemDonNhapHang`(
    IN p_MaNV varchar(10),
    IN p_MaNSX varchar(10)
)
BEGIN
    DECLARE v_MaxID int;
    DECLARE v_MaDNH varchar(10);
    DECLARE v_TrangThai varchar(20);
    DECLARE v_NgayLap datetime;

    -- Đặt TrangThai là 'Chưa nhập'
    SET v_TrangThai = 'Chưa nhập';

    -- Đặt NgayLap là thời gian hiện tại
    SET v_NgayLap = NOW();

    -- Lấy ID lớn nhất hiện tại từ bảng DonNhapHang
    SELECT MAX(CAST(SUBSTRING(MaDNH, 4, LENGTH(MaDNH) - 3) AS UNSIGNED)) INTO v_MaxID FROM DonNhapHang;

    -- Tăng ID lên 1
    SET v_MaxID = IFNULL(v_MaxID, 0) + 1;

    -- Tạo MaDNH mới với định dạng mong muốn
    SET v_MaDNH = CONCAT('DNH', LPAD(v_MaxID, 4, '0'));

    -- Thêm bản ghi mới vào bảng DonNhapHang
    INSERT INTO DonNhapHang(MaDNH, MaNV, MaNSX, NgayLap, TrangThai)
    VALUES (v_MaDNH, p_MaNV, p_MaNSX, v_NgayLap, v_TrangThai);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ThemHoaDonMoi` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ThemHoaDonMoi`(
    IN p_MaNV varchar(10),
    IN p_MaKH varchar(10),
    IN p_TrangThai varchar(21),
    IN p_TriGiaHoaDon decimal(18, 1),
    IN p_TienKhachTra decimal(18, 1),
    IN p_TienThua decimal(18, 1)
)
BEGIN
    DECLARE v_MaHD varchar(10);

    SELECT CONCAT('HD', LPAD(IFNULL(MAX(CAST(SUBSTRING(MaHD, 3) AS UNSIGNED)), 0) + 1, 4, '0')) INTO v_MaHD
    FROM HoaDon;

    INSERT INTO HoaDon (MaHD, MaNV, MaKH, NgayBanHang, TrangThai, TriGiaHoaDon, TienKhachTra, TienThua)
    VALUES (v_MaHD, p_MaNV, p_MaKH, NOW(), p_TrangThai, p_TriGiaHoaDon, p_TienKhachTra, p_TienThua);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ThemLoaiSanPham` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ThemLoaiSanPham`(
    IN p_TenLoaiSP varchar(255)
)
BEGIN
    DECLARE v_MaLoaiSP varchar(10);

    SELECT CONCAT('LSP', LPAD(IFNULL(MAX(CAST(SUBSTRING(MaLoaiSP, 4) AS UNSIGNED)), 0) + 1, 4, '0')) INTO v_MaLoaiSP
    FROM LoaiSanPham;

    INSERT INTO LoaiSanPham (MaLoaiSP, TenLoaiSP)
    VALUES (v_MaLoaiSP, p_TenLoaiSP);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ThemNhaSanXuat` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ThemNhaSanXuat`(
    IN p_TenNSX varchar(255),
    IN p_DiaChi varchar(255),
    IN p_Sdt varchar(10)
)
BEGIN
    DECLARE v_MaNSX varchar(10);

    IF EXISTS (SELECT 1 FROM NhaSanXuat WHERE Sdt = p_Sdt) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Số điện thoại đã tồn tại';
    ELSE
        SELECT CONCAT('NSX', LPAD(IFNULL(MAX(CAST(SUBSTRING(MaNSX, 4) AS UNSIGNED)), 0) + 1, 4, '0')) INTO v_MaNSX
        FROM NhaSanXuat;

        INSERT INTO NhaSanXuat (MaNSX, TenNSX, DiaChi, Sdt)
        VALUES (v_MaNSX, p_TenNSX, p_DiaChi, p_Sdt);
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ThemSPMOI` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ThemSPMOI`(
    IN p_TenSP varchar(255),
    IN p_MaLoaiSP varchar(10),
    IN p_TenLoaiSP varchar(255),
    IN p_TenNSX varchar(255),
    IN p_DonViTinh varchar(20),
    IN p_GiaTien decimal(10,1),
    IN p_GiaNhap decimal(10,1),
    IN p_SoLuong int,
    IN p_LoiNhuan int,
    IN p_TinhTrang varchar(21),
    IN p_HinhAnh BLOB
)
BEGIN
    DECLARE v_MaSP varchar(10);
    DECLARE v_MaNSX varchar(10);

    START TRANSACTION;
    BEGIN
        SELECT CONCAT('SP', LPAD(IFNULL(MAX(CAST(SUBSTRING(MaSP, 3) AS UNSIGNED)), 0) + 1, 4, '0')) INTO v_MaSP
        FROM SanPham;

        INSERT INTO SanPham (MaSP, MaLoaiSP, TenSP, DonViTinh, GiaTien, GiaNhap, SoLuong, LoiNhuan, TinhTrang, HinhAnh)
        VALUES (v_MaSP, p_MaLoaiSP, p_TenSP, p_DonViTinh, p_GiaTien, p_GiaNhap, p_SoLuong, p_LoiNhuan, p_TinhTrang, p_HinhAnh);

        COMMIT;
    END;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `UpdateNhanVien` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateNhanVien`(IN p_MaNV varchar(10), IN p_Sdt char(10), IN p_TenTK varchar(20))
BEGIN
    IF EXISTS (SELECT 1 FROM NhanVien WHERE Sdt = p_Sdt AND MaNV <> p_MaNV) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Đã tồn tại số điện thoại này';
    ELSEIF EXISTS (SELECT 1 FROM NhanVien WHERE TenTK = p_TenTK AND MaNV <> p_MaNV) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Đã tồn tại tên tài khoản này';
    ELSE
        UPDATE NhanVien SET Sdt = p_Sdt, TenTK = p_TenTK WHERE MaNV = p_MaNV;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `UpdateNhaSanXuat` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateNhaSanXuat`(
    IN p_MaNSX varchar(10),
    IN p_TenNSX varchar(255),
    IN p_DiaChi varchar(255),
    IN p_Sdt varchar(10)
)
BEGIN
    IF EXISTS (SELECT 1 FROM NhaSanXuat WHERE Sdt = p_Sdt AND MaNSX <> p_MaNSX) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Số điện thoại đã tồn tại';
    ELSE
        UPDATE NhaSanXuat 
        SET TenNSX = p_TenNSX, DiaChi = p_DiaChi, Sdt = p_Sdt
        WHERE MaNSX = p_MaNSX;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `fullproductinfo`
--

/*!50001 DROP VIEW IF EXISTS `fullproductinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `fullproductinfo` AS select `sanpham`.`MaSP` AS `MaSP`,`sanpham`.`MaLoaiSP` AS `MaLoaiSP`,`sanpham`.`MaNSX` AS `MaNSX`,`sanpham`.`TenSP` AS `TenSP`,`sanpham`.`DonViTinh` AS `DonViTinh`,`sanpham`.`GiaTien` AS `GiaTien`,`sanpham`.`GiaNhap` AS `GiaNhap`,`sanpham`.`SoLuong` AS `SoLuong`,`sanpham`.`LoiNhuan` AS `LoiNhuan`,`sanpham`.`TinhTrang` AS `TinhTrang`,`sanpham`.`HinhAnh` AS `HinhAnh`,`loaisanpham`.`TenLoaiSP` AS `TenLoaiSP`,`nhasanxuat`.`TenNSX` AS `TenNSX` from ((`sanpham` join `loaisanpham` on((`sanpham`.`MaLoaiSP` = `loaisanpham`.`MaLoaiSP`))) join `nhasanxuat` on((`sanpham`.`MaNSX` = `nhasanxuat`.`MaNSX`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-13 11:37:00
