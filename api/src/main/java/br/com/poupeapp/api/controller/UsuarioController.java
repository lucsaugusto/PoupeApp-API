package br.com.poupeapp.api.controller;

import br.com.poupeapp.api.database.BancoUsuarios;
import br.com.poupeapp.api.model.Usuario;
import br.com.poupeapp.api.model.security.Auth;
import br.com.poupeapp.api.model.security.Token;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class UsuarioController {
	BancoUsuarios bd = new BancoUsuarios();

	@GetMapping("/usuario/{idUsuario}")
	public ResponseEntity<Usuario> getById(@PathVariable int idUsuario) {
		Usuario usuario = bd.buscar(idUsuario);
		if (usuario != null) {  // post com o ID especificado
			return ResponseEntity.ok(usuario);
		}
		else {
			return ResponseEntity.notFound().build();
		}		
		
	}
	
	@GetMapping("/usuario/all")
	public ResponseEntity<ArrayList<Usuario>> getAll(){
		return ResponseEntity.ok(bd.buscarTodos());
	}
	
	
	@PostMapping("/usuario/insert")
	public ResponseEntity<Usuario> novoUsuario(@RequestBody Usuario usuario) {
		bd.gravar(usuario);
		return ResponseEntity.ok(usuario);
	}
	
	@PutMapping("/usuario/update")
	public ResponseEntity<Usuario> alteraUsuario(@RequestBody Usuario usuario) {
		bd.atualizar(usuario);
		return ResponseEntity.ok(usuario);
	}
	
	@DeleteMapping("/usuario/delete/{idUsuario}")
	public ResponseEntity<String> removeUsuario(@PathVariable int idUsuario){
		if (bd.apagar(idUsuario)) {
			return ResponseEntity.ok("DELETED");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
//	@PostMapping("/usuario/login")
//	public ResponseEntity<Usuario> autenticaUsuario(@RequestBody Usuario usuario) {
//		ArrayList<Usuario> lista = bd.buscarTodos();
//		for (int i = 0; i < lista.size(); i++) {
//			if (usuario.getEmail().equals(lista.get(i).getEmail()) && usuario.getSenha().equals(lista.get(i).getSenha())) {
//				
//				return ResponseEntity.ok(lista.get(i));
//			}
//		}
//		return ResponseEntity.status(403).build();
//	}
	
	
	@PostMapping("/usuario/login")
	public ResponseEntity<Token> autentica(@RequestBody Usuario usuario) {
		ArrayList<Usuario> lista = bd.buscarTodos();
		for (int i = 0; i < lista.size(); i++) {
			if (usuario.getEmail().equals(lista.get(i).getEmail())
					&& usuario.getSenha().equals(lista.get(i).getSenha())) {
				usuario = lista.get(i);
				String tk = Auth.generateToken(usuario);
				Token token = new Token();
				token.setToken(tk);
				token.setNome(lista.get(i).getNome());
				token.setEmail(lista.get(i).getEmail());

				return ResponseEntity.ok(token);
			}
		}
		return ResponseEntity.status(403).build();
	}
}
