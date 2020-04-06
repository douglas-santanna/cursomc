package com.project1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project1.domain.Pedido;
import com.project1.repositories.PedidoRepository;
import com.project1.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repositorioPedido;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> objetoPedido = repositorioPedido.findById(id);
		return objetoPedido.orElseThrow(() -> new ObjectNotFoundException(
				"Nenhum objeto encontrado! id: "+ id+", Tipo do objeto "+ Pedido.class.getName()));
				
	}
	
}
