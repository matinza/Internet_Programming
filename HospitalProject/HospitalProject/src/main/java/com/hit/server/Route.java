package com.hit.server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.hit.algorithm.KmpSearchAlgorithm;
import com.hit.dao.PatientFileDao;
import com.hit.dm.Patient;
import com.hit.services.PatientService;

public class Route implements Runnable {

	Socket someClient;
	Gson gson = new Gson();

	public Route(Socket s) {
		someClient = s;
	}

	@Override
	public void run() {

		while (true) {
			try {
				Scanner reader = new Scanner(new InputStreamReader(someClient.getInputStream()));
				String requestStr = reader.nextLine();
				Request request = gson.fromJson(requestStr, Request.class);

				ObjectOutputStream writer = new ObjectOutputStream(someClient.getOutputStream());

				var patientService = new PatientService(new KmpSearchAlgorithm(), new PatientFileDao());

				String responseJson = "";

				switch (request.getAction()) {
				case "Add_Patient":
					List<Patient> indexes = patientService.SearchPatients(request.getData().getId());
					if (indexes.size() == 0) {
						patientService.SavePatient(new Patient(request.getData().getName(), request.getData().getId()));
						responseJson = gson.toJson(new Request(request.getAction(),
								new Patient(request.getData().getName(), request.getData().getId())));
					} else {
						responseJson = gson.toJson(new Request(request.getAction(), new Patient(null, null)));
					}

					writer.writeObject(responseJson);
					writer.flush();
					break;
				case "Search_Patient":
					List<Patient> indexes1 = patientService.SearchPatients(request.getData().getId());
					responseJson = gson
							.toJson(new Request(request.getAction(), indexes1.size() == 0 ? new Patient(null, null)
									: new Patient(indexes1.get(0).getName(), indexes1.get(0).getId())));
					writer.writeObject(responseJson);
					writer.flush();
					break;
				case "Delete_Patient":
					List<Patient> indexes2 = patientService.SearchPatients(request.getData().getId());
					patientService.RemovePatient(request.getData().getId());
					responseJson = gson
							.toJson(new Request(request.getAction(), indexes2.size() == 0 ? new Patient(null, null)
									: new Patient(indexes2.get(0).getName(), indexes2.get(0).getId())));
					writer.writeObject(responseJson);
					writer.flush();
					break;
				default:
					break;
				}
				someClient.close();
			} catch (IOException e) {
//				e.printStackTrace();
			}
		}

	}

}
