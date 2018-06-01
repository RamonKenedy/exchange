package com.ifg.exchange.model;

public enum TipoCambio {

	COMPRA("Compra"),
	VENDA("Venda");
	
	private String descricao;

	TipoCambio(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}