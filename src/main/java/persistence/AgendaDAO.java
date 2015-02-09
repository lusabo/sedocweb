package persistence;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;
import entity.Agenda;
import entity.Event;

@Transactional
public class AgendaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static AgendaDAO getInstance() {
		return Beans.getReference(AgendaDAO.class);
	}

	@Inject
	private EntityManager em;

	public Agenda insert(Agenda entity) {
		
		Event event = EventDAO.getInstance().insert(entity.getEvent());
		em.persist(entity);
		return entity;
	}
}
