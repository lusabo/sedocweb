package entity;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

public enum Permission {

	READ("R"), WRITE("W");

	private final String value;

	Permission(String value) {
		this.value = value;
	}

	@JsonValue
	public String toString() {
		return this.value;
	}

	@JsonCreator
	public static Permission fromValue(String value) {
		Permission result = null;

		for (Permission permission : values()) {
			if (permission.toString().equalsIgnoreCase(value)) {
				result = permission;
				break;
			}
		}

		return result;
	}
}
