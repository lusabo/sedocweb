package entity;

import static javax.persistence.EnumType.STRING;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ForeignKey;

@Entity
@IdClass(AgendaPk.class)
@Table(name = "AGENDA")
public class Agenda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "EVENT_ID")
	@ForeignKey(name = "FK_AGENDA_EVENT")
	private Event event;

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "USER_ID")
	@ForeignKey(name = "FK_AGENDA_USER")
	private User user;

	@NotNull
	@Column(name = "PERMISSION_TYPE", length = 5)
	@Enumerated(STRING)
	private PermissionType permissionType;

	public Agenda() {
	}

	public Agenda(Integer eventId, String eventTitle, String eventDescription, Date eventStart, Date eventFinish,
			Integer userId, PermissionType permissionType) {
		setEvent(new Event());
		getEvent().setId(eventId);
		getEvent().setTitle(eventTitle);
		getEvent().setDescription(eventDescription);
		getEvent().setStart(eventStart);
		getEvent().setFinish(eventFinish);
		setUser(new User(userId));
		setPermissionType(permissionType);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Agenda other = (Agenda) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PermissionType getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(PermissionType permissionType) {
		this.permissionType = permissionType;
	}

}
