/** TODO 
 *	Como verificar se allDay não for nulo e vier os horários a melhor forma de desconsiderar.  	
 */

package rest;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.hibernate.validator.constraints.NotEmpty;

import persistence.AgendaDAO;
import persistence.EventDAO;
import persistence.UserDAO;
import br.gov.frameworkdemoiselle.security.LoggedIn;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ValidatePayload;
import entity.Agenda;
import entity.Event;
import entity.Permission;

@Path("agenda")
public class AgendaREST {

	@GET
	@LoggedIn
	@Produces("application/json")
	public AgendaData load() throws Exception {
		return new AgendaData();
	}

	@POST
	@LoggedIn
	@Transactional
	@Consumes("application/json")
	@ValidatePayload
	public void create(AgendaData data) throws Exception {

		Event event = new Event();
		event.setTitle(data.title);
		event.setDescription(data.description);
		event.setDateStart(data.dateStart);
		event.setDateFinish(data.dateFinish);

		if (data.timeStart != null) {
			event.setTimeStart(data.timeStart);
		}

		if (data.timeFinish != null) {
			event.setTimeFinish(data.timeFinish);
		}

		if (data.allDay != null) {
			event.setAllDay(data.allDay);
		}

		EventDAO.getInstance().insert(event);

		for (UserPermission userPermission : data.permissions) {
			Agenda agenda = new Agenda();
			agenda.setEvent(event);
			agenda.setPermission(userPermission.permission);
			agenda.setUser(UserDAO.getInstance().load(userPermission.user));
			AgendaDAO.getInstance().insert(agenda);
		}

	}

	public static class AgendaData {

		@NotEmpty
		public String title;

		public String description;

		public Boolean allDay;

		@NotNull
		public Date dateStart;

		public Time timeStart;

		@NotNull
		public Date dateFinish;

		public Time timeFinish;

		@NotEmpty
		public List<UserPermission> permissions;

	}

	public static class UserPermission {

		@NotNull
		public Integer user;

		@NotNull
		public Permission permission;

	}

}
