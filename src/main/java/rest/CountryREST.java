package rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hibernate.validator.constraints.NotEmpty;

import persistence.CountryDAO;
import br.gov.frameworkdemoiselle.BadRequestException;
import br.gov.frameworkdemoiselle.security.LoggedIn;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ValidatePayload;
import entity.Country;

@Path("country")
public class CountryREST {

	@GET
	@Produces("application/json")
	public List<CountryData> findAll() throws Exception {
		List<Country> resultCountry;
		List<CountryData> resultCountryData  = new ArrayList<CountryData>();
		resultCountry = CountryDAO.getInstance().findAll();

		for (Country country : resultCountry) {
			CountryData countryData = new CountryData();
			countryData.id = country.getId();
			countryData.name = country.getName();
			countryData.abbreviation = country.getAbbreviation();
			resultCountryData.add(countryData);
		}

		return resultCountryData;
	}

	@POST
	@LoggedIn
	@Transactional
	@ValidatePayload
	@Produces("application/json")
	@Consumes("application/json")
	public Response insert(CountryData entity, @Context UriInfo uriInfo) throws Exception {
		checkId(entity);

		Country country = new Country();
		country.setName(entity.name);
		country.setAbbreviation(entity.abbreviation);

		String id = CountryDAO.getInstance().insert(country).getId().toString();

		URI location = uriInfo.getRequestUriBuilder().path(id).build();

		return Response.created(location).entity(id).build();
	}

	private void checkId(CountryData entity) throws Exception {
		if (entity.id != null) {
			throw new BadRequestException();
		}
	}

	/*
	 * @GET
	 * @Produces("application/json") public List<Bookmark> find(@QueryParam("q") String query) throws Exception {
	 * List<Bookmark> result; if (Strings.isEmpty(query)) { result = bc.findAll(); } else { result = bc.find(query); }
	 * return result; }
	 * @GET
	 * @Path("{id}")
	 * @Produces("application/json") public Bookmark load(@PathParam("id") Long id) throws Exception { Bookmark result =
	 * bc.load(id); if (result == null) { throw new NotFoundException(); } return result; }
	 * @POST
	 * @LoggedIn
	 * @Transactional
	 * @ValidatePayload
	 * @Produces("application/json")
	 * @Consumes("application/json") public Response insert(Bookmark entity, @Context UriInfo uriInfo) throws Exception
	 * { checkId(entity); String id = bc.insert(entity).getId().toString(); URI location =
	 * uriInfo.getRequestUriBuilder().path(id).build(); return Response.created(location).entity(id).build(); }
	 * @PUT
	 * @LoggedIn
	 * @Path("{id}")
	 * @Transactional
	 * @ValidatePayload
	 * @Produces("application/json")
	 * @Consumes("application/json") public void update(@PathParam("id") Long id, Bookmark entity) throws Exception {
	 * checkId(entity); load(id); entity.setId(id); bc.update(entity); }
	 * @DELETE
	 * @LoggedIn
	 * @Path("{id}")
	 * @Transactional public void delete(@PathParam("id") Long id) throws Exception { load(id); bc.delete(id); }
	 * @DELETE
	 * @LoggedIn
	 * @Transactional public void delete(List<Long> ids) throws Exception { bc.delete(ids); }
	 */

	public static class CountryData {

		public Integer id;

		@NotEmpty
		public String name;

		@NotEmpty
		public String abbreviation;

	}
}
