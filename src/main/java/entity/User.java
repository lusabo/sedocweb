package entity;

import static javax.persistence.GenerationType.SEQUENCE;
import static util.Constants.ABBREVIATION_SIZE;
import static util.Constants.HASH_SIZE;
import static util.Constants.NAME_SIZE;

import java.io.Serializable;
import java.security.Principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.NotBlank;

import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.util.Beans;

@Entity
@Table(name = "USER_ACCOUNT", uniqueConstraints = { @UniqueConstraint(name = "UK_USER_USERNAME", columnNames = { "USER_NAME" }) })
public class User implements Principal, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "SEQ_USER")
	@SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER", allocationSize = 1)
	@Column(name = "ID")
	private Integer id;

	@NotBlank
	@Size(max = ABBREVIATION_SIZE)
	@Column(name = "USER_NAME")
	@Index(name = "IDX_USER_USERNAME")
	private String username;

	@NotBlank
	@Size(max = HASH_SIZE)
	@Column(name = "PASSWORD")
	private String password;

	@NotBlank
	@Size(max = NAME_SIZE)
	@Column(name = "NAME")
	@Index(name = "IDX_USER_NAME")
	private String name;

	@NotBlank
	@Size(max = ABBREVIATION_SIZE)
	@Column(name = "INITIALS")
	@Index(name = "IDX_USER_INITIALS")
	private String initials;

	@Column(name = "ACTIVE", nullable = false)
	@Index(name = "IDX_USER_ACTIVE")
	private boolean active = true;

	@JsonIgnore
	@Size(max = HASH_SIZE)
	@Column(name = "ACTIVATION_TOKEN")
	private String activationToken;

	/*
	 * Adicionar coluna de setor
	 */

	public User() {
	}
	
	public User(Integer id){
		setId(id);
	}

	public User(Integer id, String username, String password, String name, String initials, boolean active,
			String activationToken) {
		setId(id);
		setUsername(username);
		setPassword(password);
		setName(name);
		setInitials(initials);
		setActive(active);
		setActivationToken(activationToken);
	}

	public static User getLoggedIn() {
		return (User) Beans.getReference(SecurityContext.class).getUser();
	}

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
		User other = (User) obj;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getActivationToken() {
		return activationToken;
	}

	public void setActivationToken(String activationToken) {
		this.activationToken = activationToken;
	}

}
