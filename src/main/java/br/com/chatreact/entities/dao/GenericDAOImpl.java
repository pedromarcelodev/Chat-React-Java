package br.com.chatreact.entities.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockTimeoutException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;

public abstract class GenericDAOImpl<T, K extends Serializable> implements GenericDAO<T, K>{

	private static EntityManager entityManager;
	
	private String error;
	
	@Override
	public T save(T entity) {
		EntityManager em = null;
		try {
			this.error = null;
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch(TransactionRequiredException e) {
			this.rollback();
			this.error = e.getMessage();
		} catch(IllegalArgumentException e) {
			this.rollback();
			this.error = e.getMessage();
		} catch(IllegalStateException e) {
			this.rollback();
			this.error = e.getMessage();
		} catch (Exception e) {
			this.rollback();
			this.error = e.getMessage();
		}
		
		if (this.error == null) {
			return entity;
		} else {
			return null;
		}
	}

	@Override
	public T update(T entity) {
		EntityManager em = null;
		
		try {
			this.error = null;
			em = getEntityManager();
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} catch(TransactionRequiredException e) {
			this.rollback();
			this.error = e.getMessage();
		} catch(IllegalArgumentException e) {
			this.rollback();
			this.error = e.getMessage();
		} catch(IllegalStateException e) {
			this.rollback();
			this.error = e.getMessage();
		} catch (Exception e) {
			this.rollback();
			this.error = e.getMessage();
		}
		
		if (this.error == null) {
			return entity;
		} else {
			return null;
		}
	}

	@Override
	public T find(Class<T> cl, K id) {
		try {
			this.error = null;
			return getEntityManager().find(cl, id);
		} catch (NoResultException e) {
			this.error = e.getMessage();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(Class<T> cl) {
		List<T> list = null;
		try {
			this.error = null;
			list = getEntityManager().createQuery("select o from " + cl.getSimpleName() + " o").getResultList();
		} catch (IllegalArgumentException e) {
			this.error = e.getMessage();
		} catch (IllegalStateException e) {
			this.error = e.getMessage();
		} catch (QueryTimeoutException e) {
			this.error = e.getMessage();
		} catch (TransactionRequiredException e) {
			this.error = e.getMessage();
		} catch (PessimisticLockException e) {
			this.error = e.getMessage();
		} catch (LockTimeoutException e) {
			this.error = e.getMessage();
		} catch (PersistenceException e) {
			this.error = e.getMessage();
		} catch (Exception e) {
			this.error = e.getMessage();
		}
		
		return list;
	}

	@Override
	public boolean delete(T entity) {
		EntityManager em = null;
		this.error = null;
		
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
		} catch(TransactionRequiredException e) {
			this.rollback();
			this.error = e.getMessage();
		} catch(IllegalArgumentException e) {
			this.rollback();
			this.error = e.getMessage();
		} catch(IllegalStateException e) {
			this.rollback();
			this.error = e.getMessage();
		} catch (Exception e) {
			this.rollback();
			this.error = e.getMessage();
		}
		
		return this.error == null;
	}
	
	public abstract T find(K id);
	
	public abstract List<T> list();
	
	public void closeConnection() {
		if (entityManager == null) {
			return;
		}

		try {
			entityManager.close();
			entityManager = null;
		} catch (IllegalStateException e) {
			this.error = e.getMessage();
		}
	}

	@PostPersist
	public void postPersist() {
		this.closeConnection();
	}

	@PostRemove
	public void postRemove() {
		this.closeConnection();
	}

	@PostUpdate
	public void postUpdate() {
		this.closeConnection();
	}
	
	public void rollback() {
		if (entityManager == null) {
			return;
		}
		
		try {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
		} catch (Exception e) {
			this.error = e.getMessage();
		}
	}

	public static EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = Persistence.createEntityManagerFactory("Chat-React").createEntityManager();
		}
		return entityManager;
	}
	
	protected void setError(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}
}
