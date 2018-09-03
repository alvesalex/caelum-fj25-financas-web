package br.com.caelum.financas.mb;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.financas.dao.CategoriaDao;
import br.com.caelum.financas.dao.ContaDao;
import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;

@Named
@ViewScoped
public class MovimentacoesBean implements Serializable {
	private static final long serialVersionUID = 590502968306083042L;

	private List<Movimentacao> movimentacoes;
	private Movimentacao movimentacao = new Movimentacao();
	private Integer contaId;
	private Integer categoriaId;
	private List<Categoria> categorias;

	@Inject
	private MovimentacaoDao movimentacaoDao;

	@Inject
	private ContaDao contaDao;

	@Inject
	private CategoriaDao categoriaDao;

	public void grava() {
		Movimentacao movimentacao = getMovimentacao();

		Conta conta = contaDao.busca(contaId);
		movimentacao.setConta(conta);

		if (movimentacao.getId() == null) {
			movimentacaoDao.adiciona(movimentacao);
		} else {
			movimentacaoDao.altera(movimentacao);
		}

		movimentacoes = movimentacaoDao.lista();
		limpaFormularioDoJSF();
	}

	public void remove() {
		movimentacaoDao.remove(movimentacao);
		movimentacoes = movimentacaoDao.lista();
		limpaFormularioDoJSF();
	}

	public void adicionaCategoria() {
		if (categoriaId != null && categoriaId > 0) {
			Categoria categoria = categoriaDao.procura(categoriaId);
			movimentacao.getCategorias().add(categoria);
		}
	}

	public List<Movimentacao> getMovimentacoes() {
		if (movimentacoes == null) {
			movimentacoes = movimentacaoDao.lista();
		}
		return movimentacoes;
	}

	public List<Categoria> getCategorias() {
		if (categorias == null) {
			categorias = categoriaDao.lista();
		}
		return categorias;
	}

	public Movimentacao getMovimentacao() {
		if (movimentacao.getData() == null) {
			movimentacao.setData(LocalDateTime.now());
		}
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public Integer getContaId() {
		return contaId;
	}

	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento manager que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.movimentacao = new Movimentacao();
	}

	public TipoMovimentacao[] getTiposDeMovimentacao() {
		return TipoMovimentacao.values();
	}
}
