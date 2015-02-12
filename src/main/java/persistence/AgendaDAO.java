package persistence;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;
import entity.Agenda;
import entity.User;

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

	public List<Agenda> find(User user) {
		StringBuffer jpql = new StringBuffer();
		jpql.append(" select  ");
		jpql.append("    new Agenda( ");
		jpql.append("        e.id, ");
		jpql.append("        e.title, ");
		jpql.append("        e.description, ");
		jpql.append("        e.start, ");
		jpql.append("        e.finish, ");
		jpql.append("        u.id, ");
		jpql.append("        a.permissionType ");
		jpql.append("    ) ");
		jpql.append("   from Agenda a ");
		jpql.append("   join a.event e ");
		jpql.append("   join a.user u ");
		jpql.append("  where u = :user ");

		TypedQuery<Agenda> query = em.createQuery(jpql.toString(), Agenda.class);
		query.setParameter("user", user);

		return query.getResultList();
	}
}
