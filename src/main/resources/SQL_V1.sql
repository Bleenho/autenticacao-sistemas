INSERT INTO autenticacao.sistema VALUES (1, 'MARMITA-UAI-WEB');	
INSERT INTO autenticacao.perfil VALUES (1, 'ADM');
INSERT INTO autenticacao.modulo VALUES (1, 'TELA_ITEM', 1);
INSERT INTO autenticacao.modulo VALUES (2, 'TELA_TAMANHO_MARMITA', 1);
INSERT INTO autenticacao.modulo VALUES (3, 'TELA_CARDAPIO', 1);
INSERT INTO autenticacao.modulo_perfil VALUES (1, 1, 1);
INSERT INTO autenticacao.modulo_perfil VALUES (2, 2, 1);
INSERT INTO autenticacao.modulo_perfil VALUES (3, 3, 1);
INSERT INTO autenticacao.usuario VALUES (1, 'admin', 'adm@facil.com', 'e10adc3949ba59abbe56e057f20f883e', NOW(), 'A', 'Administracao', 1);