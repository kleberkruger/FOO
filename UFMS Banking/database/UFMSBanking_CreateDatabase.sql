-- MySQL Workbench Synchronization
-- Generated: 2017-08-13 15:42
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Kleber Kruger

CREATE SCHEMA `ufms_banking` DEFAULT CHARACTER SET utf8;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE TABLE IF NOT EXISTS `ufms_banking`.`tb_usuario` (
  `us_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `us_usuario` VARCHAR(32) NOT NULL,
  `us_senha` VARCHAR(128) NOT NULL,
  `us_tipo` ENUM('BANCARIO', 'CORRENTISTA') NOT NULL,
  PRIMARY KEY (`us_id`),
  UNIQUE INDEX `us_id_UNIQUE` (`us_id` ASC),
  UNIQUE INDEX `us_login_UNIQUE` (`us_usuario` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ufms_banking`.`tb_bancario` (
  `br_id` INT(10) UNSIGNED NOT NULL,
  `br_nome` VARCHAR(255) NOT NULL,
  `ag_numero` SMALLINT(4) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`br_id`),
  INDEX `fk_tb_bancario_tb_agencia1_idx` (`ag_numero` ASC),
  CONSTRAINT `fk_tb_bancario_tb_usuario`
    FOREIGN KEY (`br_id`)
    REFERENCES `ufms_banking`.`tb_usuario` (`us_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_bancario_tb_agencia1`
    FOREIGN KEY (`ag_numero`)
    REFERENCES `ufms_banking`.`tb_agencia` (`ag_numero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ufms_banking`.`tb_correntista` (
  `co_id` INT(10) UNSIGNED NOT NULL,
  `co_numero_conta` INT(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `co_nome` VARCHAR(255) NOT NULL,
  `co_razao_social` VARCHAR(255) NULL DEFAULT NULL,
  `co_cpf_cnpj` VARCHAR(14) NOT NULL,
  `co_telefone` VARCHAR(11) NULL DEFAULT NULL,
  `co_tipo` ENUM('PESSOA_FISICA', 'PESSOA_JURIDICA') NOT NULL,
  PRIMARY KEY (`co_id`),
  UNIQUE INDEX `us_numero_conta_UNIQUE` (`co_numero_conta` ASC),
  UNIQUE INDEX `us_cpf_cnpj_UNIQUE` (`co_cpf_cnpj` ASC),
  CONSTRAINT `fk_tb_correntista_tb_usuario1`
    FOREIGN KEY (`co_id`)
    REFERENCES `ufms_banking`.`tb_usuario` (`us_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ufms_banking`.`tb_conta_bancaria` (
  `cb_operacao` TINYINT(3) UNSIGNED ZEROFILL NOT NULL,
  `cb_numero` INT(8) UNSIGNED ZEROFILL NOT NULL,
  `cb_digito` TINYINT(1) NOT NULL,
  `cb_saldo` DOUBLE NOT NULL,
  `cb_limite` DOUBLE UNSIGNED NULL DEFAULT NULL,
  `cb_tipo` ENUM('CONTA_CORRENTE', 'CONTA_POUPANCA') NOT NULL,
  `ct_id` TINYINT(3) UNSIGNED NOT NULL,
  `ag_numero` SMALLINT(4) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`cb_operacao`, `cb_numero`),
  INDEX `fk_tb_conta_bancaria_tb_categoria1_idx` (`ct_id` ASC),
  INDEX `fk_tb_conta_bancaria_tb_agencia1_idx` (`ag_numero` ASC),
  INDEX `fk_tb_conta_bancaria_tb_correntista1_idx` (`cb_numero` ASC),
  CONSTRAINT `fk_tb_conta_bancaria_tb_correntista1`
    FOREIGN KEY (`cb_numero`)
    REFERENCES `ufms_banking`.`tb_correntista` (`co_numero_conta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_conta_bancaria_tb_categoria1`
    FOREIGN KEY (`ct_id`)
    REFERENCES `ufms_banking`.`tb_categoria` (`cg_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_conta_bancaria_tb_agencia1`
    FOREIGN KEY (`ag_numero`)
    REFERENCES `ufms_banking`.`tb_agencia` (`ag_numero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ufms_banking`.`tb_categoria` (
  `cg_id` TINYINT(3) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cg_nome` VARCHAR(32) NOT NULL,
  `cg_taxa_rendimento` FLOAT(10) UNSIGNED NOT NULL,
  `cg_taxa_juros` FLOAT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`cg_id`),
  UNIQUE INDEX `ct_id_UNIQUE` (`cg_id` ASC),
  UNIQUE INDEX `ct_nome_UNIQUE` (`cg_nome` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ufms_banking`.`tb_transacao` (
  `ts_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ts_dthr` DATETIME NOT NULL,
  `ts_valor` DOUBLE UNSIGNED NOT NULL,
  `ts_tipo` ENUM('DEPOSITO', 'SAQUE', 'TRANSFERENCIA') NOT NULL,
  `cb_numero` INT(8) UNSIGNED ZEROFILL NOT NULL,
  `cb_operacao` TINYINT(3) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`ts_id`),
  UNIQUE INDEX `tr_id_UNIQUE` (`ts_id` ASC),
  INDEX `fk_tb_transacao_tb_conta_bancaria1_idx` (`cb_numero` ASC, `cb_operacao` ASC),
  CONSTRAINT `fk_tb_transacao_tb_conta_bancaria1`
    FOREIGN KEY (`cb_numero` , `cb_operacao`)
    REFERENCES `ufms_banking`.`tb_conta_bancaria` (`cb_numero` , `cb_operacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ufms_banking`.`tb_transferencia` (
  `tb_transacao_ts_id` INT(10) UNSIGNED NOT NULL,
  `cb_numero` INT(8) UNSIGNED ZEROFILL NOT NULL,
  `cb_operacao` TINYINT(3) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`tb_transacao_ts_id`),
  INDEX `fk_tb_transferencia_tb_conta_bancaria1_idx` (`cb_numero` ASC, `cb_operacao` ASC),
  CONSTRAINT `fk_tb_transferencia_tb_transacao1`
    FOREIGN KEY (`tb_transacao_ts_id`)
    REFERENCES `ufms_banking`.`tb_transacao` (`ts_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_transferencia_tb_conta_bancaria1`
    FOREIGN KEY (`cb_numero` , `cb_operacao`)
    REFERENCES `ufms_banking`.`tb_conta_bancaria` (`cb_numero` , `cb_operacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ufms_banking`.`tb_deposito` (
  `ts_id` INT(10) UNSIGNED NOT NULL,
  `dp_numero_envelope` INT(10) UNSIGNED ZEROFILL NULL DEFAULT NULL AUTO_INCREMENT COMMENT 'O número do envelope será gerado automaticamente para emular um depósito sendo realizado no caixa eletrônico. Este campo não é obrigatório, pois estou supondo que há a possibilidade de se fazer depósitos na boca do caixa.',
  `dp_depositante_nome` VARCHAR(255) NULL DEFAULT NULL,
  `dp_depositante_telefone` VARCHAR(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ts_id`),
  UNIQUE INDEX `dp_numero_envelope_UNIQUE` (`dp_numero_envelope` ASC),
  CONSTRAINT `fk_tb_deposito_tb_transacao1`
    FOREIGN KEY (`ts_id`)
    REFERENCES `ufms_banking`.`tb_transacao` (`ts_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ufms_banking`.`tb_agencia` (
  `ag_numero` SMALLINT(4) UNSIGNED ZEROFILL NOT NULL,
  `ag_digito` TINYINT(1) UNSIGNED NOT NULL,
  `ag_telefone` VARCHAR(11) NULL DEFAULT NULL,
  `ag_municipio` VARCHAR(64) NOT NULL,
  `ag_estado` VARCHAR(2) NOT NULL,
  `bc_id` SMALLINT(3) UNSIGNED NOT NULL,
  PRIMARY KEY (`ag_numero`),
  UNIQUE INDEX `ag_id_UNIQUE` (`ag_numero` ASC),
  INDEX `fk_tb_agencia_tb_banco1_idx` (`bc_id` ASC),
  CONSTRAINT `fk_tb_agencia_tb_banco1`
    FOREIGN KEY (`bc_id`)
    REFERENCES `ufms_banking`.`tb_banco` (`bc_codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ufms_banking`.`tb_banco` (
  `bc_codigo` SMALLINT(3) UNSIGNED NOT NULL COMMENT 'Código do Banco, por exemplo: Banco do Brasil (001), Caixa Econômica (104), etc.',
  `bc_nome` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`bc_codigo`),
  UNIQUE INDEX `bc_id_UNIQUE` (`bc_codigo` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
