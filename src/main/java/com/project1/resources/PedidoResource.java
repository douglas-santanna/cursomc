package com.project1.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project1.domain.Pedido;
import com.project1.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService servicoPedido;
	 
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Integer id){
		Pedido objetoPedido = servicoPedido.buscar(id);
		return ResponseEntity.ok().body(objetoPedido);
	}
	
}
