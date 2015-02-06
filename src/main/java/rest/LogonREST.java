package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.hibernate.validator.constraints.NotEmpty;

import br.gov.frameworkdemoiselle.security.Credentials;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.ValidatePayload;
import entity.User;

@Path("logon")
public class LogonREST {

	@POST
	@ValidatePayload
	@Consumes("application/json")
	@Produces("application/json")
	public User login(CredentialsData data) throws Exception {
		Credentials credentials = Beans.getReference(Credentials.class);
		credentials.setUsername(data.username);
		credentials.setPassword(data.password);

		SecurityContext securityContext = Beans.getReference(SecurityContext.class);
		securityContext.login();
		return User.getLoggedIn();
	}

	public static class CredentialsData {

		@NotEmpty
		public String username;

		@NotEmpty
		public String password;
	}

}
