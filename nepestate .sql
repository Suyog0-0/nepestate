-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2025 at 09:04 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nepestate`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `AdminID` int(3) NOT NULL,
  `Admin_FirstName` varchar(30) NOT NULL,
  `Admin_LastName` varchar(30) NOT NULL,
  `Admin_Username` varchar(30) NOT NULL,
  `Admin_EmailAddress` varchar(50) NOT NULL,
  `Admin_Password` varchar(100) NOT NULL,
  `Admin_ProfilePicture` text NOT NULL,
  `Admin_PhoneNumber` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`AdminID`, `Admin_FirstName`, `Admin_LastName`, `Admin_Username`, `Admin_EmailAddress`, `Admin_Password`, `Admin_ProfilePicture`, `Admin_PhoneNumber`) VALUES
(1, 'Sansraya', 'Pradhanang', 'Sansraya', 'pradhanang.sansraya1612@gmail.com', 'Wasd123@', '\"\"', '9803421026'),
(2, 'Sachhyam', 'Nyachhyon', 'Nacho', 'sachhyam@gmail.com', 'Qwerty123@', '\"\"', '9861880405'),
(3, 'Rabina', 'Lama', 'Rabina12', 'rabina.lama@gmail.com', 'Bestadmin123@', '\"\"', '9803421025');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `CustomerID` int(3) NOT NULL,
  `Customer_FirstName` varchar(30) NOT NULL,
  `Customer_LastName` varchar(30) NOT NULL,
  `Customer_ProfilePicture` text NOT NULL,
  `Customer_DoB` varchar(30) NOT NULL,
  `Customer_Username` varchar(50) NOT NULL,
  `Customer_EmailAddress` varchar(50) NOT NULL,
  `Customer_Password` varchar(255) NOT NULL,
  `Customer_PhoneNumber` varchar(15) NOT NULL,
  `Customer_Description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`CustomerID`, `Customer_FirstName`, `Customer_LastName`, `Customer_ProfilePicture`, `Customer_DoB`, `Customer_Username`, `Customer_EmailAddress`, `Customer_Password`, `Customer_PhoneNumber`, `Customer_Description`) VALUES
(1, 'Sansraya', 'Pradhanang', 'default.jpg', '10-7-2004', 'IzMizu', 'pradhanang.sansraya@gmail.com', 'oD3mF9DmIdwhgBUpQqzYp/DxYfgR/3a9qhNldiz+aJEw/0ZBcrMlpz2x1xJGMMoTZHCiCA==', '9803421026', NULL),
(2, 'Sachhyam', 'Nyachhyon', 'default.jpg', '14-9-2005', 'Nacho', 'sachhyam.nyachhyon@gmail.com', 'bp8BSMqcrBMYiK8ej2pjs4/7WgRU82VBv6SGD8d8BO+xWZv0ylVhzPoRS8bwJDCU4iUrSpxR', '9848682001', NULL),
(3, 'Ina', 'Adhikari', 'default.jpg', '10-3-2006', 'Inaaa', 'ina.adhikari@gmail.com', 'L4Fu89r0bD5abq/Z6g/hik/YH2A/nPsIBCmiXXI9qF4IWtcaD3IPFn6Wpg8d4lXqcZQqdvwZ', '9841258986', NULL),
(4, 'Suyog', 'Sigdel', 'default.jpg', '12-8-2004', 'TFace', 'suyog.sigdel@gmail.com', '24b/Nb3csFAvBNbLKcVktWRc5rrTclRGWrCkEHrwpiOumgzeDGOkhEubLKV6sdv6YcCJW8D4OB84kCsrVQ==', '9803421025', NULL),
(5, 'Akshat', 'Dali', 'default.jpg', '4-5-2003', 'Elio', 'akshat.dali@gmail.com', '9hG6FZTkxJNZakCexJMvod3zVW3+aY49i29OSpqCicK9TDfYyAx0RRmlAeAZ92vsqVXBHf07', '9801114567', NULL),
(6, 'Gaurav', 'Koirala', '/images/profiles/profile.png', '3-4-1929', 'Gaurav', 'gauri.koirala@gmail.com', '0X8icoRdzhkvG4U9AOXLLaPikMO1DQ6P1HoDPW+nzV8k5LjZcT3JGgPlsO48m82xEheIkg==', '9861880405', 'Hello Everyone!');

-- --------------------------------------------------------

--
-- Table structure for table `customers_buyers`
--

