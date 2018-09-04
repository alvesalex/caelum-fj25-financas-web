package br.com.caelum.financas.mb;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.financas.dao.ContaDao;
import br.com.caelum.financas.modelo.Conta;

@Named
@RequestScoped
public class QtdeMovimentacoesDaContaBean {

	private Conta conta = new Conta();
	private int quantidade;

	@Inject
	private ContaDao contaDao;

	public void lista() {
		conta = contaDao.busca(conta.getId());
		quantidade = conta.getMovimentacoes().size();
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
