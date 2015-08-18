package br.com.chatreact.entities.dao.tests;

import java.util.List;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.chatreact.entities.Usuario;
import br.com.chatreact.entities.dao.UsuarioDAO;
import br.com.chatreact.statuses.StatusAtivacao;
import br.com.chatreact.statuses.StatusLogado;


public class UsuarioDAOTest {
	
	private static UsuarioDAO dao;
	
	private static Usuario usuario;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		dao = new UsuarioDAO();
		usuario = new Usuario();
		
		usuario.setNome("Pedro Marcelo");
		usuario.setEmail("pedromarcelodesaalvesteste@gmail.com");
		usuario.setSenha("123456789");
		usuario.setLogin("pedromjavateste");
		usuario.setStatusAtivacao(StatusAtivacao.PENDENTE);
		usuario.setStatusLogado(StatusLogado.OFFLINE);
		
		usuario = dao.save(usuario);
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		dao.delete(usuario);
		dao.closeConnection();
	}
	
	@Test
	public void testUsuarioInserido() {
		TestCase.assertNotNull(usuario);
	}
	
	@Test
	public void testBuscarPorEmail() { //Busca um usuário pelo e-mail
		Usuario test = dao.findByEmail(usuario);
		TestCase.assertTrue(usuario.equals(test));
	}
	
	@Test
	public void testBuscarPorEmailInexistente() { //Busca um usuário pelo e-mail
		Usuario test = new Usuario();
		test.setEmail("ksajdhaskdjhakj");
		TestCase.assertNull(dao.findByEmail(test));
	}
	
	@Test
	public void testBuscarPelaKey() { // Busca um usuário pela key
		Usuario test = dao.findByKey(usuario.getKey());
		TestCase.assertTrue(usuario.equals(test));
	}
	
	@Test
	public void testBuscarPelaKeyInexistente() { // Busca um usuário pela key
		Usuario test = dao.findByKey("123");
		TestCase.assertNull(test);
	}

	@Test
	public void testListarUsuariosOffline() { // Lista usuários offline, exceto o que for passado por parâmetro
		List<Usuario> list = dao.listUsersOffline(usuario);
		TestCase.assertEquals(0, list.size());
	}
	
	@Test
	public void testListarEsteUsuarioOffline() { // Lista usuários offline
		Usuario test = new Usuario();
		test.setId(-1L);
		List<Usuario> list = dao.listUsersOffline(test);
		TestCase.assertEquals(1, list.size());
	}
}
