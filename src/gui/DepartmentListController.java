package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable{
	
	private DepartmentService service;
	
	//criamos as referências para os 4 componentes(botão, tableview, tableviewid e tableviewname)
	
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	private ObservableList<Department> obsList;
	
	@FXML
	public void onBtNewAction() {
		System.out.println("onBtNewAction");
	}
	
	public void setDeparmentService(DepartmentService service) {
		this.service = service;
	}
	
	//o simples fato de declarar as colunas não faz as tabelas funcionarem
	//temos que fazer o seguinte macete.

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();//criamos um método auxiliar, para iniciar algum componente na tela
	}

	private void initializeNodes() {
		//comandos para iniciar apropriadamente o comportamento das colunas da minha tabela
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		//macete para o tableview acompanhar a largura e a altura da janela
		Stage stage = (Stage) Main.getMainScene().getWindow();//pegamos uma referencia para o stage atual
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() { //esse método vai ser responsável por acessar o serviço, carregar os departamentos,
		//e jogar os departamentos na observablelist.
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);//isso instancia o observablelist, pegando os dados originais
		//da minha list
		tableViewDepartment.setItems(obsList);
		
	}

}
