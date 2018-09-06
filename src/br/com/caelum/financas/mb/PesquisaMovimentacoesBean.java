package br.com.caelum.financas.mb;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;

@Named
@RequestScoped
public class PesquisaMovimentacoesBean {

	private Conta conta = new Conta();
	private Integer mes;
	private TipoMovimentacao tipoMovimentacao;
	private List<Movimentacao> movimentacoes;

	@Inject
	private MovimentacaoDao movimentacaoDao;

	public void pesquisa() {
		movimentacoes = movimentacaoDao.pesquisa(conta, tipoMovimentacao, mes);
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public Conta getConta() {
		if (conta.getId() != null && conta.getId() == 0) {
			conta.setId(null);
		}
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Integer getMes() {
		if (mes != null && mes == 0) {
			mes = null;
		}
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
}