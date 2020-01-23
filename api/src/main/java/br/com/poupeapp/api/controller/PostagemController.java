package br.com.poupeapp.api.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.poupeapp.api.database.BancoDados;
import br.com.poupeapp.api.model.Postagem;

@RestController
@CrossOrigin("*")
public class PostagemController {
	BancoDados bd = new BancoDados();

	@GetMapping("/postagem/{idPostagem}")
	public ResponseEntity<Postagem> getById(@PathVariable int idPostagem) {
		Postagem postagem = bd.buscar(idPostagem);
		if (postagem != null) {  // post com o ID especificado
			return ResponseEntity.ok(postagem);
		}
		else {
			return ResponseEntity.notFound().build();
		}		
		
	}
	
	@GetMapping("/postagem/all")
	public ResponseEntity<ArrayList<Postagem>> getAll(){
		return ResponseEntity.ok(bd.buscarTodos());
	}
	
	
	@PostMapping("/postagem/insert")
	public ResponseEntity<Postagem> novoCliente(@RequestBody Postagem postagem) {
		bd.gravar(postagem);
		return ResponseEntity.ok(postagem);
	}
	
	@PutMapping("/postagem/update")
	public ResponseEntity<String> alteraCliente(@RequestBody Postagem postagem) {
		bd.atualizar(postagem);
		return ResponseEntity.ok("Success");
	}
}
