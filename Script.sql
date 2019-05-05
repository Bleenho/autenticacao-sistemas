CREATE DATABASE  IF NOT EXISTS autenticacao;
USE autenticacao;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
-- Server version	8.0.15

 SET NAMES utf8 ;

--
-- Table structure for table sistema
--
CREATE TABLE sistema (
  id_sistema bigint(20) NOT NULL AUTO_INCREMENT,
  ds_sistema varchar(255) DEFAULT NULL,
  PRIMARY KEY (id_sistema)
);

--
-- Table structure for table modulo
--
CREATE TABLE modulo (
  id_modulo bigint(20) NOT NULL AUTO_INCREMENT,
  ds_modulo varchar(255) DEFAULT NULL,
  cd_sistema bigint(20) DEFAULT NULL,
  PRIMARY KEY (id_modulo),
  KEY FK_MODULO_SISTEMA (cd_sistema),
  CONSTRAINT FK_MODULO_SISTEMA FOREIGN KEY (cd_sistema) REFERENCES sistema (id_sistema)
);

--
-- Table structure for table perfil
--
CREATE TABLE perfil (
  id_perfil bigint(20) NOT NULL AUTO_INCREMENT,
  ds_perfil varchar(255) DEFAULT NULL,
  PRIMARY KEY (id_perfil)
);

--
-- Table structure for table modulo_perfil
--
CREATE TABLE modulo_perfil (
  id_modulo_perfil bigint(20) NOT NULL AUTO_INCREMENT,
  cd_modulo bigint(20) DEFAULT NULL,
  cd_perfil bigint(20) DEFAULT NULL,
  PRIMARY KEY (id_modulo_perfil),
  KEY FK_MODULO_PERFIL_MODULO (cd_modulo),
  KEY FK_MODULO_PERFIL_PERFIL (cd_perfil),
  CONSTRAINT FK_MODULO_PERFIL_PERFIL FOREIGN KEY (cd_perfil) REFERENCES perfil (id_perfil),
  CONSTRAINT FK_MODULO_PERFIL_MODULO FOREIGN KEY (cd_modulo) REFERENCES modulo (id_modulo)
);

--
-- Table structure for table parametro_sistema
--
CREATE TABLE parametro_sistema (
  id_parametro_sistema bigint(20) NOT NULL AUTO_INCREMENT,
  ds_parametro varchar(255) DEFAULT NULL,
  vl_parametro varchar(255) DEFAULT NULL,
  cd_sistema bigint(20) DEFAULT NULL,
  PRIMARY KEY (id_parametro_sistema),
  KEY FK_PARAMETRO_SISTEMA_SISTEMA (cd_sistema),
  CONSTRAINT FK_PARAMETRO_SISTEMA_SISTEMA FOREIGN KEY (cd_sistema) REFERENCES sistema (id_sistema)
);

--
-- Table structure for table usuario
--
CREATE TABLE usuario (
  id_usuario bigint(20) NOT NULL AUTO_INCREMENT,
  cd_usuario varchar(255) DEFAULT NULL,
  ds_email varchar(255) DEFAULT NULL,
  ds_senha varchar(255) DEFAULT NULL,
  dt_alteracao datetime DEFAULT NULL,
  fl_status varchar(255) DEFAULT NULL,
  nm_usuario varchar(255) DEFAULT NULL,
  cd_perfil bigint(20) DEFAULT NULL,
  PRIMARY KEY (id_usuario),
  KEY FK_USUARIO_PERFIL (cd_perfil),
  CONSTRAINT FK_USUARIO_PERFIL FOREIGN KEY (cd_perfil) REFERENCES perfil (id_perfil)
) ;

--
-- Table structure for table solicitacao_acesso
--
CREATE TABLE solicitacao_acesso (
  id_solicitacaoacesso bigint(20) NOT NULL AUTO_INCREMENT,
  ds_token varchar(255) DEFAULT NULL,
  dt_token datetime DEFAULT NULL,
  dt_token_expired datetime DEFAULT NULL,
  vl_minuto_valida bigint(20) DEFAULT NULL,
  cd_sistema bigint(20) DEFAULT NULL,
  cd_usuario bigint(20) DEFAULT NULL,
  PRIMARY KEY (id_solicitacaoacesso),
  KEY FK_SOLICITACAO_ACESSO_SISTEMA (cd_sistema),
  KEY FK_SOLICITACAO_ACESSO_USUARIO (cd_usuario),
  CONSTRAINT FK_SOLICITACAO_ACESSO_USUARIO FOREIGN KEY (cd_usuario) REFERENCES usuario (id_usuario),
  CONSTRAINT FK_SOLICITACAO_ACESSO_SISTEMA FOREIGN KEY (cd_sistema) REFERENCES sistema (id_sistema)
);

-- Dump completed on 2019-05-05 11:53:43
