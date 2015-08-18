package br.com.chatreact.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_chat")
public class Chat implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7310521359136630967L;

	@Id
	@GeneratedValue
	@Column(name="id_chat")
	private Long idChat;
	
	@ManyToOne
	@JoinColumn(name="id_usuario_a", nullable=false)
	private Usuario usuarioA;
	
	@ManyToOne
	@JoinColumn(name="id_usuario_b", nullable=false)
	private Usuario usuarioB;

	@Column(name="created_at", updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Column(name="updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@OneToMany(targetEntity=Mensagem.class, mappedBy="chat", fetch=FetchType.LAZY)
	private List<Mensagem> mensagens;

	public Long getIdChat() {
		return idChat;
	}

	public void setIdChat(Long idChat) {
		this.idChat = idChat;
	}

	public Usuario getUsuarioA() {
		return usuarioA;
	}

	public void setUsuarioA(Usuario usuarioA) {
		this.usuarioA = usuarioA;
	}

	public Usuario getUsuarioB() {
		return usuarioB;
	}

	public void setUsuarioB(Usuario usuarioB) {
		this.usuarioB = usuarioB;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	@PrePersist
	public void onCreate() {
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
	
	@PreUpdate
	public void onUpdate() {
		this.updatedAt = new Date();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((idChat == null) ? 0 : idChat.hashCode());
		result = prime * result
				+ ((updatedAt == null) ? 0 : updatedAt.hashCode());
		result = prime * result
				+ ((usuarioA == null) ? 0 : usuarioA.hashCode());
		result = prime * result
				+ ((usuarioB == null) ? 0 : usuarioB.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chat other = (Chat) obj;
		if (createdAt == null) {
			if (other.getCreatedAt() != null)
				return false;
		} else if (!createdAt.equals(other.getCreatedAt()))
			return false;
		if (idChat == null) {
			if (other.getIdChat() != null)
				return false;
		} else if (!idChat.equals(other.getIdChat()))
			return false;
		if (updatedAt == null) {
			if (other.getUpdatedAt() != null)
				return false;
		} else if (!updatedAt.equals(other.getUpdatedAt()))
			return false;
		if (usuarioA == null) {
			if (other.getUsuarioA() != null)
				return false;
		} else if (!usuarioA.equals(other.getUsuarioA()))
			return false;
		if (usuarioB == null) {
			if (other.getUsuarioB() != null)
				return false;
		} else if (!usuarioB.equals(other.getUsuarioB()))
			return false;
		return true;
	}
}
