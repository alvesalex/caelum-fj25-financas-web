package br.com.caelum.financas.modelo;

import java.math.BigDecimal;

public class ValorPorMesEAno {
	private Integer mes;
	private Integer ano;
	private BigDecimal valor;

	public ValorPorMesEAno() {
		
	}

	public ValorPorMesEAno(Integer mes, Integer ano, BigDecimal valor) {
		this.mes = mes;
		this.ano = ano;
		this.valor = valor;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
