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

package screenManager;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A Classe <b>ScreenManager</b> permite através de uma instância o gerenciamento de <b>telas</b>.
 * @since 2021
 * @author Joanderson Santos e Dicego Cerqueira
 */
public class ScreenManager {
	
	private Object currentController;
	
	
	/**
	 * Abri um nova <b>tela</b>.
	 * @param path caminho referente à <b>tela</b>.
	 * @param title título da tela.
	 * @throws IOException caso o caminho esteja errado, ou a tela não exista.
	 */
	
	public void openNewScreen(String path, String title) throws IOException{
                
        FXMLLoader fxmlLoader = new FXMLLoader();
        
        Pane p = fxmlLoader.load(getClass().getResource("/view/"+path+".fxml").openStream());
        
        Object controller =  fxmlLoader.getController();
        
        Stage stage = new Stage();
    
        stage.setScene(new Scene(p));
        stage.setTitle(title);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        
        this.currentController = controller;
        
    }
	
	/**
	 * Obtém o controlador  referente à última <b>tela</b> aberta.
	 * @return controlador referente à última <b>tela</b> aberta.
	 */
	
	public Object getCurrenController() { return this.currentController; }
}
