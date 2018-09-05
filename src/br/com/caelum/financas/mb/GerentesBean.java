package br.com.caelum.financas.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.financas.dao.GerenteDao;
import br.com.caelum.financas.modelo.Gerente;

@Named
@ViewScoped
public class GerentesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Gerente gerente = new Gerente();

	@Inject
	private GerenteDao gerenteDao;

	private List<Gerente> gerentes;

	private Integer gerenteId;

	private void limpaFormularioDoJSF() {
		this.gerente = new Gerente();
		gerenteId = null;
	}

	public void grava() {
		if (gerente.getId() == null) {
			gerenteDao.adiciona(gerente);
		} else {
			gerenteDao.altera(gerente);
		}
		gerentes = gerenteDao.lista();
		limpaFormularioDoJSF();
	}

	public void remove() {
		gerenteDao.remove(gerente);
		gerentes = gerenteDao.lista();
		limpaFormularioDoJSF();
	}

	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}

	public List<Gerente> getGerentes() {
		if (gerentes == null) {
			gerentes = gerenteDao.lista();
		}
		return gerentes;
	}

	public Integer getGerenteId() {
		return gerenteId;
	}

	public void setGerenteId(Integer gerenteId) {
		this.gerenteId = gerenteId;
	}
}