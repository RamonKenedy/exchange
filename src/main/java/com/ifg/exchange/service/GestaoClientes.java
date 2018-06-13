package com.ifg.exchange.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.ifg.exchange.model.Cliente;

import com.ifg.exchange.repository.ClientesRepository;

import com.ifg.exchange.util.Transacional;

public class GestaoClientes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClientesRepository clientes;

	@Transacional
	public void salvar(Cliente cliente) {
		clientes.guardar(cliente);

	}

	@Transacional
	public void excluir(Cliente empresa) {
		clientes.remover(empresa);
	}

}
