package persistence;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;
import entity.User;

@Transactional
public class UserDAO extends JPACrud<User, Integer> {

	private static final long serialVersionUID = 1L;
	
	public static UserDAO getInstance() {
		return Beans.getReference(UserDAO.class);
	}


	// @Override
	// public User insert(User user) {
	// return super.insert(user);
	// }
	//
	// @Override
	// public void delete(Integer id) {
	// User user = load(id);
	// if (user != null) {
	// user.setStatus(INACTIVE);
	// update(user);
	// }
	// }

	public User loadForAuthentication(String username) {
		System.out.println("#### loadForAuthentication 1 ####");
		StringBuffer jpql = new StringBuffer();
		jpql.append(" select ");
		jpql.append("    new User( ");
		jpql.append("        u.id, ");
		jpql.append("        u.username, ");
		jpql.append("        u.password, ");
		jpql.append("        u.name, ");
		jpql.append("        u.initials, ");
		jpql.append("        u.active ");
		jpql.append(" 	     ) ");
		jpql.append("   from User u");
		jpql.append("  where u.username = :username ");
		jpql.append("    and u.active is true");

		TypedQuery<User> query = getEntityManager().createQuery(jpql.toString(), User.class);
		query.setParameter("username", username);

		User result;
		try {
			result = query.getSingleResult();
		} catch (NoResultException cause) {
			result = null;
		}
		return result;
	}

}
