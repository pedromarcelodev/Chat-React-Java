package br.com.chatreact.entities.dao.tests;

import java.util.List;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.chatreact.entities.Chat;
import br.com.chatreact.entities.Usuario;
import br.com.chatreact.entities.dao.ChatDAO;
import br.com.chatreact.entities.dao.UsuarioDAO;
import br.com.chatreact.statuses.StatusAtivacao;
import br.com.chatreact.statuses.StatusLogado;

public class ChatDAOTest {
	
	private static Chat chat;
	
	private static Usuario u1;
	
	private static Usuario u2;
	
	private static ChatDAO cDao;
	
	private static UsuarioDAO uDao;

	@BeforeClass
	public static void setUpBeforeClass() {
		chat = new Chat();
		cDao = new ChatDAO();
		uDao = new UsuarioDAO();
		u1 = new Usuario();
		u2 = new Usuario();

		u1.setNome("User 1");
		u1.setEmail("E-mail 1");
		u1.setSenha("123");
		u1.setLogin("Login 1");
		u1.setStatusAtivacao(StatusAtivacao.PENDENTE);
		u1.setStatusLogado(StatusLogado.OFFLINE);
		u1 = uDao.save(u1);
		
		u2.setNome("User 2");
		u2.setEmail("E-mail 2");
		u2.setSenha("123");
		u2.setLogin("Login 2");
		u2.setStatusAtivacao(StatusAtivacao.PENDENTE);
		u2.setStatusLogado(StatusLogado.OFFLINE);
		u2 = uDao.save(u2);
		
		chat.setUsuarioA(u1);
		chat.setUsuarioB(u2);
		
		chat = cDao.save(chat);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		cDao.delete(chat);
		uDao.delete(u1);
		uDao.delete(u2);
		cDao.closeConnection();
		uDao.closeConnection();
	}

	@Test
	public void testChatIniciado() {
		TestCase.assertTrue(chat.getCreatedAt().equals(chat.getUpdatedAt()));
	}
	
	@Test
	public void testBuscarChatPorUsuarios() {
		Chat test = cDao.findByUsers(u1, u2);
		TestCase.assertTrue(chat.equals(test));
	}
	
	@Test
	public void testListarPorUsuario() {
		List<Chat> test = cDao.listChatsByUsuario(u1);
		TestCase.assertTrue(test.indexOf(chat) > -1);
	}
}
