package srping_boot.curso_jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import srping_boot.curso_jdev_treinamento.model.Usuario;
import srping_boot.curso_jdev_treinamento.repository.UsuarioRepository;

@RestController
public class GreetingsController {

	@Autowired /* Injeção de Dependência */
	private UsuarioRepository usuarioRepository;

//	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
//	@ResponseStatus(HttpStatus.OK)
//	public String greetingText(@PathVariable String name) {
//		return "index.html";
//	}

	@RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String retornaOlaMundo(@PathVariable String nome) {
		Usuario usuario = new Usuario();

		usuario.setNome(nome);
		usuarioRepository.save(usuario);
		return "Olá Mundo: " + nome;
	}

	@GetMapping(value = "/listarTodos")
	@ResponseBody /* Retorna os dados para o corpo da resposta */
	public ResponseEntity<List<Usuario>> listaUsuario() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); /* Retorna a Lista em JSON */
	}

	@PostMapping(value = "/salvar")
	@ResponseBody
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
		Usuario user = usuarioRepository.save(usuario);
		
		return  new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}
	
	
	@PutMapping(value = "/atualizar")
	@ResponseBody
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {
		
		if(usuario.getId() == null) {
			return  new ResponseEntity<String>("Id Não foi informado para fazer a atualização!!", HttpStatus.CREATED);
		}
		Usuario user = usuarioRepository.saveAndFlush(usuario);
		
		return  new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity<String> delete(@RequestParam Long iduser) {

		usuarioRepository.deleteById(iduser);
		return  new ResponseEntity<String>("Usuario deletado com sucesso", HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarUserId")
	@ResponseBody
	public ResponseEntity<Usuario> buscarUserId(@RequestParam Long idUser) {
		
		Usuario user =  usuarioRepository.findById(idUser).get();
		return  new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarPorNome")
	@ResponseBody
	public ResponseEntity< List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name) {
		
		List<Usuario> listaUsuario =  usuarioRepository.buscarPornome(name.trim().toUpperCase());
		
		return  new ResponseEntity<List<Usuario>>(listaUsuario, HttpStatus.OK);
	}
}











		
		
		
		
		
		
		
		
		
		
		
