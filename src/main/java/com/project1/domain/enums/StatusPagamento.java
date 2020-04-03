package com.project1.domain.enums;

public enum StatusPagamento {
	
	PENDENTE(1, "Pendente"),
	PAGO(2, "Pago"),
	CANCELADO(3, "Cancelado");
	
	private Integer cod;
	private String descricao;
	
	private StatusPagamento( Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static StatusPagamento toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(StatusPagamento x : StatusPagamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Cod: "+ cod +" não encontrado: ");
		
	}
	
}
