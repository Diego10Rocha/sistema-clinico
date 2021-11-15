package resultLoginTry;

public enum ResultLoginTry {

	SUCCESS("SUCCESS"), FAIL_CPF("FAIL_CPF"), FAIL_PASSWORD("FAIL_PASSWORD");

	private String value;

	private ResultLoginTry(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
