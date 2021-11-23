/*******************************************************************************
Autor: Diego Cerqueira e Joanderson Santos
Componente Curricular: MI Programação
Concluido em: 18/10/2021
Declaro que este código foi elaborado por Diego Cerqueira e Joanderson Santos em dupla e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * A Classe <b>MyDate</b> permite através de uma instância a obtenção da <b>data<b> atual do Sistema de 
 * forma simples. Além de prover métodos para comparação de <b>datas</b>. 
 * referente à GUI do <b>Gerenciador de Tarefas</b>.
 * @since 2021
 * @author Joanderson Santos e Diego Cerqueira
 */

public class MyDate {
	
	private Date date;
	private SimpleDateFormat dateFormat;
	
	/**
	 * Construtor padrão da classe <b>MyDate</b>.<br>
     * 
	 */
	
	public MyDate() {
		
		this.date = new Date();
		this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	/**
	 * Obtém a <b>data</b> atual do Sistema.
	 * @return <b>data</b> atual do Sistema no formato:<b>dd/MM/yyyy<b>
	 */
	
	public String getCurrentDate(){
		
		return this.dateFormat.format(date);
	}
	
	/**
	 * Obtém o <b>dia</b> atual do Sistema.
	 * @return <b>dia</b> atual do Sistema.
	 */
	
	
	public String getCurrentDay(){
		
		SimpleDateFormat temp  = new SimpleDateFormat("dd");
		
		return temp.format(date);
	}
	
	/**
	 * Obtém o <b>mês</b> atual do Sistema.
	 * @return <b>mês</b> atual do Sistema.
	 */
	
	
	public String getCurrentMonth(){
		
		SimpleDateFormat temp  = new SimpleDateFormat("MM");
		
		return temp.format(date);
	}
	
	/**
	 * Obtém o <b>ano</b> atual do Sistema.
	 * @return <b>ano</b> atual do Sistema.
	 */
	
	public String getCurrentYear(){
		
		SimpleDateFormat temp  = new SimpleDateFormat("yyyy");
		
		return temp.format(date);
	}
	
	/**
	 * Compara duas <b>datas<b>.
	 * @param date1 primeira data a ser comparada.
	 * @param date2 segunda data a ser comparada
	 * @return inteiro positivo se a primeira <b>data</b> é <b>maior<b> que a segunda, inteiro negativo
	 * se a primeira <b>data</b> é <b>menor</b> que a primeira, ou zero se as duas <b>datas<b> forem <b>iguais</b>.
	 */
	
	public int compareTo(String date1, String date2) {
		
		LocalDate localDate1 = getLocalDate(date1);
		LocalDate localDate2 = getLocalDate(date2);

		int valComparete = localDate1.compareTo(localDate2);
		
		return valComparete;
	}
	
	/**
	 * Obtém um objeto do tipo <b>LocalDate</b> dada uma <b>data<b> do tipo String, no formato:<b>dd/MM/yyyy<b>.
	 * @param date data utilizada como referência.
	 * @return objeto do tipo <b>LocalDate</b>.
	 */
	
	private LocalDate getLocalDate(String date) {
		
		LocalDate localDate;
		
		//Format date day/dayOfMonth/year  example: 12/08/2021
		
		int year = Integer.parseInt(date.substring(6, 10));
		int month = Integer.parseInt(date.substring(3, 5));
		int dayOfMonth = Integer.parseInt(date.substring(0, 2));
		
		localDate = LocalDate.of(year, month, dayOfMonth);
		
		return localDate;
	}
}
