package com.ifg.exchange.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.rpc.ServiceException;

import org.hibernate.validator.constraints.NotEmpty;

import com.fincatto.cotacao.classes.Cotacao;
import com.fincatto.cotacao.classes.Indice;
import com.fincatto.cotacao.ws.WSConsulta;

@Entity
@Table(name = "transacao")
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String cliente;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_cambio")
	private TipoCambio tipoCambio;

	@NotEmpty
	private String moeda;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal quantidade = BigDecimal.ZERO;

	@Column(name = "valor_unit", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorUnitario = BigDecimal.ZERO;

	@Column(name = "dias_entrega")
	private Integer diasEntrega;

	@Temporal(TemporalType.DATE)
	private Date data;

	public BigDecimal getValorTotal() {
		return quantidade.multiply(valorUnitario).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public TipoCambio getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(TipoCambio tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public String getMoeda() {
		return moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		try {
			Cotacao cotacao = new WSConsulta().getCotacao(Indice.DOLAR_VENDA, LocalDate.now());
			valorUnitario = cotacao.getValor();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getDiasEntrega() {
		return diasEntrega;
	}

	public void setDiasEntrega(Integer diasEntrega) {
		this.diasEntrega = diasEntrega;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}