package entity;

import static javax.persistence.GenerationType.SEQUENCE;
import static util.Constants.NAME_SIZE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "JOB_TITLE")
public class JobTitle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = SEQUENCE, generator = "SEQ_JOB_TITLE")
	@SequenceGenerator(name = "SEQ_JOB_TITLE", sequenceName = "SEQ_JOB_TITLE", allocationSize = 1)
	private Long id;

	@NotNull
	@Size(max = NAME_SIZE)
	@Column(name = "NAME", unique = true)
	@Index(name = "IDX_JOB_TITLE_NAME")
	private String name;

	@Column(name = "ACTIVE", nullable = false)
	@Index(name = "IDX_JOB_TITLE_ACTIVE")
	private Boolean active = true;

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
		JobTitle other = (JobTitle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}