package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.PacienteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Paciente;

public class PacienteRecepcionistaController implements Initializable{

	@FXML
	private ListView<Paciente> lvPacientes;

	@FXML
	private Button btnAdicionarPaciente;

	@FXML
	private Button btnRemoverPaciente;

	@FXML
	private Button btnEditarPaciente;

	@FXML
	private Button btnVoltar;

	private ObservableList<Paciente> obsPacientes;

	public void loadPacientes() {

		List<Paciente> pacientesCadastrados = PacienteDAO.getPatients();

		obsPacientes = FXCollections.observableArrayList(pacientesCadastrados);

		lvPacientes.setItems(obsPacientes);
	}

	@FXML
	void closeScreen(ActionEvent event) {

	}

	@FXML
	void openScreenFormularioEditPaciente(ActionEvent event) {

	}

	@FXML
	void openScreenFormularioPaciente(ActionEvent event) {

	}

	@FXML
	void removerPaciente(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		loadPacientes();
		
	}
}
