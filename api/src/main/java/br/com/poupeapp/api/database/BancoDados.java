package br.com.poupeapp.api.database;

import java.util.ArrayList;

import br.com.poupeapp.api.model.Postagem;

public class BancoDados {

	private ArrayList<Postagem> lista;
	private static int incr = 0;
	
	public BancoDados() {
		lista = new ArrayList<>();
	}
	
	//Create
	public void gravar(Postagem postagem) {
		incr++;
		postagem.setIdPostagem(incr);
		this.lista.add(postagem);
	}
	
	//Read
	public Postagem buscar(int idPostagem) {
		Postagem postagem = null;
		for (Postagem posicao : lista) {
			if (posicao.getIdPostagem() == idPostagem) {
				postagem = posicao;
				break;
			}
		}
		return postagem;
	}
	
	
	//Update
	public void atualizar(Postagem postagem) {
    	int posicao=-1;
		for (int i=0; i< lista.size(); i++) {
			if (lista.get(i).getIdPostagem() == postagem.getIdPostagem()) {
				posicao = i;
				break;
			}
		}
		if (posicao >= 0) {
			lista.set(posicao, postagem);
		}	
	}
	
	//get All
	public ArrayList<Postagem> buscarTodos(){
		return lista;
	}
	
}