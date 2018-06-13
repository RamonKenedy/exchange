package com.ifg.exchange.controller;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ifg.exchange.model.TipoCambio;
import com.ifg.exchange.model.Transacao;
import com.ifg.exchange.service.GestaoTransacoes;

@Named
@ViewScoped
public class CadastroTransacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GestaoTransacoes gestaoTransacoes;

	private Transacao transacao;

	@PostConstruct
	protected void init() {
		transacao = new Transacao();
		transacao.setMoeda("dolar");
	}

	public void salvar() {
		transacao.setData(new Date());
		gestaoTransacoes.salvar(transacao);
		transacao = new Transacao();

		FacesMessage msg = new FacesMessage("Transação realizada com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String getNomeBotao() {
		if (TipoCambio.VENDA.equals(transacao.getTipoCambio())) {
			return "Vender";
		}
		return "Comprar";
	}

	public Transacao getTransacao() {
		return transacao;
	}

}