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

package factory;

import model.Especialidade;

/**
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class EspecialidadeFactory {

	/**
	 * Metodo de cadastro de especialidade e subespeecialidade
	 * @param nomeEspecialidadePrincipal
	 * @param nomeSubEspecialidade
	 */
	public static void createEspecialidade(String nomeEspecialidadePrincipal, String nomeSubEspecialidade) {

		new Especialidade(nomeEspecialidadePrincipal, true);

		new Especialidade(nomeSubEspecialidade, false);

	}

	/**
	 * Metodo de cadastro de especialidade
	 * @param nomeEspecialidade
	 * @param isPrincipal
	 */
	public static void createEspecialidade(String nomeEspecialidade, boolean isPrincipal) {

		new Especialidade(nomeEspecialidade, isPrincipal);
	}

}
