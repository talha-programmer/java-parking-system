-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 21, 2020 at 05:45 AM
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
(18, 11, 10, '2020-12-21 04:39:43');

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
(11, 0, 20),
(11, 1, 25),
(11, 2, 35),
(11, 3, 50);

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
(11, 'New Parking', 'Lahore');

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
(14, 11);

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
(11, 0, 10),
(11, 1, 20),
(11, 2, 30),
(11, 3, 5);

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
(13, 'admin', '$31$16$zTTq8HUkEN99J-rOhzCxOAFoOBn4C1Yf1lilSjQwURk', 'Admin', 'mail@admin.com', 0),
(14, 'worker', '$31$16$6vN3eT5sDZC7rMaY_4k0HV6TXK4OJAxoa1FMpNrX4O8', 'New Worker', 'worker@gmail.com', 1);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `parking_lot`
--
ALTER TABLE `parking_lot`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `vehicle`
--
ALTER TABLE `vehicle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
