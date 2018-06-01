package com.ifg.exchange.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.ifg.exchange.model.Transacao;
import com.ifg.exchange.repository.TransacoesRepository;
import com.ifg.exchange.util.Transacional;

public class GestaoTransacoes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TransacoesRepository transacoes;
	
	@Transacional
	public void salvar(Transacao transacao) {
		transacoes.guardar(transacao);
	}
	
}