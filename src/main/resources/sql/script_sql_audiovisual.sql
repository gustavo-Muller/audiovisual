USE mysql;

DROP DATABASE audiovisual;

CREATE DATABASE audiovisual;

USE audiovisual;

CREATE TABLE IF NOT EXISTS usuario(
  `id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `telefone` VARCHAR(100),
  `celular` VARCHAR(100),
  `tipo` INT(20) NOT NULL
  );


CREATE TABLE IF NOT EXISTS marca (
  `id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  descricao VARCHAR(255)
  );
  

CREATE TABLE IF NOT EXISTS equipamento(
  `idEquipamento` INT  PRIMARY KEY NOT NULL auto_increment,
  `codigo` VARCHAR(100) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `descricao` VARCHAR(100) NOT NULL,
  `marca` INT NOT NULL,
  `tipo` INT NOT NULL,
    FOREIGN KEY (`marca`)REFERENCES `marca` (`id`)
   );



CREATE TABLE IF NOT EXISTS aluguel (
  `idAluguel` INT(11) NOT NULL PRIMARY KEY  AUTO_INCREMENT,
  `Usuario_idUsuario` INT NOT NULL,
  `data_retirada` datetime NOT NULL,
  `data_devolucao` datetime NOT NULL,
    FOREIGN KEY (`Usuario_idUsuario`) REFERENCES `usuario` (`id`)
    );
   

CREATE TABLE IF NOT EXISTS aluguel_equipamento (
  `Aluguel_idAluguel` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `Equipamento_idEquipamento` INT NOT NULL,
  `idAluguel_equipamento` INT(11) NOT NULL,
    FOREIGN KEY (`Aluguel_idAluguel`)REFERENCES `Aluguel` (`idAluguel`),    
    FOREIGN KEY (`Equipamento_idEquipamento`)REFERENCES `equipamento` (`idEquipamento`)
    ); 
    
 USE audiovisual;