package controller.paciente;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import controller.medico.MedicoPacientesController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Paciente;
import model.Prontuario;
import screenManager.ScreenManager;

public class ProntuariosPacienteController implements Initializable {

	@FXML
	private ListView<Prontuario> lvProntuarios;

	@FXML
	private Button btnVoltar;
	private ObservableList<Prontuario> obsProntuarios;

	private Paciente pacienteSelecionado = MedicoPacientesController.getPacienteSelecionado();

	@FXML
	void closeScreen(ActionEvent event) {
		
		ScreenManager.closeScreen(btnVoltar);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadProntuarios();

	}

	private void loadProntuarios() {

		List<Prontuario> prontuarios = pacienteSelecionado.getProntuarios();

		obsProntuarios = FXCollections.observableArrayList(prontuarios);

		lvProntuarios.setItems(obsProntuarios);

	}

}
