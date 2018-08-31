package br.com.caelum.financas.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.exception.ValorInvalidoException;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.modelo.ValorPorMesEAno;

@Stateless
public class MovimentacaoDao {

	@PersistenceContext
	EntityManager manager;

	public void adiciona(Movimentacao movimentacao) {
		this.manager.persist(movimentacao);

		if (movimentacao.getValor().compareTo(BigDecimal.ZERO) < 0) {
			throw new ValorInvalidoException("Movimentação negativa");
		}
	}

	public void altera(Movimentacao movimentacao) {
		this.manager.merge(movimentacao);
	}

	public Movimentacao busca(Integer id) {
		return this.manager.find(Movimentacao.class, id);
	}

	public List<Movimentacao> lista() {
		return this.manager.createQuery("select m from Movimentacao m",
				Movimentacao.class).getResultList();
	}

	public List<Movimentacao> movimentacoesDaConta(Conta conta) {
		TypedQuery<Movimentacao> query = this.manager
				.createQuery(
						"select m from Movimentacao m where m.conta = :conta order by m.valor desc",
						Movimentacao.class);
		query.setParameter("conta", conta);
		return query.getResultList();
	}

	public List<Movimentacao> listaPorValorETipo(BigDecimal valor,
			TipoMovimentacao tipoMovimentacao) {
		TypedQuery<Movimentacao> query = this.manager
				.createQuery(
						"select m from Movimentacao m where m.valor = :valor and m.tipoMovimentacao = :tipoMovimentacao",
						Movimentacao.class);
		query.setParameter("valor", valor);
		query.setParameter("tipoMovimentacao", tipoMovimentacao);
		return query.getResultList();
	}

	public List<Movimentacao> buscaTodasMovimentacoesDaConta(String titular) {
		TypedQuery<Movimentacao> query = this.manager
				.createQuery(
						"select m from Movimentacao m where m.conta.titular like :titular",
						Movimentacao.class);
		query.setParameter("titular", "%" + titular + "%");
		return query.getResultList();
	}

	public BigDecimal calculaTotalMovimentado(Conta conta,
			TipoMovimentacao tipoMovimentacao) {
		TypedQuery<BigDecimal> query = this.manager
				.createQuery(
						"select sum(m.valor) from Movimentacao m where m.conta = :conta and m.tipoMovimentacao = :tipoMovimentacao",
						BigDecimal.class);
		query.setParameter("conta", conta);
		query.setParameter("tipoMovimentacao", tipoMovimentacao);
		return query.getSingleResult();
	}

	public List<ValorPorMesEAno> listaMesesComMovimentacoes(Conta conta,
			TipoMovimentacao tipoMovimentacao) {
		TypedQuery<ValorPorMesEAno> query = this.manager
				.createQuery(
						"select new br.com.caelum.financas.modelo.ValorPorMesEAno"
								+ "(month(m.data), year(m.data), sum(m.valor)) from Movimentacao m "
								+ "where m.conta = :conta and m.tipoMovimentacao = :tipoMovimentacao "
								+ "group by year(m.data), month(m.data) "
								+ "order by sum(valor) desc",
						ValorPorMesEAno.class);
		query.setParameter("conta", conta);
		query.setParameter("tipoMovimentacao", tipoMovimentacao);
		return query.getResultList();
	}

	public void remove(Movimentacao movimentacao) {
		Movimentacao movimentacaoParaRemover = this.manager.find(
				Movimentacao.class, movimentacao.getId());
		this.manager.remove(movimentacaoParaRemover);
	}

}
