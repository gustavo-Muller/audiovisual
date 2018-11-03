USE mysql;

DROP DATABASE audiovisual;

CREATE DATABASE audiovisual;

USE audiovisual;

CREATE TABLE IF NOT EXISTS Usuario(
  `idUsuario` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `telefone` VARCHAR(100) NOT NULL,
  `celular` VARCHAR(100) NOT NULL,
  `tipo` INT(20) NOT NULL
  );


CREATE TABLE IF NOT EXISTS Marca (
  `idMarca` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  `nome` VARCHAR(100) NOT NULL
  );
  

CREATE TABLE IF NOT EXISTS Equipamento(
  `idEquipamento` INT  PRIMARY KEY NOT NULL auto_increment,
  `codigo` int not null,
  `nome` VARCHAR(100) NOT NULL,
  `descricao` VARCHAR(100) NOT NULL,
  `marca` INT NOT NULL,
  `tipo` INT NOT NULL,
    FOREIGN KEY (`marca`)REFERENCES `Marca` (`idMarca`)
   );


CREATE TABLE IF NOT EXISTS Data_hora(
  `idData_hora` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `data_retirada` datetime NOT NULL,
  `data_devolucao` datetime NOT NULL
  );



CREATE TABLE IF NOT EXISTS Aluguel (
  `idAluguel` INT(11) NOT NULL PRIMARY KEY  AUTO_INCREMENT,
  `Usuario_idUsuario` INT NOT NULL,
  `Data_hora_idData_hora` INT NOT NULL,
    FOREIGN KEY (`Usuario_idUsuario`) REFERENCES `Usuario` (`idUsuario`),
    FOREIGN KEY (`Data_hora_idData_hora`) REFERENCES `Data_hora` (`idData_hora`)
    );
   

CREATE TABLE IF NOT EXISTS Aluguel_equipamento (
  `Aluguel_idAluguel` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `Equipamento_idEquipamento` INT NOT NULL,
  `idAluguel_equipamento` INT(11) NOT NULL,
    FOREIGN KEY (`Aluguel_idAluguel`)REFERENCES `Aluguel` (`idAluguel`),    
    FOREIGN KEY (`Equipamento_idEquipamento`)REFERENCES `Equipamento` (`idEquipamento`)
    );    