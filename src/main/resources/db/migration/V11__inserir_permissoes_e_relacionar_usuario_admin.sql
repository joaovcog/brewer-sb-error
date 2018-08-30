INSERT INTO permissoes VALUES (1, 'ROLE_CADASTRAR_CIDADE');
INSERT INTO permissoes VALUES (2, 'ROLE_CADASTRAR_USUARIO');

INSERT INTO grupos_permissoes (codigo_grupo, codigo_permissao) VALUES (1, 1);
INSERT INTO grupos_permissoes (codigo_grupo, codigo_permissao) VALUES (1, 2);

INSERT INTO usuarios_grupos (codigo_usuario, codigo_grupo) VALUES (
	(SELECT codigo FROM usuarios WHERE email = 'joao@email.com'), 1);