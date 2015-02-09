package entity;

import static javax.persistence.GenerationType.SEQUENCE;
import static util.Constants.NAME_SIZE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "EVENT")
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = SEQUENCE, generator = "SEQ_EVENT")
	@SequenceGenerator(name = "SEQ_EVENT", sequenceName = "SEQ_EVENT", allocationSize = 1)
	private Integer id;

	@NotBlank
	@Size(max = NAME_SIZE)
	@Column(name = "TITLE")
	@Index(name = "IDX_EVENT_TITLE")
	private String title;

	@Column(name = "DESCRIPTION", columnDefinition = "TEXT")
	private String description;

	@NotBlank
	@Column(name = "START")
	@Temporal(TemporalType.TIMESTAMP)
	@Index(name = "IDX_EVENT_START")
	private Date start;

	@NotBlank
	@Column(name = "FINISH")
	@Temporal(TemporalType.TIMESTAMP)
	@Index(name = "IDX_EVENT_FINISH")
	private Date finish;

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
		Event other = (Event) obj;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getFinish() {
		return finish;
	}

	public void setFinish(Date finish) {
		this.finish = finish;
	}

}
