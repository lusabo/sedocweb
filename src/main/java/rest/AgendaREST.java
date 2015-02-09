package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.hibernate.validator.constraints.NotEmpty;

import persistence.AgendaDAO;
import br.gov.frameworkdemoiselle.security.LoggedIn;
import br.gov.frameworkdemoiselle.util.ValidatePayload;
import entity.Agenda;
import entity.Event;
import entity.Permission;
import entity.User;

@Path("agenda")
public class AgendaREST {

	@POST
	@ValidatePayload
	@LoggedIn
	@Consumes("application/json")
	@Produces("application/json")
	public Agenda create(AgendaData data) throws Exception {

		return AgendaDAO.getInstance().insert(new Agenda(data.event, data.user, data.permission));

	}

	public static class AgendaData {

		@NotEmpty
		public Permission permission;

		@NotEmpty
		public User user;

		@NotEmpty
		public Event event;

	}

}
