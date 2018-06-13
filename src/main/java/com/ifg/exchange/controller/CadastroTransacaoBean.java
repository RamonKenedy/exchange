package com.ifg.exchange.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ifg.exchange.model.Cliente;
import com.ifg.exchange.model.TipoCambio;
import com.ifg.exchange.model.Transacao;
import com.ifg.exchange.repository.ClientesRepository;
import com.ifg.exchange.service.GestaoTransacoes;

@Named
@ViewScoped
public class CadastroTransacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GestaoTransacoes gestaoTransacoes;
	@Inject
	private ClientesRepository clientes;

	private Transacao transacao;

	private String clienteSelecionado;
	private List<String> clientesTela;
	private List<Cliente> todosClientes;

	@PostConstruct
	protected void init() {
		/*
		 * // Para criar banco de dados na primeira operação Cliente clienteTeste = new
		 * Cliente(); clienteTeste.setCpf("99999999999");
		 * clienteTeste.setNome("Cliente Teste"); clientes.guardar(clienteTeste);
		 */

		transacao = new Transacao();
		transacao.setMoeda("dolar");
		consultarClientes();
	}

	public void salvar() {
		List<Cliente> clientesBusca = clientes.pesquisar(clienteSelecionado);
		if (clientesBusca != null && clientesBusca.size() > 0) {
			transacao.setCliente(clientesBusca.get(0));
		}
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

	public void consultarClientes() {
		todosClientes = clientes.todos();
		clientesTela = new ArrayList<String>();

		for (Cliente cliente : todosClientes) {
			clientesTela.add(cliente.getNome());
		}
	}

	public Transacao getTransacao() {
		return transacao;
	}

	public String getCliente() {
		return clienteSelecionado;
	}

	public void setCliente(String cliente) {
		this.clienteSelecionado = cliente;
	}

	public List<String> getClientesTela() {
		return clientesTela;
	}

	public void setClientesTela(List<String> clientesTela) {
		this.clientesTela = clientesTela;
	}

}