package rest;

import javax.persistence.EntityManager;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import security.Passwords;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;
import entity.User;

@Path("temp")
public class TempREST {

	@POST
	@Path("load")
	@Transactional
	public void load() throws Exception {
		newUser("admin", "admin", "Administrador", "adm", true);
		newUser("luciano", "luciano", "Luciano", "lsb", true);
	}

	private User newUser(String username, String password, String name, String initials, boolean active) {

		User user = new User();
		user.setUsername(username);
		user.setPassword(Passwords.hash(password, username));
		user.setName(name);
		user.setInitials(initials);
		user.setActive(active);
		getEntityManager().persist(user);

		return user;
	}

	private EntityManager getEntityManager() {
		return Beans.getReference(EntityManager.class);
	}
}
