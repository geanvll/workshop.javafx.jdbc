package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {
	
	public static Stage currentStage(ActionEvent event) {//para acessar o stage onde o controler que recebeu o evento está
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

}
