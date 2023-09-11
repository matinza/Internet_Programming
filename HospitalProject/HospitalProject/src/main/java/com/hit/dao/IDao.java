package com.hit.dao;
import java.io.IOException;
import java.util.List;

import com.hit.dm.Patient;

public interface IDao {
	public Patient GetPatient(Long id);
	public List<Patient> GetAllPatients();
	public Boolean DeletePatient(Long id);
	public Boolean SavePatient(Patient patient) throws IOException;
}
