/*******************************************************************************
Autor: Diego Cerqueira e Joanderson Santos
Componente Curricular: MI Programação
Concluido em: 07/12/2021
Declaro que este código foi elaborado por Diego Cerqueira e Joanderson Santos em dupla e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package instanceType;

/**
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public enum InstanceType {
	
	MEDICO(0),
	PACIENTE(1),
	RECEPCIONISTA(2),
	ESPECIALIDADE(3),
	PRONTUARIO(4),
	CONSULTA(5),
	AGENDA_CONSULTA(6);
	
	private int value;
	
	private InstanceType(int value) {
		
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	
	
}
