package br.com.caelum.financas.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Categoria;

@Stateless
public class CategoriaDao implements Serializable {
	private static final long serialVersionUID = -2525219831157756895L;

	@Inject
	private EntityManager em;

	public Categoria procura(Integer id) {
		return em.find(Categoria.class, id);
	}

	public List<Categoria> lista() {
		em.joinTransaction();
		return em.createQuery("select c from Categoria c", Categoria.class)
				.getResultList();
	}
}