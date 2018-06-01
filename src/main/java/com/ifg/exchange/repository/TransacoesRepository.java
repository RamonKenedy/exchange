package com.ifg.exchange.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ifg.exchange.model.Transacao;

public class TransacoesRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Transacao guardar(Transacao transacao) {
		return manager.merge(transacao);
	}

}