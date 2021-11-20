package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.EspecialidadeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Especialidade;

public class EspecialidadeController implements Initializable {

	@FXML
	private ListView<Especialidade> lvEspecialidades;

	@FXML
	private Button btnAdicionarEspecialidade;

	@FXML
	private Button btnRemoverEspecialidade;

	@FXML
	private Button btnEditarEspecialidade;

	@FXML
	private Button btnVoltar;

	private ObservableList<Especialidade> obsEspecialidades;

	public void loadEspecialidades() {

		List<Especialidade> especialidadesCadastradas = EspecialidadeDAO.getSpecialties();

		obsEspecialidades = FXCollections.observableArrayList(especialidadesCadastradas);

		lvEspecialidades.setItems(obsEspecialidades);
	}

	@FXML
	void closeScreen(ActionEvent event) {

	}

	@FXML
	void openScreenFormularioEditEspecialidade(ActionEvent event) {

	}

	@FXML
	void openScreenFormularioEspecialidade(ActionEvent event) {

	}

	@FXML
	void removerEspecialidade(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadEspecialidades();

	}
}
