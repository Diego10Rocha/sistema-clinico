package controller.consulta;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import dao.AgendaConsultaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import login.Login;
import model.AgendaConsulta;
import screenManager.ScreenManager;

public class ConsultaMedicoController implements Initializable {

	@FXML
    private ListView<AgendaConsulta> lvConsultas;

    @FXML
    private Button btnAtenderPaciente;

    @FXML
    private Button btnVoltar;
    
    private ObservableList<AgendaConsulta> obsConsultas;
    
    ScreenManager screenManager = new ScreenManager();

    @FXML
    void openScreenAtendimentoPaciente(ActionEvent event) {
    	try {
			screenManager.openNewScreen("medico/AtendimentoMedicoScreen", "Atendimento");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@FXML
	void closeScreen(ActionEvent event) {

		ScreenManager.closeScreen(btnVoltar);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadConsultas();

	}

	private void loadConsultas() {

		List<AgendaConsulta> agendasConsultaCadastradas = AgendaConsultaDAO.findByCPF_Medico(Login.getCPF_userLogged());
		Collections.sort(agendasConsultaCadastradas);
		obsConsultas = FXCollections.observableArrayList(agendasConsultaCadastradas);
		
		lvConsultas.setItems(obsConsultas);

	}
	
}
