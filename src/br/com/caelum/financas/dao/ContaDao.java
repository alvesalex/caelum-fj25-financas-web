package br.com.caelum.financas.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;

@Stateless
public class ContaDao {

	@Inject
	private EntityManager manager;

	public void adiciona(Conta conta) {
		manager.joinTransaction();
		this.manager.persist(conta);
	}

	public void altera(Conta conta) {
		manager.joinTransaction();
		this.manager.merge(conta);
	}

	public Conta busca(Integer id) {
		return this.manager.find(Conta.class, id);
	}

	public List<Conta> lista() {
		return this.manager.createQuery("select c from Conta c", Conta.class)
				.getResultList();
	}

	public void remove(Conta conta) {
		manager.joinTransaction();
		Conta contaParaRemover = this.manager.find(Conta.class, conta.getId());
		this.manager.remove(contaParaRemover);
	}

	public int trocaNomeDoBancoEmLote(String nomeBancoAntigo,
			String nomeBancoNovo) {
		String jpql = "UPDATE Conta c SET c.banco = :nomeBancoNovo "
				+ "WHERE c.banco = :nomeBancoAntigo";

		Query query = manager.createQuery(jpql);
		query.setParameter("nomeBancoNovo", nomeBancoNovo);
		query.setParameter("nomeBancoAntigo", nomeBancoAntigo);

		return query.executeUpdate();
	}
}