package entity;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

public enum PermissionType {

	READ("R"), WRITE("W");

	private final String value;

	PermissionType(String value) {
		this.value = value;
	}

	@JsonValue
	public String toString() {
		return this.value;
	}

	@JsonCreator
	public static PermissionType fromValue(String value) {
		PermissionType result = null;

		for (PermissionType permissionType : values()) {
			if (permissionType.toString().equalsIgnoreCase(value)) {
				result = permissionType;
				break;
			}
		}

		return result;
	}
}
