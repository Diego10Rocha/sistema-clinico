package instanceType;

public enum InstanceType {
	
	MEDICO(0),
	PACIENTE(1),
	RECEPCIONISTA(2),
	ESPECIALIDADE(3),
	PRONTUARIO(4)
	;
	
	private int value;
	
	private InstanceType(int value) {
		
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	
	
}
