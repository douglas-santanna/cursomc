package com.project1.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.project1.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String emailDeEnvio;
	
	public void envioDeConfirmacaoDePedido(Pedido pedido) {
		SimpleMailMessage sm = prepareSimpleMailMessagemFromPedido(pedido);
		enviarEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessagemFromPedido(Pedido pedido) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(pedido.getCliente().getEmail());
		sm.setFrom(emailDeEnvio);
		sm.setSubject("O pedido ocorreu corretamente. O número é: "+ pedido.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(pedido.toString());
		return sm;
	}
	
}
