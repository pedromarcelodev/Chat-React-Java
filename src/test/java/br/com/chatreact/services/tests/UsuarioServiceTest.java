package br.com.chatreact.services.tests;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.chatreact.entities.Usuario;
import br.com.chatreact.services.UsuarioService;
import br.com.chatreact.statuses.StatusAtivacao;

public class UsuarioServiceTest {
	
	private static UsuarioService sUsuario;
	
	private static Usuario usuario;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sUsuario = new UsuarioService();
		usuario = new Usuario();
		usuario.setNome("Nome");
		usuario.setEmail("emailtest@test.com");
		usuario.setLogin("login_test");
		usuario.setSenha("123");
		usuario = sUsuario.cadastrarUsuario(usuario);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		UsuarioService.getUsuarioDAO().delete(usuario);
	}

	@Test
	public void testUsuarioCadastrado() {
		TestCase.assertNotNull(usuario);
	}
	
	@Test
	public void testAtivarUsuario() {
		usuario = sUsuario.ativarCadastro(usuario.getKey());
		TestCase.assertEquals(StatusAtivacao.ATIVO, usuario.getStatusAtivacao());
	}
	
	@Test
	public void testGerarSenha() {
		String oldPassword = usuario.getSenha();
		usuario = sUsuario.gerarSenhaEsquecida(usuario);
		TestCase.assertTrue(!oldPassword.equals(usuario.getSenha()));
	}

}
