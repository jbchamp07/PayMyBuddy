-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 03 juin 2022 à 10:57
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `paymebuddy`
--

-- --------------------------------------------------------

--
-- Structure de la table `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `balance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `account`
--

INSERT INTO `account` (`id`, `user_id`, `balance`) VALUES
(1, 1, 150),
(3, 2, 202),
(4, 4, 304),
(5, 5, 1909),
(166, 170, 500);

-- --------------------------------------------------------

--
-- Structure de la table `friendlist`
--

CREATE TABLE `friendlist` (
  `id` int(11) NOT NULL,
  `id_giver` int(11) NOT NULL,
  `id_receiver` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `friendlist`
--

INSERT INTO `friendlist` (`id`, `id_giver`, `id_receiver`) VALUES
(1, 1, 3),
(2, 1, 4),
(3, 1, 5);

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

CREATE TABLE `transaction` (
  `id` int(11) NOT NULL,
  `id_giver` int(11) NOT NULL,
  `id_receiver` int(11) NOT NULL,
  `amount` double NOT NULL,
  `description` varchar(100) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `transaction`
--

INSERT INTO `transaction` (`id`, `id_giver`, `id_receiver`, `amount`, `description`, `date`) VALUES
(2, 1, 4, 14, '1 donne a 4 14euros', '2022-04-01'),
(3, 1, 4, 13, '2eme transactions', '2022-04-01'),
(4, 1, 5, 15, 'jb donne a jluc', '2022-04-01'),
(5, 5, 1, 51, 'jluc donne a jb', '2022-04-01'),
(6, 1, 5, 123, 'a', '2022-04-14'),
(7, 1, 5, 123, 'a', '2022-04-15'),
(8, 1, 5, 123, 'a', '2022-04-21'),
(9, 1, 5, 123, 'a', '2022-04-22'),
(14, 1, 5, 4, 'des', '2022-04-01'),
(15, 1, 5, 50, 'desc', '2022-04-01'),
(16, 1, 5, 654, 'sdfg', '2022-04-01'),
(17, 1, 5, 45, 'desc', '2022-04-01'),
(18, 1, 5, 50, 'sa marche !', '2022-04-01'),
(19, 1, 2, 2, 'jb2', '2022-04-01'),
(20, 1, 4, 4, 'jb4', '2022-04-01');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `account_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `firstname`, `lastname`, `email`, `password`, `account_id`) VALUES
(1, 'Jean-Baptiste', 'Champetier', 'jb.champetier@gmail.com', '$2y$10$VdyidWhenX0mRZmxSwDXguGbvp7s3ZU2h0iurj46QQkpsKNZsRZuG', 1),
(2, 'jean-Baptiste2', 'Champetier2', 'jb.champetier@gmail.com2', '$2y$10$CUiAfKE4DNF95PUjpB35jeh7.r8Ci0P2U1eBG4qdpn/CCJ0DcHHDa', 3),
(4, 'jean-Baptiste4', 'Champetier4', 'jb.champ@gmail.com4', '$2y$10$CUiAfKE4DNF95PUjpB35jeh7.r8Ci0P2U1eBG4qdpn/CCJ0DcHHDa', 4),
(5, 'jean-luc', 'champetier', 'champetier.jean-luc@gmail.com', '$2y$10$O8jgxj1d6YN6su4rpVrRv.h5uY680Q3kqDPP47M.kPDq701/..5xS', 5),
(170, 'PayMyBuddy', 'PayMyBuddy', 'PayMyBuddy@gmail.com', '$2a$10$ex9UT7dAl5IaT4LjUHh1UOfVE0dT.ZLeKW3xTNH6iN6ri/1K0fZd6', 166);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `friendlist`
--
ALTER TABLE `friendlist`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=167;

--
-- AUTO_INCREMENT pour la table `friendlist`
--
ALTER TABLE `friendlist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT pour la table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=171;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
