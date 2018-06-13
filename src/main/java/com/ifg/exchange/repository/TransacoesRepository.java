package com.ifg.exchange.repository;

import java.io.Serializable;
import java.util.List;

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

	public List<Transacao> todas() {
		return manager.createQuery("from Transacao", Transacao.class).getResultList();
	}

	public void remover(Transacao transacao) {
		transacao = porId(transacao.getId());
		manager.remove(transacao);
	}

	public Transacao porId(Long id) {
		return manager.find(Transacao.class, id);
	}
}