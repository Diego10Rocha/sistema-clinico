/*******************************************************************************
Autor: Diego Cerqueira e Joanderson Santos
Componente Curricular: MI Programa��o
Concluido em: 18/10/2021
Declaro que este c�digo foi elaborado por Diego Cerqueira e Joanderson Santos em dupla e n�o cont�m nenhum
trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo
de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte
do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
******************************************************************************************/

package date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * A Classe <b>MyDate</b> permite atrav�s de uma inst�ncia a obten��o da <b>data<b> atual do Sistema de 
 * forma simples. Al�m de prover m�todos para compara��o de <b>datas</b>. 
 * referente � GUI do <b>Gerenciador de Tarefas</b>.
 * @since 2021
 * @author Joanderson Santos e Diego Cerqueira
 */

public class MyDate {
	
	private Date date;
	private SimpleDateFormat dateFormat;
	
	/**
	 * Construtor padr�o da classe <b>MyDate</b>.<br>
     * 
	 */
	
	public MyDate() {
		
		this.date = new Date();
		this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	/**
	 * Obt�m a <b>data</b> atual do Sistema.
	 * @return <b>data</b> atual do Sistema no formato:<b>dd/MM/yyyy<b>
	 */
	
	public String getCurrentDate(){
		
		return this.dateFormat.format(date);
	}
	
	/**
	 * Obt�m o <b>dia</b> atual do Sistema.
	 * @return <b>dia</b> atual do Sistema.
	 */
	
	
	public String getCurrentDay(){
		
		SimpleDateFormat temp  = new SimpleDateFormat("dd");
		
		return temp.format(date);
	}
	
	/**
	 * Obt�m o <b>m�s</b> atual do Sistema.
	 * @return <b>m�s</b> atual do Sistema.
	 */
	
	
	public String getCurrentMonth(){
		
		SimpleDateFormat temp  = new SimpleDateFormat("MM");
		
		return temp.format(date);
	}
	
	/**
	 * Obt�m o <b>ano</b> atual do Sistema.
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
	 * @return inteiro positivo se a primeira <b>data</b> � <b>maior<b> que a segunda, inteiro negativo
	 * se a primeira <b>data</b> � <b>menor</b> que a primeira, ou zero se as duas <b>datas<b> forem <b>iguais</b>.
	 */
	
	public int compareTo(String date1, String date2) {
		
		LocalDate localDate1 = getLocalDate(date1);
		LocalDate localDate2 = getLocalDate(date2);

		int valComparete = localDate1.compareTo(localDate2);
		
		return valComparete;
	}
	
	/**
	 * Obt�m um objeto do tipo <b>LocalDate</b> dada uma <b>data<b> do tipo String, no formato:<b>dd/MM/yyyy<b>.
	 * @param date data utilizada como refer�ncia.
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
