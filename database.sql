-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 28, 2020 at 10:10 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `parking_system`
--
CREATE DATABASE IF NOT EXISTS `parking_system` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `parking_system`;

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `id` int(11) NOT NULL,
  `vehicle_id` int(11) NOT NULL,
  `parking_lot_id` int(11) NOT NULL,
  `time_entrance` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `time_exit` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `total_fee` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`id`, `vehicle_id`, `parking_lot_id`, `time_entrance`, `time_exit`, `total_fee`) VALUES
(1, 14, 7, '2020-11-16 15:21:26', '2020-11-17 07:24:33', 321),
(2, 14, 7, '2020-11-16 15:21:26', '2020-11-17 07:36:24', 324.667),
(3, 14, 7, '2020-11-16 15:21:26', '2020-11-17 07:36:24', 324.667),
(4, 14, 7, '2020-11-16 15:21:26', '2020-11-17 07:36:24', 324.667),
(5, 15, 7, '2020-11-16 15:26:55', '2020-11-17 07:40:10', 648.667),
(6, 15, 7, '2020-11-16 15:26:55', '2020-11-17 07:43:20', 650.667),
(7, 18, 7, '2020-11-16 15:42:07', '2020-11-17 13:15:57', 862),
(8, 17, 7, '2020-11-16 15:34:10', '2020-11-17 15:27:05', 954.667),
(9, 4, 7, '2020-11-15 19:11:43', '2020-11-17 15:42:02', 1780),
(10, 14, 7, '2020-11-16 15:21:26', '2020-11-19 07:48:31', 1289),
(11, 12, 7, '2020-11-16 14:13:04', '2020-11-22 08:54:54', 5547.33);

-- --------------------------------------------------------

--
-- Table structure for table `parked_vehicle`
--

CREATE TABLE `parked_vehicle` (
  `id` int(11) NOT NULL,
  `parking_lot_id` int(11) NOT NULL,
  `vehicle_id` int(11) NOT NULL,
  `time_parked` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parked_vehicle`
--

INSERT INTO `parked_vehicle` (`id`, `parking_lot_id`, `vehicle_id`, `time_parked`) VALUES
(2, 7, 5, '2020-11-15 20:00:00'),
(3, 7, 6, '2020-11-16 06:14:31'),
(4, 7, 7, '2020-11-16 06:20:05'),
(5, 7, 8, '2020-11-16 06:29:14'),
(6, 7, 9, '2020-11-16 07:07:04'),
(7, 7, 10, '2020-11-16 07:07:51'),
(8, 7, 11, '2020-11-16 07:08:30'),
(9, 7, 12, '2020-11-16 14:10:09'),
(11, 7, 13, '2020-11-16 15:16:54'),
(14, 7, 16, '2020-11-16 15:27:51'),
(17, 7, 18, '2020-11-22 08:54:07');

-- --------------------------------------------------------

--
-- Table structure for table `parking_fee`
--

CREATE TABLE `parking_fee` (
  `parking_lot_id` int(11) NOT NULL,
  `vehicle_type` int(11) NOT NULL,
  `parking_fee` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parking_fee`
--

INSERT INTO `parking_fee` (`parking_lot_id`, `vehicle_type`, `parking_fee`) VALUES
(1, 0, 30),
(1, 1, 10),
(1, 2, 40),
(1, 3, 50),
(4, 0, 10),
(4, 1, 20),
(4, 2, 20),
(4, 3, 40),
(7, 0, 20),
(7, 1, 35),
(7, 2, 50),
(7, 3, 100),
(7, 0, 20),
(7, 1, 35),
(7, 2, 40),
(7, 3, 100),
(8, 0, 30),
(8, 1, 40),
(8, 2, 50),
(8, 3, 100),
(9, 0, 10),
(9, 1, 20),
(9, 2, 30),
(9, 3, 50),
(10, 0, 20),
(10, 1, 35),
(10, 2, 40),
(10, 3, 100),
(7, 0, 20),
(7, 1, 35),
(7, 2, 50),
(7, 3, 100);

-- --------------------------------------------------------

--
-- Table structure for table `parking_lot`
--

CREATE TABLE `parking_lot` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `location` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parking_lot`
--

INSERT INTO `parking_lot` (`id`, `name`, `location`) VALUES
(7, 'New Parking', 'Lahore');

-- --------------------------------------------------------

--
-- Table structure for table `parking_lot_allocation`
--

CREATE TABLE `parking_lot_allocation` (
  `user_id` int(11) NOT NULL,
  `parking_lot_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parking_lot_allocation`
--

INSERT INTO `parking_lot_allocation` (`user_id`, `parking_lot_id`) VALUES
(11, 7),
(11, 8),
(11, 9);

-- --------------------------------------------------------

--
-- Table structure for table `parking_lot_capacity`
--

CREATE TABLE `parking_lot_capacity` (
  `parking_lot_id` int(11) NOT NULL,
  `vehicle_type` int(11) NOT NULL,
  `capacity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parking_lot_capacity`
--

INSERT INTO `parking_lot_capacity` (`parking_lot_id`, `vehicle_type`, `capacity`) VALUES
(7, 0, 20),
(7, 1, 10),
(7, 2, 20),
(7, 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `user_type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `full_name`, `email`, `user_type`) VALUES
(2, 'talha', '$31$16$mSPBQMSPN8azhIWPJskleEHcJx-NMxx6rO3hG4tKXfs', 'M Talha', 'mail@sample.com', 0),
(9, 'user', '$31$16$WI5Dfz--liylq_KEwsg_XBnpclEfB5a3cVhqsIyqCPk', 'sample user', 'mail@mail.com', 0),
(11, 'worker', '$31$16$nxn0P44RBQBQVFh6F44RofFs5kesqvHkwAqVMr-53GI', 'Ahmed Ali', 'gmail@gmail.com', 1),
(12, 'worker2', '$31$16$7brWk8nrU1cwW1UyDRvc8i7Pr5E2MCjPLs-RvvKiyHs', 'Ismail Khan', 'mail@google.com', 1),
(13, 'admin', '$31$16$zTTq8HUkEN99J-rOhzCxOAFoOBn4C1Yf1lilSjQwURk', 'Admin', 'mail@admin.com', 0);

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

