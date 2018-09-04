package br.com.caelum.financas.mb;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Cache;

import br.com.caelum.financas.modelo.Conta;

@Named
@RequestScoped
public class CacheInvalidaBean {

	private Integer id;
	private Conta conta;

	@Inject
	private Cache cache;

	public void invalidar() {
		cache.evict(Conta.class, id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Conta getConta() {
		return conta;
	}
}
