-- Cria o banco de dados
-- CREATE SCHEMA `ufms_banking` DEFAULT CHARACTER SET utf8;

-- Insere um Banco
-- INSERT INTO `ufms_banking`.`tb_banco` (`bc_codigo`, `bc_nome`) VALUES (10, 'UFMS Bank');

-- Insere uma Agencia
-- INSERT INTO `ufms_banking`.`tb_agencia` (`ag_numero`, `ag_digito`, `ag_telefone`, `ag_municipio`, `ag_estado`, `bc_id`) VALUES (1, 0, '6732910200', 'Coxim', 'MS', 10);

-- Insere três Categorias
-- INSERT INTO `ufms_banking`.`tb_categoria` (`cg_nome`, `cg_taxa_rendimento`, `cg_taxa_juros`) VALUES ('A', 0.75, 10);
-- INSERT INTO `ufms_banking`.`tb_categoria` (`cg_nome`, `cg_taxa_rendimento`, `cg_taxa_juros`) VALUES ('B', 0.6, 12);
-- INSERT INTO `ufms_banking`.`tb_categoria` (`cg_nome`, `cg_taxa_rendimento`, `cg_taxa_juros`) VALUES ('C', 0.5, 14);

-- Insere um Bancario
-- INSERT INTO `ufms_banking`.`tb_usuario` (`us_usuario`, `us_senha`, `us_tipo`) VALUES ('admin', 'admin', 'BANCARIO');
-- INSERT INTO `ufms_banking`.`tb_bancario` (`br_id`, `br_nome`, `ag_numero`) VALUES (1, 'Gedson Faria', 1);

-- Insere um Correntista
-- INSERT INTO `ufms_banking`.`tb_usuario` (`us_usuario`, `us_senha`, `us_tipo`) VALUES ('kleberkruger', '123', 'CORRENTISTA');
-- INSERT INTO `ufms_banking`.`tb_correntista` (`co_id`, `co_nome`, `co_cpf_cnpj`, `co_tipo`) VALUES ('2', 'Kleber Kruger', '02135730165', 'PESSOA_FISICA');
-- Cria a Conta Corrente e a Conta Poupanca do correntista
-- INSERT INTO `ufms_banking`.`tb_conta_bancaria` (`cb_operacao`, `cb_numero`, `cb_digito`, `cb_saldo`, `cb_limite`, `cb_tipo`, `ct_id`, `ag_numero`) VALUES (1, 1, 0, 0, 2000, 'CONTA_CORRENTE', 1, 1);
-- INSERT INTO `ufms_banking`.`tb_conta_bancaria` (`cb_operacao`, `cb_numero`, `cb_digito`, `cb_saldo`, `cb_limite`, `cb_tipo`, `ct_id`, `ag_numero`) VALUES (13, 1, 0, 0, null, 'CONTA_POUPANCA', 1, 1);

-- Insere um Deposito
-- INSERT INTO `ufms_banking`.`tb_transacao` (`ts_dthr`, `ts_valor`, `ts_tipo`, `cb_numero`, `cb_operacao`) VALUES (now(), '5000', 'DEPOSITO', '1', '1');
-- INSERT INTO `ufms_banking`.`tb_deposito` (`ts_id`, `dp_depositante_nome`, `dp_depositante_telefone`) VALUES ('1', 'Kleber', '67998836289');
-- Atualiza o saldo da Conta Corrente
-- UPDATE `ufms_banking`.`tb_conta_bancaria` SET `cb_saldo`='5000' WHERE `cb_operacao`='001' and`cb_numero`='00000001';

-- Insere um Saque
-- INSERT INTO `ufms_banking`.`tb_transacao` (`ts_dthr`, `ts_valor`, `ts_tipo`, `cb_numero`, `cb_operacao`) VALUES (now(), 2000, 'SAQUE', 1, 1);
-- Atualiza o saldo da Conta Corrente
-- UPDATE `ufms_banking`.`tb_conta_bancaria` SET `cb_saldo`='3000' WHERE `cb_operacao`='001' and`cb_numero`='00000001';

-- Insere uma Transacao
-- INSERT INTO `ufms_banking`.`tb_transacao` (`ts_dthr`, `ts_valor`, `ts_tipo`, `cb_numero`, `cb_operacao`) VALUES (now(), 1200, 'TRANSFERENCIA', '1', '13');
-- INSERT INTO `ufms_banking`.`tb_transferencia` (`tb_transacao_ts_id`, `cb_numero`, `cb_operacao`) VALUES ('3', '1', '13');
-- Atualiza os saldos das Contas Bancarias
-- UPDATE `ufms_banking`.`tb_conta_bancaria` SET `cb_saldo`='1800' WHERE `cb_operacao`='001' and`cb_numero`='00000001';
-- UPDATE `ufms_banking`.`tb_conta_bancaria` SET `cb_saldo`='1200' WHERE `cb_operacao`='013' and`cb_numero`='00000001';
