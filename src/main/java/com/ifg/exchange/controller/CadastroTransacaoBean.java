package com.ifg.exchange.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.ifg.exchange.model.Cliente;
import com.ifg.exchange.model.TipoCambio;
import com.ifg.exchange.model.Transacao;
import com.ifg.exchange.repository.ClientesRepository;
import com.ifg.exchange.repository.TransacoesRepository;
import com.ifg.exchange.service.GestaoTransacoes;
import com.ifg.exchange.util.FacesMessages;

@Named
@ViewScoped
public class CadastroTransacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TransacoesRepository transacoes;

	@Inject
	private GestaoTransacoes gestaoTransacoes;

	@Inject
	private FacesMessages messages;

	@Inject
	private ClientesRepository clientes;

	private Transacao transacaoEdicao = new Transacao();
	private Transacao transacaoSelecionada;
	private List<Transacao> todasTransacoes;

	private String clienteSelecionado;
	private List<String> clientesTela;
	private List<Cliente> todosClientes;

	public void prepararNovoCadastro() {
		transacaoEdicao = new Transacao();
		transacaoEdicao.setMoeda("dolar");
		consultarClientes();
	}

	public void salvar() {
		List<Cliente> clientesBusca = clientes.pesquisar(clienteSelecionado);
		if (clientesBusca != null && clientesBusca.size() > 0) {
			transacaoEdicao.setCliente(clientesBusca.get(0));
		}
		transacaoEdicao.setData(new Date());

		gestaoTransacoes.salvar(transacaoEdicao);
		consultar();

		messages.info("Transação salva com sucesso!");

		RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:transacoes-table"));
	}

	public String getNomeBotao() {
		if (TipoCambio.VENDA.equals(transacaoEdicao.getTipoCambio())) {
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

	public void excluir() {
		gestaoTransacoes.excluir(transacaoSelecionada);
		transacaoSelecionada = null;

		consultar();

		messages.info("Transação excluída com sucesso!");
	}

	public void consultar() {
		todasTransacoes = transacoes.todas();
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

	public List<Transacao> getTodasTransacoes() {
		return todasTransacoes;
	}

	public void setTodasTransacoes(List<Transacao> todasTransacoes) {
		this.todasTransacoes = todasTransacoes;
	}

	public Transacao getTransacaoEdicao() {
		return transacaoEdicao;
	}

	public void setTransacaoEdicao(Transacao transacaoEdicao) {
		this.transacaoEdicao = transacaoEdicao;
	}

	public Transacao getTransacaoSelecionada() {
		return transacaoSelecionada;
	}

	public void setTransacaoSelecionada(Transacao transacaoSelecionada) {
		this.transacaoSelecionada = transacaoSelecionada;
	}

}