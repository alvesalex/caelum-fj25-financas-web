package br.com.caelum.financas.mb;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import br.com.caelum.financas.dao.ContaDao;
import br.com.caelum.financas.dao.GerenteDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Gerente;

@Named
@ViewScoped
public class ContasBean implements Serializable {
	private static final long serialVersionUID = -8726507846108513880L;

	private Conta conta = new Conta();
	private List<Conta> contas;

	@Inject
	private ContaDao contaDao;

	@Inject
	private GerenteDao gerenteDao;

	private Integer gerenteId;

	@Inject
	private Validator validator;

	private boolean validar() {
		Set<ConstraintViolation<Conta>> erros = validator.validate(conta);

		erros.forEach((erro) -> {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(erro.getMessage()));
		});

		boolean ehValido = (erros.size() == 0);

		return ehValido;
	}

	public void grava() {
		if (validar()) {
			if (gerenteId != null) {
				Gerente gerente = gerenteDao.busca(gerenteId);
				conta.setGerente(gerente);
			}

			if (conta.getId() == null) {
				contaDao.adiciona(conta);
			} else {
				contaDao.altera(conta);
			}
			contas = contaDao.lista();
			limpaFormularioDoJSF();
		}
	}

	public List<Conta> getContas() {
		if (contas == null) {
			contas = contaDao.lista();
		}
		return contas;
	}

	public void remove() {
		contaDao.remove(conta);
		contas = contaDao.lista();
		limpaFormularioDoJSF();
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento em que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.conta = new Conta();
	}

	public Integer getGerenteId() {
		return gerenteId;
	}

	public void setGerenteId(Integer gerenteId) {
		this.gerenteId = gerenteId;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
}