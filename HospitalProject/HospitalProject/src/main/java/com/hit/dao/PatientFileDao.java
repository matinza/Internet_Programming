package com.hit.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.Patient;

public class PatientFileDao implements IDao {

	String path = "./src/main/resources/patients.txt";

	@Override
	public Patient GetPatient(Long id) {
		Type patientsListType = new TypeToken<ArrayList<Patient>>() {
		}.getType();

		try {
			List<Patient> patients = new Gson().fromJson(new FileReader(path), patientsListType);
			if (patients == null) {
				return null;
			}

			for (Patient patientIterator : patients) {
				if (patientIterator.getId().equals(id)) {
					return patientIterator;
				}
			}
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public List<Patient> GetAllPatients() {
		Type patientsListType = new TypeToken<ArrayList<Patient>>() {
		}.getType();

		try {
			List<Patient> patients = new Gson().fromJson(new FileReader(path), patientsListType);
			if (patients == null) {
				return null;
			}
			return patients;
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean DeletePatient(Long id) {
		Gson gson = new Gson();
		Type patientsListType = new TypeToken<ArrayList<Patient>>() {
		}.getType();

		try {
			List<Patient> patients = new Gson().fromJson(new FileReader(path), patientsListType);
			if (patients == null) {
				return false;
			}

			Patient patientToRemove = null;
			for (Patient patientIterator : patients) {
				if (patientIterator.getId().equals(id)) {
					patientToRemove = patientIterator;
				}
			}

			patients.remove(patientToRemove);

			String patientsJson = gson.toJson(patients);
			try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
				out.write(patientsJson);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean SavePatient(Patient patient) {
		Gson gson = new Gson();
		Type patientsListType = new TypeToken<ArrayList<Patient>>() {
		}.getType();

		try {
			List<Patient> patients = new Gson().fromJson(new FileReader(path), patientsListType);
			if (patients == null) {
				patients = new ArrayList<Patient>();
			}

			// check if patient already exists
			for (Patient patientIterator : patients) {
				if (patientIterator.getId().equals(patient.getId())) {
					return false;
				}
			}

			// add new patient
			patients.add(patient);

			// write to json file
			String patientsJson = gson.toJson(patients);
			try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
				out.write(patientsJson);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
