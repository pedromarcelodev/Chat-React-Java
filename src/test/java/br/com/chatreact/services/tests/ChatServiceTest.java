package br.com.chatreact.services.tests;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.chatreact.entities.Chat;
import br.com.chatreact.entities.Mensagem;
import br.com.chatreact.entities.Usuario;
import br.com.chatreact.entities.dao.ChatDAO;
import br.com.chatreact.entities.dao.MensagemDAO;
import br.com.chatreact.entities.dao.UsuarioDAO;
import br.com.chatreact.services.ChatService;
import br.com.chatreact.statuses.StatusAtivacao;
import br.com.chatreact.statuses.StatusLogado;

public class ChatServiceTest {
	
	private static UsuarioDAO uDao;
	
	private static ChatDAO cDao;
	
	private static MensagemDAO mDao;
	
	private static Usuario u1, u2;
	
	private static Chat chat;
	
	private static Mensagem mens;
	
	private static ChatService sChat;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		uDao = new UsuarioDAO();
		cDao = new ChatDAO();
		mDao = new MensagemDAO();
		sChat = new ChatService();
		
		u1 = new Usuario();
		u1.setNome("Nome 1");
		u1.setEmail("E-mail 1");
		u1.setLogin("Login 1");
		u1.setSenha("123");
		u1.setStatusAtivacao(StatusAtivacao.PENDENTE);
		u1.setStatusLogado(StatusLogado.ONLINE);
		u1 = uDao.save(u1);
		
		u2 = new Usuario();
		u2.setNome("Nome 2");
		u2.setEmail("E-mail 2");
		u2.setLogin("Login 2");
		u2.setSenha("123");
		u2.setStatusAtivacao(StatusAtivacao.PENDENTE);
		u2.setStatusLogado(StatusLogado.ONLINE);
		u2 = uDao.save(u2);
		
		chat = sChat.abrirChat(u1, u2.getId());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		mDao.delete(mens);
		cDao.delete(chat);
		uDao.delete(u1);
		uDao.delete(u2);
	}

	@Test
	public void testAbrirChat() {
		TestCase.assertNotNull(chat);
	}

	@Test
	public void testEnviarMensagem() {
		Mensagem test = new Mensagem();
		test.setTexto("Mensagem");
		test.setUsuario(u1);
		mens = sChat.enviarMensagem(chat, test);
		TestCase.assertNotNull(mens);
	}
	
	@Test
	public void testNaoAbrirChatConsigoMesmo() {
		TestCase.assertNull(sChat.abrirChat(u1, u1.getId()));
	}
	
	@Test
	public void testAbrirChatUsuarioInexistente() {
		TestCase.assertNull(sChat.abrirChat(u1, -1L));
	}
}
