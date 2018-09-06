package br.com.caelum.financas.mb;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Movimentacao;

@Named
@RequestScoped
public class SearchPesquisaTextualBean {
	private String descricao;
	private List<Movimentacao> movimentacoes;

	@Inject
	private MovimentacaoDao movimentacaoDao;

	public void pesquisar() {
		movimentacoes = movimentacaoDao.buscaPorDescricao(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}
}