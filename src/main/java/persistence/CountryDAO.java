package persistence;

import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;
import entity.Country;

@Transactional
public class CountryDAO extends JPACrud<Country, Integer> {

	private static final long serialVersionUID = 1L;

	public static CountryDAO getInstance() {
		return Beans.getReference(CountryDAO.class);
	}
}
