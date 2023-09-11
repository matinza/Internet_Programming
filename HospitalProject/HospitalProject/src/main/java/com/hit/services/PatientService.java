package com.hit.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hit.algorithm.IAlgoSearch;
import com.hit.dao.IDao;
import com.hit.dm.Patient;

public class PatientService {
	IAlgoSearch algoSearch;
	IDao patientFileDao;

	public PatientService(IAlgoSearch algoSearch, IDao patientFileDao) {
		this.algoSearch = algoSearch;
		this.patientFileDao = patientFileDao;
	}

	public Patient GetPatient(long id) {
		return this.patientFileDao.GetPatient(id);
	}

	public void RemovePatient(long id) {
		this.patientFileDao.DeletePatient(id);
	}

	public void SavePatient(Patient patient) throws IOException {
		this.patientFileDao.SavePatient(patient);
	}

	public List<Patient> SearchPatients(Long patternId) {
		List<Patient> patients = new ArrayList<Patient>();

		for (int i = 0; i < this.patientFileDao.GetAllPatients().size(); i++) {
			if (!algoSearch.IndexArrayOfPatternInText(patternId.toString(),
					this.patientFileDao.GetAllPatients().get(i).getId().toString()).isEmpty()) {
				patients.add(this.patientFileDao.GetAllPatients().get(i));
			}
		}

		return patients;
	}
}