CREATE TABLE `vehicle` (
  `id` int(11) NOT NULL,
  `reg_number` varchar(50) NOT NULL,
  `vehicle_name` varchar(50) NOT NULL,
  `owner_name` varchar(50) DEFAULT NULL,
  `vehicle_type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vehicle`
--

INSERT INTO `vehicle` (`id`, `reg_number`, `vehicle_name`, `owner_name`, `vehicle_type`) VALUES
(4, 'RIL-8377', 'Suzuki Mehran', 'Khwaja Nadeem', 2),
(5, 'JML-2309', 'Honda Pridor', 'Irfan Hashmi', 0),
(6, 'LHR-7899', 'Honda Civic', 'Ahmad', 2),
(7, 'NH-120', 'Honda City', '', 2),
(8, 'NH-193', 'Toyota Passo', '', 2),
(9, 'JMK-490', 'Shezore', '', 3),
(10, 'JMK-380', 'Shezore', 'Irfan khan', 3),
(11, 'JMK-390', 'Shezore', 'Khalid', 3),
(12, 'RMS-239', 'Suzuki Mehran', '', 2),
(13, 'JIL-2400', 'Suzuki Mehran', '', 2),
(14, 'JIL-2290', 'Toyota Passo', 'Jameel', 0),
(15, 'JIL-2403', 'Toyota Passo', 'Jameel', 2),
(16, 'JMK-3920', 'Honda Bike', 'Irfan', 0),
(17, 'RMS-309', 'Suzuki Khyber', '', 2),
(18, 'RIL-9033', 'Suzuki Mehran', '', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `parked_vehicle`
--
ALTER TABLE `parked_vehicle`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `parking_lot`
--
ALTER TABLE `parking_lot`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `parked_vehicle`
--
ALTER TABLE `parked_vehicle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `parking_lot`
--
ALTER TABLE `parking_lot`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `vehicle`
--
ALTER TABLE `vehicle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
