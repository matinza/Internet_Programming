package com.hit.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class SideBarController implements Initializable {

	@FXML
	private BorderPane bp;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadPage("MainScene");
	}

	@FXML
	public void handleHomeButtonClicked(ActionEvent e) {
		loadPage("MainScene");
	}

	@FXML
	private void handleADDButtonClicked(ActionEvent e) {
		loadPage("AddPatientScene");
	}

	@FXML
	private void handleSEARCHButtonClicked(ActionEvent e) {
		loadPage("SearchPatientScene");
	}

	@FXML
	private void handleDELETEButtonClicked(ActionEvent e) {
		loadPage("DeletePatientScene");
	}

	private void loadPage(String page) {
		Parent root = null;

		try {
			root = FXMLLoader.load(getClass().getResource("../scenes/" + page + ".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		bp.setCenter(root);
	}

}
