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

package date;

/**
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class MyDateTime {

	/**
	 * Compara duas <b>datas<b>.
	 * @param time1 primeira data-hora a ser comparada.
	 * @param time1 segunda data-hora a ser comparada
	 * @return inteiro positivo se a primeira <b>data</b> é <b>maior<b> que a segunda, inteiro negativo
	 * se a primeira <b>data-hora</b>é<b>menor</b> que a primeira, ou zero se as duas <b>datas-horas<b> forem <b>iguais</b>.
	 */
	public static int compareTo(String time1, String time2) {

		int horaTime1 = parseHora(time1);
		int horaTime2 = parseHora(time2);

		if (horaTime1 == horaTime2)

			return compareMinutos(time1, time2);

		else
			return compareHora(time1, time2);

	}

	/**
	 * Compara os minutos de uma data-hora
	 * @param time1
	 * @param time2
	 * @return int
	 */
	private static int compareMinutos(String time1, String time2) {

		int minutoTime1 = parseMinutos(time1);
		int minutoTime2 = parseMinutos(time2);

		if (minutoTime1 < minutoTime2)

			return -1;

		else if (minutoTime1 > minutoTime2)

			return 1;

		return 0;
	}

	/**
	 * Converte uma String em um int com os minutos relativos
	 * @param time
	 * @return int
	 */
	private static int parseMinutos(String time) {

		if (time.length() == 5)
			return Integer.parseInt(time.substring(3));

		try {

			if (parseHora(time) < 10)
				return Integer.parseInt(time.substring(2));

			return Integer.parseInt(time.substring(3));

		} catch (NumberFormatException e) {

			return Integer.parseInt(time.substring(2));
		}
	}

	/**
	 * Compara as horas de uma data-hora
	 * @param time1
	 * @param time2
	 * @return int
	 */
	private static int compareHora(String time1, String time2) {

		int horaTime1 = parseHora(time1);
		int horaTime2 = parseHora(time2);

		if (horaTime1 < horaTime2)

			return -1;

		else if (horaTime1 > horaTime2)

			return 1;

		return 0;
	}

	/**
	 * Converte uma String em um int com as horas relativos
	 * @param time
	 * @return int
	 */
	private static int parseHora(String time) {

		try {

			return Integer.parseInt(time.substring(0, 2));

		} catch (NumberFormatException e) {

			return Integer.parseInt(time.substring(0, 1));
		}

	}
}
