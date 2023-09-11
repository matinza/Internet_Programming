package com.hit.server;

import com.hit.dm.Patient;

public class Request {
	private String action;
	private Patient data;

	public Request(String action, Patient data) {
		this.action = action;
		this.data = data;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Patient getData() {
		return data;
	}

	public void setData(Patient data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Request [action=" + action + ", data=" + data + "]";
	}
}
