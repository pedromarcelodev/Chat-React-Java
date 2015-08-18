package br.com.chatreact.services;

import java.util.ArrayList;

import br.com.chatreact.entities.Chat;
import br.com.chatreact.entities.Mensagem;
import br.com.chatreact.entities.Usuario;

public class ChatService extends GenericServiceImpl {
	
	public Chat abrirChat(Usuario usuario, Long idConvidado) {
		if (usuario.getId() == idConvidado) {
			return null;
		}
		
		Usuario convidado = getUsuarioDAO().find(idConvidado);
		
		if (convidado == null) {
			return null;
		}
		
		return getChatDAO().saveOrUpdateChat(usuario, convidado);
	}
	
	public Mensagem enviarMensagem(Chat chat, Mensagem mensagem) {
		if (chat.getMensagens() == null) {
			chat.setMensagens(new ArrayList<Mensagem>());
		} else if (chat.getMensagens().contains(mensagem)) {
			return mensagem;
		}
		
		chat.getMensagens().add(mensagem);
		mensagem.setChat(chat);
		getChatDAO().update(chat);
		return getMensagemDAO().save(mensagem);
	}
}