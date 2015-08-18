package br.com.chatreact.entities.dao.tests;

import java.util.List;

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
import br.com.chatreact.statuses.StatusAtivacao;
import br.com.chatreact.statuses.StatusLogado;

public class MensagemDAOTest {
	
	private static UsuarioDAO uDao;
	
	private static ChatDAO cDao;
	
	private static MensagemDAO mDao;
	
	private static Usuario u1, u2;
	
	private static Chat chat;
	
	private static Mensagem men;

	@BeforeClass
	public static void setUpBeforeClass() {
		uDao = new UsuarioDAO();
		cDao = new ChatDAO();
		mDao = new MensagemDAO();
		
		u1 = new Usuario();
		u1.setNome("Nome 1");
		u1.setEmail("E-mail 1");
		u1.setSenha("Senha 1");
		u1.setLogin("Login 1");
		u1.setStatusAtivacao(StatusAtivacao.PENDENTE);
		u1.setStatusLogado(StatusLogado.OFFLINE);
		u1 = uDao.save(u1);
		
		u2 = new Usuario();
		u2.setNome("Nome 2");
		u2.setEmail("E-mail 2");
		u2.setSenha("Senha 2");
		u2.setLogin("Login 2");
		u2.setStatusAtivacao(StatusAtivacao.PENDENTE);
		u2.setStatusLogado(StatusLogado.OFFLINE);
		u2 = uDao.save(u2);
		
		chat = new Chat();
		chat.setUsuarioA(u1);
		chat.setUsuarioB(u2);
		chat = cDao.save(chat);
		
		men = new Mensagem();
		men.setChat(chat);
		men.setUsuario(u1);
		men.setTexto("Mensagem de teste");
		men = mDao.save(men);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		mDao.delete(men);
		cDao.delete(chat);
		uDao.delete(u1);
		uDao.delete(u2);
		mDao.closeConnection();
		cDao.closeConnection();
		uDao.closeConnection();
	}

	@Test
	public void testMensagemEnviada() {
		TestCase.assertNotNull(men);
	}
	
	@Test
	public void testAutorMensagem() {
		TestCase.assertTrue(u1.equals(men.getUsuario()));
	}
	
	@Test
	public void testMensagemEstaNoChat() {
		List<Mensagem> mens = mDao.listMessagesByChat(chat);
		TestCase.assertTrue(mens.contains(men));
	}

}
