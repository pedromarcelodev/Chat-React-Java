package br.com.chatreact.services;

import br.com.chatreact.entities.dao.ChatDAO;
import br.com.chatreact.entities.dao.MensagemDAO;
import br.com.chatreact.entities.dao.UsuarioDAO;

public class GenericServiceImpl implements GenericService{
	
	private static ChatDAO chatDAO;
	
	private static MensagemDAO mensagemDAO;
	
	private static UsuarioDAO usuarioDAO;

	public static ChatDAO getChatDAO() {
		if (chatDAO == null) {
			chatDAO = new ChatDAO();
		}
		
		return chatDAO;
	}

	public static MensagemDAO getMensagemDAO() {
		if (mensagemDAO == null) {
			mensagemDAO = new MensagemDAO();
		}
		
		return mensagemDAO;
	}

	public static UsuarioDAO getUsuarioDAO() {
		if (usuarioDAO == null) {
			usuarioDAO = new UsuarioDAO();
		}
		
		return usuarioDAO;
	}
	
}