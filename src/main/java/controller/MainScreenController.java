/**
 * Sample Skeleton for 'MainScreen.fxml' Controller Class
 */

package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class MainScreenController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="menuArquivo"
    private Menu menuArquivo; // Value injected by FXMLLoader

    @FXML // fx:id="menuItemProjetos"
    private MenuItem menuItemProjetos; // Value injected by FXMLLoader

    @FXML // fx:id="menuSobre"
    private Menu menuSobre; // Value injected by FXMLLoader

    @FXML // fx:id="menuItemSistema"
    private MenuItem menuItemSistema; // Value injected by FXMLLoader

    @FXML
    void acessProjetos(ActionEvent event) {

    }

    @FXML
    void acessSobreSistema(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert menuArquivo != null : "fx:id=\"menuArquivo\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert menuItemProjetos != null : "fx:id=\"menuItemProjetos\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert menuSobre != null : "fx:id=\"menuSobre\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert menuItemSistema != null : "fx:id=\"menuItemSistema\" was not injected: check your FXML file 'MainScreen.fxml'.";

    }
}
