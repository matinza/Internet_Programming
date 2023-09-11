package com.hit.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.hit.communication.Communication;
import com.hit.dm.Patient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class DeleteSceneController implements Initializable {

	private Communication com;

	@FXML
	private TextField id_deleteID_textField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		com = new Communication("localhost", 34567);
	}

	@FXML
	public void handleDelete_Click(ActionEvent e) {
		Patient patient = new Patient("", Long.parseLong(id_deleteID_textField.getText()));
		Patient answer = com.SendToServerAndRecieve("Delete_Patient", patient);
		Alert alert;
		if (answer.getName() == null && answer.getId() == null) {
			alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("PATIENT DOES NOT EXIST");
			alert.setContentText("Patient does not exist!");
			alert.setHeaderText("Warning!");
			alert.showAndWait();
		} else {
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Deleted patient");
			alert.setContentText("Patient Name: " + answer.getName() + "\nPatient ID: " + answer.getId()
					+ "\n\nDeleted successfully!");
			alert.setHeaderText("Success!");
			alert.showAndWait();
		}

		id_deleteID_textField.clear();

	}
}
