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

package screenManager;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A Classe <b>ScreenManager</b> permite atrav�s de uma inst�ncia o
 * gerenciamento de <b>telas</b>.
 * 
 * @since 2021
 * @author Joanderson Santos e Dicego Cerqueira
 */
public class ScreenManager {

	private Object currentController;

	/**
	 * Abri um nova <b>tela</b>.
	 * 
	 * @param path  caminho referente � <b>tela</b>.
	 * @param title t�tulo da tela.
	 * @throws IOException caso o caminho esteja errado, ou a tela n�o exista.
	 */

	public void openNewScreen(String path, String title) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader();

		Pane p = fxmlLoader.load(getClass().getResource("/view/" + path + ".fxml").openStream());

		Object controller = fxmlLoader.getController();

		Stage stage = new Stage();

		stage.setScene(new Scene(p));
		stage.setTitle(title);
		stage.setResizable(true);
		stage.setMinHeight(400);
		stage.setMinWidth(600);
		stage.initModality(Modality.APPLICATION_MODAL);

		Image image = new Image("https://cdn.pixabay.com/photo/2017/05/27/22/25/cruz-2349636_960_720.png");

		stage.getIcons().add(image);

		stage.show();

		this.currentController = controller;

	}

	public void openNewScreen(String path, String title, boolean isResizable) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader();

		Pane p = fxmlLoader.load(getClass().getResource("/view/" + path + ".fxml").openStream());

		Object controller = fxmlLoader.getController();

		Stage stage = new Stage();

		stage.setScene(new Scene(p));
		stage.setTitle(title);
		stage.setResizable(isResizable);
		stage.setMinHeight(400);
		stage.setMinWidth(600);
		stage.initModality(Modality.APPLICATION_MODAL);

		Image image = new Image("https://cdn.pixabay.com/photo/2017/05/27/22/25/cruz-2349636_960_720.png");

		stage.getIcons().add(image);

		stage.show();

		this.currentController = controller;

	}

	public void openNewScreen(String path, String title, boolean isResizable, boolean isMaximized) throws IOException {

		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/" + path + ".fxml"));
		Parent root = loader.load();

		Stage stage = new Stage();

		Scene scene = new Scene(root);

		stage.setTitle(title);
		stage.setMaximized(isMaximized);
		stage.setResizable(isResizable);
		stage.setMinHeight(400);
		stage.setMinWidth(600);

		Image image = new Image("https://cdn.pixabay.com/photo/2017/05/27/22/25/cruz-2349636_960_720.png");

		stage.getIcons().add(image);
		stage.setScene(scene);
		stage.show();

	}

	public static void closeScreen(Button buttonClose) {

		Stage stage = (Stage) buttonClose.getScene().getWindow();

		stage.close();
	}

	/**
	 * Obt�m o controlador referente � �ltima <b>tela</b> aberta.
	 * 
	 * @return controlador referente � �ltima <b>tela</b> aberta.
	 */

	public Object getCurrenController() {
		return this.currentController;
	}
}
