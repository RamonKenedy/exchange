package com.ifg.exchange.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.ifg.exchange.model.Cliente;
import com.ifg.exchange.repository.ClientesRepository;
import com.ifg.exchange.service.GestaoClientes;
import com.ifg.exchange.util.FacesMessages;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private ClientesRepository clientes;

	@Inject
	private GestaoClientes cadastroCliente;

	@Inject
	private FacesMessages messages;

	private List<Cliente> todosClientes;
	private Cliente clienteEdicao = new Cliente();
	private Cliente clienteSelecionado;

	public void prepararNovoCadastro() {
		clienteEdicao = new Cliente();
	}

	public void salvar() {
		cadastroCliente.salvar(clienteEdicao);
		consultar();

		messages.info("Cliente salvo com sucesso!");

		RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:clientes-table"));
	}

	public void excluir() {
		cadastroCliente.excluir(clienteSelecionado);
		clienteSelecionado = null;

		consultar();

		messages.info("Cliente excluído com sucesso!");
	}

	public void consultar() {
		todosClientes = clientes.todos();
	}

	public List<Cliente> getTodosClientes() {
		return todosClientes;
	}

	public Cliente getClienteEdicao() {
		return clienteEdicao;
	}

	public void setClienteEdicao(Cliente clienteEdicao) {
		this.clienteEdicao = clienteEdicao;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

}
