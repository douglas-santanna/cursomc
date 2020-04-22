package com.project1.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project1.domain.Cliente;
import com.project1.dto.ClienteDTO;
import com.project1.dto.ClienteNovoDTO;
import com.project1.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService servicoCliente;
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> buscar(@PathVariable Integer id){
		Cliente objetoCliente = servicoCliente.buscar(id);
		return ResponseEntity.ok().body(objetoCliente);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> buscarTodos(){
		List<Cliente> listaObjetoCliente = servicoCliente.bucarTodos();
		List<ClienteDTO> listaObjetoClienteDTO = listaObjetoCliente.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaObjetoClienteDTO);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> buscarPaginacao(
			@RequestParam(value="pagina", defaultValue="0") Integer pagina,
			@RequestParam(value="registroPorPagina", defaultValue = "24") Integer registroPorPagina,
			@RequestParam(value="ordenacao", defaultValue="ASC") String ordenacao,
			@RequestParam(value="buscarPelaColuna", defaultValue="nome") String buscarPelaColuna
		){
		Page<Cliente> listaObjetoCliente = servicoCliente.buscarPaginacao(pagina, registroPorPagina, ordenacao, buscarPelaColuna);
		Page<ClienteDTO> listaObjetoClienteDTO = listaObjetoCliente.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listaObjetoClienteDTO);	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNovoDTO objetoClienteNovoDTO){
		Cliente objetoCliente= servicoCliente.fromDTO(objetoClienteNovoDTO);
		objetoCliente = servicoCliente.insert(objetoCliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objetoCliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objetoClienteDTO,@PathVariable Integer id){
		Cliente objetoCliente = servicoCliente.fromDTO(objetoClienteDTO);
		objetoCliente.setId(id);
		objetoCliente = servicoCliente.update(objetoCliente);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		servicoCliente.delete(id);
		return ResponseEntity.noContent().build();
	}
}
