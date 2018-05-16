package com.ifg.exchange.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.ifg.exchange.model.Orcamento;
import com.ifg.exchange.repository.OrcamentosRepository;
import com.ifg.exchange.util.Transacional;

public class GestaoOrcamentos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private OrcamentosRepository orcamentos;
	
	@Transacional
	public void salvar(Orcamento orcamento) {
		orcamentos.guardar(orcamento);
	}
	
}