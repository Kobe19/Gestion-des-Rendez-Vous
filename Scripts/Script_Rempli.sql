-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 22 mai 2020 à 16:37
-- Version du serveur :  5.7.24
-- Version de PHP :  7.1.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projet_bd20`
--

-- --------------------------------------------------------

--
-- Structure de la table `consulter`
--

DROP TABLE IF EXISTS `consulter`;
CREATE TABLE IF NOT EXISTS `consulter` (
  `id_patient` int(11) NOT NULL,
  `id_seance` int(11) NOT NULL,
  `id_type_consultation` int(11) NOT NULL,
  `indicateur` int(11) NOT NULL,
  `reglement` varchar(250) DEFAULT NULL,
  `prix` varchar(11) DEFAULT NULL,
  `posture` varchar(250) DEFAULT NULL,
  `comportement` text,
  `rdv_N` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_patient`,`id_seance`,`id_type_consultation`) USING BTREE,
  KEY `consulter_SEANCE0_FK` (`id_seance`),
  KEY `consulter_TYPE_CONSULTATION1_FK` (`id_type_consultation`),
  KEY `number` (`rdv_N`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `consulter`
--

INSERT INTO `consulter` (`id_patient`, `id_seance`, `id_type_consultation`, `indicateur`, `reglement`, `prix`, `posture`, `comportement`, `rdv_N`) VALUES
(1, 13, 1, 0, 'Carte Bleue', '', '', '', 3),
(1, 14, 1, 0, 'Espèce', '', '', '', 9),
(1, 15, 1, 0, 'Espèce', '', '', '', 10),
(2, 13, 1, 0, 'Espèce', '', '', '', 6),
(3, 14, 1, 0, 'Espèce', '', '', '', 8),
(3, 15, 1, 0, 'Espèce', '', '', '', 11),
(5, 13, 1, 0, 'Espèce', '', '', '', 7),
(5, 14, 1, 0, 'Espèce', '', '', '', 4),
(7, 16, 2, 10, 'Carte Bleue', '', '', '', 12);

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `id_patient` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(250) NOT NULL,
  `prenom` varchar(250) NOT NULL,
  `adresse` varchar(250) NOT NULL,
  `telephone` int(11) NOT NULL,
  `moyen_connaissance` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL,
  `id_type_patient` int(11) NOT NULL,
  PRIMARY KEY (`id_patient`),
  KEY `PATIENT_TYPE_PATIENT_FK` (`id_type_patient`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`id_patient`, `nom`, `prenom`, `adresse`, `telephone`, `moyen_connaissance`, `email`, `id_type_patient`) VALUES
(1, 'kobe', 'danny', '55 rue de la maternelle 95000 Paris', 87654, 'bouche à oreille ', 'kobe@yahoo.fr', 5),
(2, 'Lebron', 'James', '67 rue des fleurs 92190 Bois colombes', 198864655, 'Pages jaunes', 'lebron@yahoo.fr', 5),
(3, 'brad', 'jean', '65 rue des manguiers, 45000 Melun', 93287342, 'Internet', 'brad@gmail.com', 2),
(4, 'drale', 'wayne', '56 Rue du general leclerc', 9675634, 'Autre patient', 'drake@yahoo.fr', 5),
(5, 'henri', 'guirol', '56 rue de villejuif 5600 PARIS', 9999999, 'Docteur', 'HENRI@yahoo.fr', 3),
(6, 'DOAL', 'Yvan', '54 RUE de nnn 654 PARIS', 88888888, 'Pages jaunes', 'yvan@yahoo.fr', 5),
(7, 'kapgep', 'gisele', '45 rue des mangues 12106 Douala', 677593611, 'bouche à oreille ', 'kapguep', 4);

-- --------------------------------------------------------

--
-- Structure de la table `profession`
--

DROP TABLE IF EXISTS `profession`;
CREATE TABLE IF NOT EXISTS `profession` (
  `id_profession` int(11) NOT NULL AUTO_INCREMENT,
  `intitule` varchar(250) NOT NULL,
  `annee` varchar(250) NOT NULL,
  `id_patient` int(11) NOT NULL,
  PRIMARY KEY (`id_profession`),
  KEY `PROFESSION_PATIENT_FK` (`id_patient`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `profession`
--

INSERT INTO `profession` (`id_profession`, `intitule`, `annee`, `id_patient`) VALUES
(1, 'informaticien', '2020', 1),
(2, 'etudiant', '2013', 1),
(3, 'Manager', '2001', 1),
(4, 'basketteur', '2020', 1),
(5, 'basketball player', '2020', 2),
(6, 'entrepreneur', '2013', 2),
(7, 'avocat', '2020', 3),
(8, 'pilote', '2001', 3),
(9, 'info', '2020', 5),
(10, 'architecte 3', '2001', 5),
(11, 'Etudiante', '2020', 7),
(12, 'chirurgienne', '2013', 7);

-- --------------------------------------------------------

--
-- Structure de la table `seance`
--

DROP TABLE IF EXISTS `seance`;
CREATE TABLE IF NOT EXISTS `seance` (
  `id_seance` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `creneau` varchar(255) NOT NULL,
  PRIMARY KEY (`id_seance`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `seance`
--

INSERT INTO `seance` (`id_seance`, `date`, `creneau`) VALUES
(13, '2020-05-01', '8h00 - 8h30'),
(14, '2020-05-30', '8h00 - 8h30'),
(15, '2020-05-02', '8h00 - 8h30'),
(16, '2020-05-22', '19h30 - 20h00');

-- --------------------------------------------------------

--
-- Structure de la table `type_consultation`
--

DROP TABLE IF EXISTS `type_consultation`;
CREATE TABLE IF NOT EXISTS `type_consultation` (
  `id_type_consultation` int(11) NOT NULL AUTO_INCREMENT,
  `intitule` varchar(250) NOT NULL,
  PRIMARY KEY (`id_type_consultation`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `type_consultation`
--

INSERT INTO `type_consultation` (`id_type_consultation`, `intitule`) VALUES
(1, 'Standard'),
(2, 'Anxiete');

-- --------------------------------------------------------

--
-- Structure de la table `type_patient`
--

DROP TABLE IF EXISTS `type_patient`;
CREATE TABLE IF NOT EXISTS `type_patient` (
  `id_type_patient` int(11) NOT NULL AUTO_INCREMENT,
  `intitule` varchar(250) NOT NULL,
  PRIMARY KEY (`id_type_patient`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `type_patient`
--

INSERT INTO `type_patient` (`id_type_patient`, `intitule`) VALUES
(1, 'Enfant'),
(2, 'Ado'),
(3, 'homme'),
(4, 'femme'),
(5, 'couple');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `Type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `username`, `password`, `Type`) VALUES
(1, 'kobe@yahoo.fr', 'patientX', 'Simple utilisateur'),
(2, 'lebron@yahoo.fr', 'patient2', 'Simple utilisateur'),
(11, 'ADMIN', 'ADMIN', 'ADMIN'),
(14, 'kapguep', 'PatientK', 'Simple Utilisateur'),
(13, 'yvan@yahoo.fr', 'Admin1', 'Admin'),
(12, 'henri@yahoo.fr', 'patient5', 'Simple utilisateur');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `consulter`
--
ALTER TABLE `consulter`
  ADD CONSTRAINT `consulter_PATIENT_FK` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_patient`),
  ADD CONSTRAINT `consulter_SEANCE0_FK` FOREIGN KEY (`id_seance`) REFERENCES `seance` (`id_seance`),
  ADD CONSTRAINT `consulter_TYPE_CONSULTATION1_FK` FOREIGN KEY (`id_type_consultation`) REFERENCES `type_consultation` (`id_type_consultation`);

--
-- Contraintes pour la table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `PATIENT_TYPE_PATIENT_FK` FOREIGN KEY (`id_type_patient`) REFERENCES `type_patient` (`id_type_patient`);

--
-- Contraintes pour la table `profession`
--
ALTER TABLE `profession`
  ADD CONSTRAINT `PROFESSION_PATIENT_FK` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_patient`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
