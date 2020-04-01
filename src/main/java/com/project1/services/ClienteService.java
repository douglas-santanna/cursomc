package com.project1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project1.domain.Cliente;
import com.project1.repositories.ClienteRepository;
import com.project1.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repositorioCliente;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> objetoCliente = repositorioCliente.findById(id);
		return objetoCliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: "+ id + ", Tipo Objeto: "+ Cliente.class.getName()));
	}
	
	
}