CREATE TABLE `customers_buyers` (
  `Customer_ProfilePicture` text NOT NULL,
  `CustomerID` int(3) NOT NULL,
  `Customer_FirstName` varchar(30) NOT NULL,
  `Customer_Username` varchar(50) NOT NULL,
  `Customer_PhoneNumber` varchar(15) NOT NULL,
  `PropertyID` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers_buyers`
--

INSERT INTO `customers_buyers` (`Customer_ProfilePicture`, `CustomerID`, `Customer_FirstName`, `Customer_Username`, `Customer_PhoneNumber`, `PropertyID`) VALUES
('default', 11, 'Gaurav', 'Gaurav', '9840366440', 10),
('default', 11, 'Gaurav', 'Gaurav', '9840366440', 10),
('default', 11, 'Gaurav', 'Gaurav', '9840366440', 11),
('default', 19, 'Ina', 'crackhead', '9840366440', 15),
('default', 9, 'Ina', 'crackhead', '9840366440', 9);

-- --------------------------------------------------------

--
-- Table structure for table `favorites`
--

CREATE TABLE `favorites` (
  `RoleID` int(3) NOT NULL,
  `FavoritesID` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `favorites`
--

INSERT INTO `favorites` (`RoleID`, `FavoritesID`) VALUES
(8, 17);

-- --------------------------------------------------------

--
-- Table structure for table `property`
--

CREATE TABLE `property` (
  `PropertyID` int(3) NOT NULL,
  `Property_Title` varchar(255) NOT NULL,
  `Property_Type` varchar(15) NOT NULL,
  `Property_Price` int(11) NOT NULL,
  `Property_Area` float NOT NULL,
  `Property_Address` varchar(70) NOT NULL,
  `Property_City` varchar(50) NOT NULL,
  `Property_Status` varchar(30) NOT NULL,
  `Property_Description` varchar(255) NOT NULL,
  `Property_Amentities` varchar(100) NOT NULL,
  `Property_DateAdded` date NOT NULL,
  `Property_Photos` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `property`
--

INSERT INTO `property` (`PropertyID`, `Property_Title`, `Property_Type`, `Property_Price`, `Property_Area`, `Property_Address`, `Property_City`, `Property_Status`, `Property_Description`, `Property_Amentities`, `Property_DateAdded`, `Property_Photos`) VALUES
(17, '3 BHK Apartment in Lazimpat', 'appartment', 18000000, 1350, 'Lazimpat', 'Kathmandu', 'Available', 'A spacious 3-bedroom apartment located in the heart of Kathmandu with modern amenities and beautiful city views. Ideal for families.', 'lift,dining-room,fire-place,solar-water,security,drainage,washing-machine', '2025-05-22', '/images/property/house2.jpg'),
(18, 'Modern House with Garden in Lakeside', 'house', 9500000, 2500, 'NewRoad', 'Pokhara', 'Available', ' A well-designed 2-storey house with ample garden space, located just 5 minutes walk from Phewa Lake.', 'parking,cafeteria,gym,fire-alarm,security,jacuzzi,kitchen', '2025-05-22', '/images/property/house1.jpeg'),
(19, 'Luxurious 3-BHK Apartment in Prime Lazimpat Location with Modern Amenities', 'appartment', 18000000, 1350, 'Lazimpat', 'Kathmandu', 'Available', 'This beautifully designed 3-bedroom apartment is located in the prestigious Lazimpat area of Kathmandu. The property boasts spacious interiors, an open kitchen, two balconies, and large windows that allow plenty of natural light. ', 'swimming-pool,gym,security,jacuzzi,kitchen', '2025-05-22', '/images/property/house1.jpeg'),
(20, 'Elegant 2-Storey Family House with Garden and Lake View in Pokhara', 'house', 9500000, 2500, 'NewRoad', 'Pokhara', 'Available', 'Nestled in the serene neighborhood of Lakeside, this 2-storey house offers tranquility and scenic views of Phewa Lake. The property includes a private garden, open terrace, and a large living room with modern finishes.', 'cctv,parking,conditioning,fire-alarm,fire-place,solar-water', '2025-05-22', '/images/property/ListedProperty.png'),
(21, 'Prime Commercial Property on Main Road – Perfect for Showroom or Office', 'colony', 120000, 3000, 'Rampur', 'Biratnagar', 'Available', 'This spacious commercial property is located on the busiest stretch of Main Road in Biratnagar. Ideal for showrooms, banks, offices, or retail outlets, it offers ample space, high foot traffic, and excellent visibility.', 'cctv,cafeteria,conditioning,fire-place,security,jacuzzi,kitchen', '2025-05-22', '/images/property/ListedProperty1.png'),
(22, ' 2BHK Apartment with Balcony & Covered Parking in Bakhundole', 'appartment', 13500000, 1100, 'Bakhundole', 'Lalitpur', 'Available', 'Situated in the peaceful neighborhood of Bakhundole, this 2-bedroom apartment is perfect for urban professionals.', 'garden,fencing,tv-cable,security,washing-machine', '2025-05-22', '/images/property/ListedProperty2.png'),
(23, 'Luxurious BHK Bungalow with Private Garden and Driveway in Budhanilkantha', 'appartment', 2800000, 3000, 'Budhanil', 'Kathmandu', 'Available', 'Set on 1.5 ropani of land, this stunning bungalow offers green views, marble flooring, and spacious interiors. Ideal for families seeking privacy and a high-end residential location close to nature.', 'cafeteria,gym,fire-place,jacuzzi,kitchen', '2025-05-22', '/images/property/house2.jpg'),
(24, '1000 sq.ft Commercial Space for Rent in New Road (Ideal for Retail or Office)', 'colony', 90000, 1000, 'BishalBazar', 'Kathmandu', 'Available', 'Strategically located in the heart of Kathmandu, this commercial space is ideal for mobile shops, showrooms, or financial institutions. Includes front-facing shutters, foot traffic, and branding visibility.', 'swimming-pool,fire-alarm,fire-place,solar-water,security,jacuzzi', '2025-05-22', '/images/property/homepageBackground.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `RoleID` int(3) NOT NULL,
  `RoleType` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`RoleID`, `RoleType`) VALUES
(1, 'Admin1 '),
(2, 'Admin2'),
(3, 'customer_izmizu'),
(4, 'customer_nacho'),
(5, 'customer_inaaa'),
(6, 'customer_tface'),
(7, 'customer_elio'),
(8, 'customer_gaurav');

-- --------------------------------------------------------

--
-- Table structure for table `role_admin`
--

CREATE TABLE `role_admin` (
  `RoleID` int(3) NOT NULL,
  `AdminID` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role_admin`
--

INSERT INTO `role_admin` (`RoleID`, `AdminID`) VALUES
(1, 1),
(2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `role_customer`
--

CREATE TABLE `role_customer` (
  `RoleID` int(3) NOT NULL,
  `CustomerID` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role_customer`
--

INSERT INTO `role_customer` (`RoleID`, `CustomerID`) VALUES
(3, 1),
(4, 2),
(5, 3),
(6, 4),
(7, 5),
(8, 6);

-- --------------------------------------------------------

--
-- Table structure for table `role_property`
--

CREATE TABLE `role_property` (
  `RoleID` int(3) NOT NULL,
  `PropertyID` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role_property`
--

INSERT INTO `role_property` (`RoleID`, `PropertyID`) VALUES
(3, 17),
(3, 18),
(3, 19),
(3, 20),
(8, 21),
(8, 22),
(5, 23),
(5, 24);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`AdminID`),
  ADD UNIQUE KEY `Admin_Username` (`Admin_Username`),
  ADD UNIQUE KEY `Admin_EmailAddress` (`Admin_EmailAddress`),
  ADD UNIQUE KEY `Admin_PhoneNumber` (`Admin_PhoneNumber`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`CustomerID`),
  ADD UNIQUE KEY `Cust_Username` (`Customer_Username`),
  ADD UNIQUE KEY `Cust_EmailAddress` (`Customer_EmailAddress`),
  ADD UNIQUE KEY `Cust_PhoneNumber` (`Customer_PhoneNumber`);

--
-- Indexes for table `customers_buyers`
--
ALTER TABLE `customers_buyers`
  ADD KEY `fk_customers_buyers_customer` (`CustomerID`),
  ADD KEY `fk_customers_buyers_property` (`PropertyID`);

--
-- Indexes for table `favorites`
--
ALTER TABLE `favorites`
  ADD PRIMARY KEY (`FavoritesID`),
  ADD KEY `role_fk_favorites` (`RoleID`);

--
-- Indexes for table `property`
--
ALTER TABLE `property`
  ADD PRIMARY KEY (`PropertyID`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`RoleID`);

--
-- Indexes for table `role_admin`
--
ALTER TABLE `role_admin`
  ADD KEY `role_fk_admin` (`RoleID`),
  ADD KEY `admin_fk_role` (`AdminID`);

--
-- Indexes for table `role_customer`
--
ALTER TABLE `role_customer`
  ADD KEY `role_fk_customer` (`RoleID`),
  ADD KEY `customer_fk_role` (`CustomerID`);

--
-- Indexes for table `role_property`
--
ALTER TABLE `role_property`
  ADD KEY `role_fk_property` (`RoleID`),
  ADD KEY `property_fk_role` (`PropertyID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `AdminID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `CustomerID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `favorites`
--
ALTER TABLE `favorites`
  MODIFY `FavoritesID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `property`
--
ALTER TABLE `property`
  MODIFY `PropertyID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `RoleID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `favorites`
--
ALTER TABLE `favorites`
  ADD CONSTRAINT `role_fk_favorites` FOREIGN KEY (`RoleID`) REFERENCES `roles` (`RoleID`);

--
-- Constraints for table `role_admin`
--
ALTER TABLE `role_admin`
  ADD CONSTRAINT `admin_fk_role` FOREIGN KEY (`AdminID`) REFERENCES `admins` (`AdminID`),
  ADD CONSTRAINT `role_fk_admin` FOREIGN KEY (`RoleID`) REFERENCES `roles` (`RoleID`);

--
-- Constraints for table `role_customer`
--
ALTER TABLE `role_customer`
  ADD CONSTRAINT `customer_fk_role` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`),
  ADD CONSTRAINT `role_fk_customer` FOREIGN KEY (`RoleID`) REFERENCES `roles` (`RoleID`);

--
-- Constraints for table `role_property`
--
ALTER TABLE `role_property`
  ADD CONSTRAINT `property_fk_role` FOREIGN KEY (`PropertyID`) REFERENCES `property` (`PropertyID`),
  ADD CONSTRAINT `role_fk_property` FOREIGN KEY (`RoleID`) REFERENCES `roles` (`RoleID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
