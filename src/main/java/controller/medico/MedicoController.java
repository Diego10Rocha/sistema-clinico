package controller.medico;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import screenManager.ScreenManager;

public class MedicoController {

    @FXML
    private Button btnConsulta;

    @FXML
    private Button btnAtenderPaciente;
    
    @FXML
    private Button btnSair;
    
    ScreenManager screenManager = new ScreenManager();

    @FXML
    void exit(ActionEvent event) {
    	ScreenManager.closeScreen(btnSair);
    }

    @FXML
    void openScreenAtendimento(ActionEvent event) {
    	try {
			screenManager.openNewScreen("medico/AtendimentoMedicoScreen", "Atendimento");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void openScreenConsultas(ActionEvent event) {
    	try {
			screenManager.openNewScreen("consulta/ConsultaMedicoScreen", "Atendimento");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
