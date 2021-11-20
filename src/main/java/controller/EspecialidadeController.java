package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.EspecialidadeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Especialidade;
import screenManager.ScreenManager;

public class EspecialidadeController implements Initializable, EventHandler<ActionEvent> {

	@FXML
	private ListView<Especialidade> lvEspecialidades;

	@FXML
	private Button btnAdicionarEspecialidade;

	@FXML
	private Button btnRemoverEspecialidade;

	@FXML
	private Button btnEditarEspecialidade;

	private ScreenManager screenManager = new ScreenManager();

	private FormularioCadastroEspecialidadeController formularioEspecialidade;

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
	void openScreenFormularioEspecialidade(ActionEvent event) throws IOException {

		screenManager.openNewScreen("FormularioCadastroEspecialidade", "Cadastro Especialidade");

		setReferenciaFormularioCadastroEspecialidadeController();

	}

	private void setReferenciaFormularioCadastroEspecialidadeController() {

		Object currentController = screenManager.getCurrenController();

		formularioEspecialidade = (FormularioCadastroEspecialidadeController) currentController;

		formularioEspecialidade.addButtonsListener(this);

	}

	@FXML
	void removerEspecialidade(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		formularioEspecialidade = new FormularioCadastroEspecialidadeController();

		loadEspecialidades();

	}

	@Override
	public void handle(ActionEvent event) {
		
		if(event.getSource() == formularioEspecialidade.getBtnCadastrar()) {
			
			formularioEspecialidade.cadastrarEspecialidade();
			
			loadEspecialidades();
			
		}
		
		else if(event.getSource() == formularioEspecialidade.getBtnCancelar()) {
			
			formularioEspecialidade.closeScreen();;
		}
		
	}
}
