package com.project1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.project1.domain.Cliente;
import com.project1.dto.ClienteDTO;
import com.project1.repositories.ClienteRepository;
import com.project1.services.exception.DataIntegrityException;
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
	
	public List<Cliente> bucarTodos(){
		return repositorioCliente.findAll();
	}
	
	public Page<Cliente> buscarPaginacao(Integer pagina, Integer registroPorPagina, String ordenacao, String buscarPelaColuna){
		PageRequest pageRequest = PageRequest.of(pagina, registroPorPagina, Direction.valueOf(ordenacao), buscarPelaColuna);
		return repositorioCliente.findAll(pageRequest);
	}
	
	public Cliente update(Cliente objetoCliente) {
		Cliente objetoClienteNovo = buscar(objetoCliente.getId());
		updateAux(objetoClienteNovo, objetoCliente);
		return repositorioCliente.save(objetoClienteNovo);
	}
	
	public void updateAux(Cliente objetoClienteNovo, Cliente objetoCliente) {
		objetoClienteNovo.setNome(objetoCliente.getNome());
		objetoClienteNovo.setEmail(objetoCliente.getEmail());
	}
	
	public void delete(Integer id){
		buscar(id);
		try{
			repositorioCliente.deleteById(id);
		}catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir um cliente com pedidos associados");
		}
	}
	
	public Cliente fromDTO(ClienteDTO objetoClienteDTO) {
		return new Cliente(objetoClienteDTO.getId(), objetoClienteDTO.getNome(), objetoClienteDTO.getEmail(), null, null);
	}
	
}
