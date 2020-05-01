package com.project1.services;

import org.springframework.mail.SimpleMailMessage;

import com.project1.domain.Pedido;

public interface EmailService {
	
	void envioDeConfirmacaoDePedido(Pedido obj);
	
	void enviarEmail(SimpleMailMessage msg);
	
}
