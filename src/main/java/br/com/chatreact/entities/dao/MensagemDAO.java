package br.com.chatreact.entities.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.chatreact.entities.Chat;
import br.com.chatreact.entities.Mensagem;

public class MensagemDAO extends GenericDAOImpl<Mensagem, Long>{
	
	public List<Mensagem> listMessagesByChat(Chat chat) {
		EntityManager em = getEntityManager();
		String hql = "select m from Mensagem m where m.chat = :chat";
		return em.createQuery(hql, Mensagem.class)
			.setParameter("chat", chat)
			.getResultList();
	}

	@Override
	public Mensagem find(Long id) {
		return this.find(Mensagem.class, id);
	}

	@Override
	public List<Mensagem> list() {
		return this.list(Mensagem.class);
	}

}
