package br.com.chatreact.entities.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.chatreact.entities.Usuario;
import br.com.chatreact.statuses.StatusLogado;

public class UsuarioDAO extends GenericDAOImpl<Usuario, Long>{
	
	@Override
	public Usuario save(Usuario entity) {
		entity.setKey(entity.getEmail() + "/" + entity.getLogin());
		return super.save(entity);
	}
	
	public Usuario findByEmail(Usuario user) {
		EntityManager em = getEntityManager();
		String hql = "select u from Usuario u where u.email = :email";
		try {
			return em.createQuery(hql, Usuario.class).setParameter("email", user.getEmail()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Usuario findByKey(String key) {
		EntityManager em = getEntityManager();
		String hql = "select u from Usuario u where u.key = :key";
		try {
			return em.createQuery(hql, Usuario.class).setParameter("key", key).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Usuario> listUsersOnline(Usuario user) {
		return this.listUsersByStatusLogado(StatusLogado.ONLINE, user);
	}
	
	public List<Usuario> listUsersOffline(Usuario user) {
		return this.listUsersByStatusLogado(StatusLogado.OFFLINE, user);
	}
	
	public List<Usuario> listUsersOcupado(Usuario user) {
		return this.listUsersByStatusLogado(StatusLogado.OCUPADO, user);
	}
	
	public List<Usuario> listUsersAusente(Usuario user) {
		return this.listUsersByStatusLogado(StatusLogado.AUSENTE, user);
	}
	
	private List<Usuario> listUsersByStatusLogado(StatusLogado statusLogged, Usuario user) {
		EntityManager em = getEntityManager();
		String hql = "select u from Usuario u where u.statusLogado = :statusLogged and u.id <> :id";
		return em.createQuery(hql, Usuario.class)
			.setParameter("statusLogged", statusLogged)
			.setParameter("id", user.getId())
			.getResultList();
	}

	@Override
	public Usuario find(Long id) {
		return this.find(Usuario.class, id);
	}

	@Override
	public List<Usuario> list() {
		return this.list(Usuario.class);
	}
}
