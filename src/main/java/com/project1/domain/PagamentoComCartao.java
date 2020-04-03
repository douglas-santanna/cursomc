package com.project1.domain;

import javax.persistence.Entity;

import com.project1.domain.enums.StatusPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	private Integer numeroParcelas; 
	
	public PagamentoComCartao() {
	}

	public PagamentoComCartao(Integer id, StatusPagamento statusPagamento, Pedido pedido, Integer numeroParcelas) {
		super(id, statusPagamento, pedido);
		this.numeroParcelas = numeroParcelas;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	
	
	
}
