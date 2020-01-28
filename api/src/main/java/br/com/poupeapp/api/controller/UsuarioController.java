package br.com.poupeapp.api.controller;

import br.com.poupeapp.api.database.BancoUsuarios;
import br.com.poupeapp.api.model.Usuario;

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
	public ResponseEntity<Usuario> novoCliente(@RequestBody Usuario usuario) {
		bd.gravar(usuario);
		return ResponseEntity.ok(usuario);
	}
	
	@PutMapping("/usuario/update")
	public ResponseEntity<String> alteraCliente(@RequestBody Usuario usuario) {
		bd.atualizar(usuario);
		return ResponseEntity.ok("Success");
	}
	
	@DeleteMapping("/usuario/delete/{idUsuario}")
	public ResponseEntity<String> removeCliente(@PathVariable int idUsuario){
		if (bd.apagar(idUsuario)) {
			return ResponseEntity.ok("DELETED");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/usuario/login")
	public ResponseEntity<Usuario> autentica(@RequestBody Usuario usuario) {
		ArrayList<Usuario> lista = bd.buscarTodos();
		for (int i = 0; i < lista.size(); i++) {
			if (usuario.getEmail().equals(lista.get(i).getEmail()) && usuario.getSenha().equals(lista.get(i).getSenha())) {
				return ResponseEntity.ok(usuario);
			}
		}
		return ResponseEntity.status(403).build();
	}
}
