package br.com.caelum.financas.modelo;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

import br.com.caelum.financas.validator.NumeroEAgencia;

@Entity
@Cacheable
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "agencia",
		"numero" }) })
@NumeroEAgencia
@Audited
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Pattern(regexp = "[A-Z].*")
	private String titular;
	private String agencia;
	private String numero;

	@NotNull
	@Size(min = 3, max = 20)
	@Column(nullable = false, length = 20)
	private String banco;

	@OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
	@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
	private List<Movimentacao> movimentacoes;

	@OneToOne
	@JoinColumn(unique = true)
	private Gerente gerente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}
}