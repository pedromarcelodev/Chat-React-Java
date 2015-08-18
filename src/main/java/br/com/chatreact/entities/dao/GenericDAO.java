package br.com.chatreact.entities.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, K extends Serializable> {
	
	public T save(T entity);
	
	public T update(T entity);
	
	public T find(Class<T> cl, K id);
	
	public List<T> list(Class<T> cl);
	
	public boolean delete(T entity);
}
