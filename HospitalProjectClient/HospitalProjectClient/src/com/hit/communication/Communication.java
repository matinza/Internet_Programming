package com.hit.communication;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hit.dm.Patient;

public class Communication {
	private static Socket myServer = null;
	private Gson gson = new Gson();

	public Communication(String user, int port) {
		setMyServer(user, port);
	}

	private void setMyServer(String user, int port) {
		if (myServer == null || myServer.isClosed()) {
			try {
				Communication.myServer = new Socket(user, port);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private Patient GetAnswerFromServer() throws IOException {
		try (Scanner reader = new Scanner(new InputStreamReader(myServer.getInputStream()))) {
			String requestStr = reader.nextLine();
			requestStr = requestStr.substring(requestStr.indexOf("{"));
			Request request = gson.fromJson(requestStr, Request.class);

			return request.getData();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return new Patient(null, null);
	}

	public Patient SendToServerAndRecieve(String action, Patient data) {
		if (myServer == null || myServer.isClosed()) {
			setMyServer("localhost", 34567);
		}
		Gson gson = new Gson();
		String requestJson = gson.toJson(new Request(action, data));
		PrintWriter writer;

		try {
			writer = new PrintWriter(new OutputStreamWriter(myServer.getOutputStream()));
			writer.println(requestJson);
			writer.flush();

			Patient recievedAnswerFromServer = GetAnswerFromServer();
			return recievedAnswerFromServer;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
