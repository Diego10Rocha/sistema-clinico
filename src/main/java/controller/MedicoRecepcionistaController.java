package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.MedicoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Medico;

public class MedicoRecepcionistaController implements Initializable{

	@FXML
	private ListView<Medico> lvMedicos;

	@FXML
	private Button btnAdicionarMedico;

	@FXML
	private Button btnRemoverMedico;

	@FXML
	private Button btnEditarMedico;

	@FXML
	private Button btnVoltar;

	private ObservableList<Medico> obsMedicos;

	public void loadMedicos() {

		List<Medico> medicoCadastrados = MedicoDAO.getDoctors();

		obsMedicos = FXCollections.observableArrayList(medicoCadastrados);

		lvMedicos.setItems(obsMedicos);
	}

	@FXML
	void closeScreen(ActionEvent event) {

	}

	@FXML
	void openScreenFormularioEditMedico(ActionEvent event) {

	}

	@FXML
	void openScreenFormularioMedico(ActionEvent event) {

	}

	@FXML
	void removerMedico(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		loadMedicos();
		
	}

}
