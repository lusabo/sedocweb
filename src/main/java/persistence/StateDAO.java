package persistence;

import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;
import entity.State;

@Transactional
public class StateDAO extends JPACrud<State, Integer> {

	private static final long serialVersionUID = 1L;

	public static StateDAO getInstance() {
		return Beans.getReference(StateDAO.class);
	}
}
