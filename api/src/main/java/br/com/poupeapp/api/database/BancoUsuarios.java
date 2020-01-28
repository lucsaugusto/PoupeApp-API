package br.com.poupeapp.api.database;

import java.util.ArrayList;

import br.com.poupeapp.api.model.Usuario;

public class BancoUsuarios {

	private ArrayList<Usuario> lista;
	private static int incr = 0;
	
	public BancoUsuarios() {
		lista = new ArrayList<>();
	}
	
	//Create
	public void gravar(Usuario usuario) {
		incr++;
		usuario.setIdUsuario(incr);
		this.lista.add(usuario);
	}
	
	//Read
	public Usuario buscar(int idUsuario) {
		Usuario usuario = null;
		for (Usuario posicao : lista) {
			if (posicao.getIdUsuario() == idUsuario) {
				usuario = posicao;
				break;
			}
		}
		return usuario;
	}
	
	
	//Update
	public void atualizar(Usuario usuario) {
    	int posicao=-1;
		for (int i=0; i< lista.size(); i++) {
			if (lista.get(i).getIdUsuario() == usuario.getIdUsuario()) {
				posicao = i;
				break;
			}
		}
		if (posicao >= 0) {
			lista.set(posicao, usuario);
		}	
	}
	
	
	//Delete
	public boolean apagar(int id) {
		int posicao=-1;
		for (int i=0; i< lista.size(); i++) {
			if (lista.get(i).getIdUsuario() == id) {
				posicao = i;
				break;
			}
		}
		if (posicao >=0) {
			lista.remove(posicao);
			return true;
		}
		return false;				
	}
	
	//get All
	public ArrayList<Usuario> buscarTodos(){
		return lista;
	}
	
}
