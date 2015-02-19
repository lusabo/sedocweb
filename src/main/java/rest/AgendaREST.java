package rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;

import persistence.AgendaDAO;
import persistence.EventDAO;
import persistence.UserDAO;
import br.gov.frameworkdemoiselle.security.LoggedIn;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ValidatePayload;
import entity.Agenda;
import entity.Event;
import entity.PermissionType;
import entity.User;

@Path("agenda")
public class AgendaREST {

	@GET
	@LoggedIn
	@Produces("application/json")
	public List<AgendaData> load() throws Exception {
		User user = User.getLoggedIn();

		List<Agenda> agendas = AgendaDAO.getInstance().find(user);
		List<AgendaData> agendasData = new ArrayList<AgendaData>();

		for (Agenda agenda : agendas) {
			AgendaData agendaData = new AgendaData();

			agendaData.id = agenda.getEvent().getId();
			agendaData.title = agenda.getEvent().getTitle();
			agendaData.description = agenda.getEvent().getDescription();
			agendaData.start = agenda.getEvent().getStart();
			agendaData.finish = agenda.getEvent().getFinish();
			agendaData.user = agenda.getUser();
			agendaData.permissionType = agenda.getPermissionType();

			agendasData.add(agendaData);
		}

		return agendasData;
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
		event.setStart(data.start);
		event.setFinish(data.finish);

		EventDAO.getInstance().insert(event);

		User user = UserDAO.getInstance().load(data.user.getId());
		
		Agenda agenda = new Agenda();
		agenda.setEvent(event);
		agenda.setUser(user);
		agenda.setPermissionType(data.permissionType);

		AgendaDAO.getInstance().insert(agenda);
	}

	public static class AgendaData {

		public Integer id;

		@NotEmpty
		public String title;

		public String description;

		@NotNull
		public Date start;

		@NotNull
		public Date finish;

		@NotNull
		public User user;

		@NotNull
		public PermissionType permissionType;

	}
}
