package br.com.chatreact.entities.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.chatreact.entities.Chat;
import br.com.chatreact.entities.Mensagem;
import br.com.chatreact.entities.Usuario;

public class ChatDAO extends GenericDAOImpl<Chat, Long>{

	public List<Chat> listChatsByUsuario(Usuario user) {
		EntityManager em = getEntityManager();
		String hql = "select c from Chat c where c.usuarioA = :userA or c.usuarioB = :userB";
		return em.createQuery(hql, Chat.class)
			.setParameter("userA", user)
			.setParameter("userB", user)
			.getResultList();
	}
	
	public Chat findByUsers(Usuario userA, Usuario userB) {
		EntityManager em = getEntityManager();
		String hql = "select c from Chat c where (c.usuarioA = :userA1 and c.usuarioB = :userB1) or (c.usuarioA = :userA2 and c.usuarioB = :userB2)";
		try {
			return em.createQuery(hql, Chat.class)
				.setParameter("userA1", userA)
				.setParameter("userB1", userB)
				.setParameter("userA2", userB)
				.setParameter("userB2", userA)
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Chat findByMessage(Mensagem mensagem) {
		EntityManager em = getEntityManager();
		String hql = "select c from Mensagem m inner join m.chat as c where m = :mensagem";
		
		try {
			return em.createQuery(hql, Chat.class)
				.setParameter("mensagem", mensagem)
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Chat saveOrUpdateChat(Usuario userA, Usuario userB) {
		Chat chat = this.findByUsers(userA, userB);
		
		if (chat == null) {
			chat = new Chat();
			chat.setUsuarioA(userA);
			chat.setUsuarioB(userB);
			
			chat = this.save(chat);
		} else {
			chat = this.update(chat);
		}
		
		return chat;
	}

	@Override
	public Chat find(Long id) {
		return this.find(Chat.class, id);
	}

	@Override
	public List<Chat> list() {
		return this.list(Chat.class);
	}
}
