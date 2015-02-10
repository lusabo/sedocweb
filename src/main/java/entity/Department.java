package entity;

import static javax.persistence.GenerationType.SEQUENCE;
import static util.Constants.ABBREVIATION_SIZE;
import static util.Constants.NAME_SIZE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "DEPARTMENT")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = SEQUENCE, generator = "SEQ_DEPARTMENT")
	@SequenceGenerator(name = "SEQ_DEPARTMENT", sequenceName = "SEQ_DEPARTMENT", allocationSize = 1)
	private Integer id;

	@NotBlank
	@Size(max = NAME_SIZE)
	@Column(name = "NAME")
	@Index(name = "IDX_DEPARTMENT_NAME")
	private String name;

	@NotBlank
	@Size(max = ABBREVIATION_SIZE)
	@Column(name = "ABBREVIATION", unique = true)
	@Index(name = "IDX_DEPARTMENT_ABBREVIATION")
	private String abbreviation;

	@Column(name = "ACTIVE", nullable = false)
	@Index(name = "IDX_DEPARTMENT_ACTIVE")
	private boolean active = true;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Boolean getactive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
