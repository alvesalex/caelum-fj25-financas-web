package br.com.caelum.financas.revision;

import java.time.LocalDateTime;

import org.hibernate.envers.RevisionListener;

public class MeuRevisionListener implements RevisionListener {
	@Override
	public void newRevision(Object revisionEntity) {
		MinhaRevisionEntity entity = MinhaRevisionEntity.class
				.cast(revisionEntity);

		entity.setHorario(LocalDateTime.now());
	}
}