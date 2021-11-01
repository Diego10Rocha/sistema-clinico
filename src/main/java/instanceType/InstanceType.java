package instanceType;

public enum InstanceType {
	
	MEDICO('M'),
	PACIENTE('P'),
	RECEPCIONISTA('R');
	
	private char value;
	
	private InstanceType(char value) {
		
		this.value = value;
	}

	public char getValue() {
		return value;
	}

	
	
}
