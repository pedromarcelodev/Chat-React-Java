package br.com.chatreact.services;

import java.util.Random;

import br.com.chatreact.entities.Usuario;
import br.com.chatreact.statuses.StatusAtivacao;
import br.com.chatreact.statuses.StatusLogado;

public class UsuarioService extends GenericServiceImpl {
	
	public Usuario cadastrarUsuario(Usuario usuario) {
		usuario.setStatusAtivacao(StatusAtivacao.PENDENTE);
		usuario.setStatusLogado(StatusLogado.OFFLINE);
		return getUsuarioDAO().save(usuario);
	}
	
	public Usuario ativarCadastro(String key) {
		Usuario usuario = getUsuarioDAO().findByKey(key);
		
		if (usuario == null) {
			return null;
		}
		
		usuario.setStatusAtivacao(StatusAtivacao.ATIVO);
		usuario = getUsuarioDAO().update(usuario);
		
		return usuario;
	}
	
	public Usuario gerarSenhaEsquecida(Usuario usuario) {
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		int alphaSize = alpha.length();
		Random rand = new Random();
		String newPassword = "";
		
		for (int i = 0; i < 15; i++) {
			newPassword += alpha.charAt(rand.nextInt(alphaSize));
		}
		
		usuario.setSenha(newPassword);
		return getUsuarioDAO().update(usuario);
	}

}