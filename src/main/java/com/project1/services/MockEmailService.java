package com.project1.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void enviarEmail(SimpleMailMessage msg) {
		LOG.info("Enviando e-mail");
		LOG.info(msg.toString());
		LOG.info("E-mail enviado com sucesso");
	}

	@Override
	public void enviarEmailHTML(MimeMessage msg) {
		LOG.info("Enviando e-mail de HTML");
		LOG.info(msg.toString());
		LOG.info("E-mail enviado com sucesso");
	}


}
