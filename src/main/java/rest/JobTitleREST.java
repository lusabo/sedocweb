package rest;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import persistence.JobTitleDAO;
import br.gov.frameworkdemoiselle.BadRequestException;
import br.gov.frameworkdemoiselle.NotFoundException;
import br.gov.frameworkdemoiselle.security.LoggedIn;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ValidatePayload;
import entity.JobTitle;

@Path("jobtitle")
public class JobTitleREST {

	@GET
	@Produces("application/json")
	public List<JobTitle> find(@QueryParam("filter") String filter, @QueryParam("showInactive") Boolean showInactive)
			throws Exception {

		return JobTitleDAO.getInstance().find(filter, showInactive);
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public JobTitle load(@PathParam("id") Long id) throws Exception {

		JobTitle result = JobTitleDAO.getInstance().load(id);

		if (result == null) {
			throw new NotFoundException();
		}

		return result;
	}

	@PUT
	@LoggedIn
	@Path("{id}")
	@Transactional
	@ValidatePayload
	@Produces("application/json")
	@Consumes("application/json")
	public void update(@PathParam("id") Long id, JobTitle entity) throws Exception {
		checkId(entity);
		load(id);

		entity.setId(id);

		JobTitleDAO.getInstance().update(entity);
	}

	@POST
	@LoggedIn
	@Transactional
	@ValidatePayload
	@Produces("application/json")
	@Consumes("application/json")
	public Response insert(JobTitle entity, @Context UriInfo uriInfo) throws Exception {
		checkId(entity);

		String id = JobTitleDAO.getInstance().insert(entity).getId().toString();

		URI location = uriInfo.getRequestUriBuilder().path(id).build();

		return Response.created(location).entity(id).build();
	}

	private void checkId(JobTitle entity) throws Exception {
		if (entity.getId() != null) {
			throw new BadRequestException();
		}
	}

}
