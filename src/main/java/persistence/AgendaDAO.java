package persistence;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;
import entity.Agenda;

@Transactional
public class AgendaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public static AgendaDAO getInstance() {
		return Beans.getReference(AgendaDAO.class);
	}

	public Agenda insert(Agenda entity) {
		em.persist(entity);
		return entity;
	}

}
