package persistence;

import java.util.List;

import javax.persistence.TypedQuery;

import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;
import entity.JobTitle;

@Transactional
public class JobTitleDAO extends JPACrud<JobTitle, Long> {

	private static final long serialVersionUID = 1L;

	public static JobTitleDAO getInstance() {
		return Beans.getReference(JobTitleDAO.class);
	}
	
	public List<JobTitle> find(String filter, Boolean showInactive){
		StringBuffer q = new StringBuffer();
		q.append("  from JobTitle jt ");
		q.append(" where lower(jt.name) like :name ");
		
		if(!showInactive){
			q.append("   and jt.active is true ");
		}
		
		TypedQuery<JobTitle> query = getEntityManager().createQuery(q.toString(), JobTitle.class);
		query.setParameter("name", "%" + filter.toLowerCase() + "%");
		
		return query.getResultList();
	}
}
