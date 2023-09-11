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

public class AddSceneController implements Initializable {

	private Communication com;

	@FXML
	private TextField id_add_name_textField;

	@FXML
	private TextField id_add_id_textField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		com = new Communication("localhost", 34567);
	}

	@FXML
	public void handleAddPatient_click(ActionEvent e) {
		Patient patient = new Patient(id_add_name_textField.getText(), Long.parseLong(id_add_id_textField.getText()));
		Patient answer = com.SendToServerAndRecieve("Add_Patient", patient);
		Alert alert;

		if (answer.getName() == null && answer.getId() == null) {
			alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("PATIENT ALREADY EXIST");
			alert.setContentText("Patient already exist, you can delete it and add it again :)");
			alert.setHeaderText("Failure!");
			alert.showAndWait();
		} else {
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("ADDED PATIENT");
			alert.setContentText("Patient Name: " + answer.getName() + "\nPatient ID: " + answer.getId()
					+ "\n\nAdded successfully!");
			alert.setHeaderText("Success!");
			alert.showAndWait();
		}

		id_add_name_textField.clear();
		id_add_id_textField.clear();
	}
}
