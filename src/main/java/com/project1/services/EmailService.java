package com.project1.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.project1.domain.Pedido;

public interface EmailService {
	
	void envioDeConfirmacaoDePedido(Pedido obj);
	
	void enviarEmail(SimpleMailMessage msg);
	
	void envioDeConfirmacaoDePedidoHTML(Pedido obj);
	
	void enviarEmailHTML(MimeMessage msg);
	
}
