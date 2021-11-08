package resultLoginTry;

public enum ResultLoginTry {

	SUCCESS(1), FAIL_CPF(2), FAIL_PASSWORD(3);

	private int value;

	private ResultLoginTry(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}
