package br.com.chatreact.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.chatreact.statuses.StatusAtivacao;
import br.com.chatreact.statuses.StatusLogado;
import br.com.chatreact.utils.HashString;

@Entity
@Table(name="tb_usuarios")
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6523080918562115103L;
	
	@Id
	@GeneratedValue
	@Column(name="id_usuario")
	private Long id;
	
	@Column(name="nome_usuario", length=150, nullable=false)
	private String nome;
	
	@Column(name="login_usuario", length=60, nullable=false, unique=true)
	private String login;
	
	@Column(name="email_usuario", length=150, nullable=false, unique=true)
	private String email;
	
	@Column(name="senha_usuario", length=64, nullable=false)
	private String senha;
	
	@Column(name="key", nullable=false, length=64, unique=true)
	private String key;
	
	@Column(name="status_logado", nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private StatusLogado statusLogado;
	
	@Column(name="status_ativacao", nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private StatusAtivacao statusAtivacao;
	
	@Column(name="created_at", nullable=false, insertable=true, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Column(name="updated_at", nullable=false, insertable=true, updatable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@OneToMany(mappedBy="usuarioB", targetEntity=Chat.class, fetch=FetchType.LAZY)
	private Set<Chat> chats;
	
	@OneToMany(mappedBy="usuario")
	private List<Mensagem> mensagem;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = HashString.sha256(senha);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = HashString.sha256(key);
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

	public StatusLogado getStatusLogado() {
		return statusLogado;
	}

	public void setStatusLogado(StatusLogado statusLogado) {
		this.statusLogado = statusLogado;
	}

	public StatusAtivacao getStatusAtivacao() {
		return statusAtivacao;
	}

	public void setStatusAtivacao(StatusAtivacao statusAtivacao) {
		this.statusAtivacao = statusAtivacao;
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result
				+ ((statusAtivacao == null) ? 0 : statusAtivacao.hashCode());
		result = prime * result
				+ ((statusLogado == null) ? 0 : statusLogado.hashCode());
		result = prime * result
				+ ((updatedAt == null) ? 0 : updatedAt.hashCode());
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
		Usuario other = (Usuario) obj;
		if (createdAt == null) {
			if (other.getCreatedAt() != null)
				return false;
		} else if (!createdAt.equals(other.getCreatedAt()))
			return false;
		if (email == null) {
			if (other.getEmail() != null)
				return false;
		} else if (!email.equals(other.getEmail()))
			return false;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		if (key == null) {
			if (other.getKey() != null)
				return false;
		} else if (!key.equals(other.getKey()))
			return false;
		if (login == null) {
			if (other.getLogin() != null)
				return false;
		} else if (!login.equals(other.getLogin()))
			return false;
		if (nome == null) {
			if (other.getNome() != null)
				return false;
		} else if (!nome.equals(other.getNome()))
			return false;
		if (senha == null) {
			if (other.getSenha() != null)
				return false;
		} else if (!senha.equals(other.getSenha()))
			return false;
		if (statusAtivacao != other.getStatusAtivacao())
			return false;
		if (statusLogado != other.getStatusLogado())
			return false;
		if (updatedAt == null) {
			if (other.getUpdatedAt() != null)
				return false;
		} else if (!updatedAt.equals(other.getUpdatedAt()))
			return false;
		return true;
	}
}
