-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema basedprograma
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `basedprograma` ;

-- -----------------------------------------------------
-- Schema basedprograma
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `basedprograma` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `basedprograma` ;

-- -----------------------------------------------------
-- Table `basedprograma`.`encargado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `basedprograma`.`encargado` ;

CREATE TABLE IF NOT EXISTS `basedprograma`.`encargado` (
  
  `codEncar` INT NOT NULL,
  `nomEncar` VARCHAR(60) NULL DEFAULT NULL,
  `numTelEnca` VARCHAR(60) NULL DEFAULT NULL,
  `diasTraba` INT NULL DEFAULT NULL,
  PRIMARY KEY (`codEncar`))
  
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `basedprograma`.`restaurante`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `basedprograma`.`restaurante` ;

CREATE TABLE IF NOT EXISTS `basedprograma`.`restaurante` (
  `codRest` INT NOT NULL,
  `codEncar` INT NULL DEFAULT NULL,
  `numTelRest` VARCHAR(60) NULL DEFAULT NULL,
  `direRest` VARCHAR(200) NULL DEFAULT NULL,
  `nomRest` VARCHAR(60) NULL DEFAULT NULL,
  `encargado_codEncar` INT NOT NULL,
  
  PRIMARY KEY (`codRest`),
  INDEX `fk_restaurante_encargado1_idx` (`encargado_codEncar` ASC) VISIBLE,
  CONSTRAINT `fk_restaurante_encargado1`
    FOREIGN KEY (`encargado_codEncar` )
    REFERENCES `basedprograma`.`encargado` (`codEncar`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `basedprograma`.`clientes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `basedprograma`.`clientes` ;

CREATE TABLE IF NOT EXISTS `basedprograma`.`clientes` (
  `codRest` INT NOT NULL,
  `codClien` INT NOT NULL,
  `nomClien` VARCHAR(60) NULL DEFAULT NULL,
  `dirClien` VARCHAR(200) NULL DEFAULT NULL,
  `numTelClien` VARCHAR(60) NULL DEFAULT NULL,
  `numPedido` INT UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`codClien`, `codRest`),
  INDEX `fk_restaurante_clientes` (`codRest` ASC) VISIBLE,
  CONSTRAINT `fk_restaurante_clientes`
    FOREIGN KEY (`codRest`)
    REFERENCES `basedprograma`.`restaurante` (`codRest`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


insert into encargado
	(codEncar, nomEncar, numTelEnca, diasTraba)
    values
    (123, 'Juan perez', '60685989', 45);
    
INSERT INTO `basedprograma`.`restaurante`
(`codRest`,

`numTelRest`,
`direRest`,
`nomRest`,
`encargado_codEncar`
)
VALUES
(001,'98564454','calle palomo' ,'Bali poket',123 );


-- select * from restaurante; 


   -- select * from encargado;
   insert into clientes 
	(codRest, codClien, nomClien, dirClien, numTelClien, numPedido)
    values
	 (001,312,'nacho salcedo', 'calle de los santos 3','65699865', 4564),
	 (001,313,'pepe sanchez', 'urb las mimosas 4', '85696541', 4565),
	 (001,314,'juan ortega','calle los melones 3', '6547832', 4566),
	 (001,315,'emilio reyes', 'avenida españa 7', '65693321', 4567),
	 (001,316,'perico palotes', 'calle el agua 3' ,'89674532', 4568),
	 (001,317,'kiko narvaez', 'urba casas del duque 4','15243985', 4569),
	 (01,318,'antonio lucas', 'avenida los reyes catolicos 5','58698745', 4570);
	
