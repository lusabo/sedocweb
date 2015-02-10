package entity;

import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.TemporalType.DATE;
import static util.Constants.NAME_SIZE;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
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

	@NotNull
	@Column(name = "DATE_START")
	@Temporal(DATE)
	@Index(name = "IDX_EVENT_DATESTART")
	private Date dateStart;

	@Column(name = "TIME_START")
	@Index(name = "IDX_EVENT_TIMESTART")
	private Time timeStart;

	@NotNull
	@Column(name = "DATE_FINISH")
	@Temporal(DATE)
	@Index(name = "IDX_EVENT_DATEFINISH")
	private Date dateFinish;

	@Column(name = "TIME_FINISH")
	@Index(name = "IDX_EVENT_TIMEFINISH")
	private Time timeFinish;

	@Column(name = "ALLDAY", nullable = false)
	@Index(name = "IDX_EVENT_ALLDAY")
	private boolean allDay = false;

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

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Time timeStart) {
		this.timeStart = timeStart;
	}

	public Date getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}

	public Date getTimeFinish() {
		return timeFinish;
	}

	public void setTimeFinish(Time timeFinish) {
		this.timeFinish = timeFinish;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

}
