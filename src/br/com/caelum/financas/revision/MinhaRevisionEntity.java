package br.com.caelum.financas.revision;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity
@RevisionEntity(MeuRevisionListener.class)
public class MinhaRevisionEntity extends DefaultRevisionEntity {
	private static final long serialVersionUID = -9049111513285950962L;

	private LocalDateTime horario;

	public LocalDateTime getHorario() {
		return horario;
	}

	public void setHorario(LocalDateTime horario) {
		this.horario = horario;
	}
}