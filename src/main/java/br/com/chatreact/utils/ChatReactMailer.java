package br.com.chatreact.utils;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.simplemail.Mailer;
import br.com.chatreact.entities.Usuario;

public class ChatReactMailer {
	private Mailer mailer;
	
	public ChatReactMailer(Mailer mailer) {
		this.mailer = mailer;
	}
	
	public boolean enviarEmailConfirmacao(Usuario usuario) {
		try {
			Email email = new SimpleEmail();
			email.setSubject("Chat React - Sua Nova Senha");
			email.addTo(usuario.getEmail());
			mailer.send(email);
			return true;
		} catch (EmailException e) {
			return false;
		}
	}
}