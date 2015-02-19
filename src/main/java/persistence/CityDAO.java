package persistence;

import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;
import entity.City;

@Transactional
public class CityDAO extends JPACrud<City, Integer> {

	private static final long serialVersionUID = 1L;

	public static CityDAO getInstance() {
		return Beans.getReference(CityDAO.class);
	}
}
