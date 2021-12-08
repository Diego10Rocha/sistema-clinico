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

package resultLoginTry;

/**
 * Enum do resultado de uma tentativa de login
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
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
