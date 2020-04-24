package com.project1.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.project1.domain.Cliente;
import com.project1.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Campo obrigatório")
	@Length(min=3, max=120, message = "Deve conter mais que 3 caracteres e mais que 120")
	private String nome;
	
	@NotEmpty(message="Campo obrigatório")
	@Email(message = "E-mail inválido")
	private String email;
	
	public ClienteDTO() {		
	}
	
	public ClienteDTO(Cliente objetoCliente) {
		id = objetoCliente.getId();
		nome = objetoCliente.getNome();
		email = objetoCliente.getEmail();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	};
	
	
}
