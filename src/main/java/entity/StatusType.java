package entity;

import org.codehaus.jackson.annotate.JsonValue;

public enum StatusType {

	ACTIVE("active"), INACTIVE("inactive");

	private final String value;

	StatusType(String value) {
		this.value = value;
	}

	@JsonValue
	public String toString() {
		return this.value;
	}

}
