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
 * A Classe <b>ScreenManager</b> permite através de uma instância o
 * gerenciamento de <b>telas</b>.
 * 
 * @since 2021
 * @author Joanderson Santos e Diego Cerqueira
 */
public class ScreenManager {

	private Object currentController;

	/**
	 * Abri uma nova <b>tela</b>.
	 * 
	 * @param path  caminho referente à <b>tela</b>.
	 * @param title título da tela.
	 * @throws IOException caso o caminho esteja errado, ou a tela não exista.
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

		Image applicationIcon = new Image(getClass().getResourceAsStream("/image/icon.png"));

		stage.getIcons().add(applicationIcon);

		stage.show();

		this.currentController = controller;

	}

	/**
	 * Abri uma nova <b>tela</b>.
	 * 
	 * @param path  caminho referente à <b>tela</b>.
	 * @param title título da tela.
	 * @throws IOException caso o caminho esteja errado, ou a tela não exista
	 * @param isResizable diz se a tela pode mudar de tamanho ou não
	 */
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

	/**
	 * Abri uma nova <b>tela</b>.
	 * 
	 * @param path  caminho referente à <b>tela</b>.
	 * @param title título da tela.
	 * @throws IOException caso o caminho esteja errado, ou a tela não exista.
	 * @param isResizable diz se a tela pode mudar de tamanho ou não
	 * @param isMaximized diz se a tela começa maximizada
	 */
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

		this.currentController = loader.<Object>getController();

	}

	public static void closeScreen(Button buttonClose) {

		Stage stage = (Stage) buttonClose.getScene().getWindow();

		stage.close();
	}

	/**
	 * Obtém o controlador referente à última <b>tela</b> aberta.
	 * 
	 * @return controlador referente à última <b>tela</b> aberta.
	 */

	public Object getCurrenController() {
		return this.currentController;
	}
}
