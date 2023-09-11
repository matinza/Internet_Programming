package com.hit.service;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import com.hit.algorithm.KmpSearchAlgorithm;
import com.hit.dao.PatientFileDao;
import com.hit.dm.Patient;
import com.hit.services.PatientService;

public class PatientServiceTest {

	@Test
	public void testAllClassesTogehter() throws IOException {
		var patientService = new PatientService(new KmpSearchAlgorithm(), new PatientFileDao());
		patientService.SavePatient(new Patient("Matan", (long) 123456789));
		patientService.SavePatient(new Patient("Tomer", (long) 987654321));
		patientService.SavePatient(new Patient("Avishay", (long) 1313123475));
		patientService.SavePatient(new Patient("Avraham", (long) 987987981));
		patientService.SavePatient(new Patient("Amit", (long) 123740812));
		patientService.SavePatient(new Patient("Avi", (long) 13740812));
		patientService.SavePatient(new Patient("Moran", (long) 13740123));
		patientService.SavePatient(new Patient("Ortal", (long) 13740123));
		patientService.SavePatient(new Patient("Shoval", (long) 13123123));

		Patient patient = patientService.GetPatient(123456789);
		Assert.assertEquals("Matan", patient.getName());
		
		patient = patientService.GetPatient(1313123475);
		Assert.assertEquals("Avishay", patient.getName());
		
		patientService.RemovePatient(1313123475);
		patientService.RemovePatient(123);
		patientService.RemovePatient(987987981);

		var patientsSearchTest = patientService.SearchPatients((long) 12345789);
		for (int i = 0; i < patientsSearchTest.size(); i++) {
			System.out.println(patientsSearchTest.get(i).getName() + "  " + patientsSearchTest.get(i).getId());
		}
		patientsSearchTest = patientService.SearchPatients((long) 987);
	}
}
