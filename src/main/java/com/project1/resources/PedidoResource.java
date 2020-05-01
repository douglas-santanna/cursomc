package com.project1.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project1.domain.Pedido;
import com.project1.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService servicoPedido;
	 
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Pedido> buscar(@PathVariable Integer id){
		Pedido objetoPedido = servicoPedido.buscar(id);
		return ResponseEntity.ok().body(objetoPedido);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido objetoPedido){
		objetoPedido = servicoPedido.insert(objetoPedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objetoPedido.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
