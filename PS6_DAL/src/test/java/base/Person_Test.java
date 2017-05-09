package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;


public class Person_Test {
	
	static PersonDomainModel per1 = new PersonDomainModel();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		per1.setBirthday(new Date(0));
		per1.setCity("Bear");
		per1.setFirstName("David");
		per1.setLastName("Olaoye");
		per1.setPostalCode(19701);
		per1.setStreet("22 Grand Teton Drive");
		PersonDAL.addPerson(per1);
		PersonDomainModel per2 = PersonDAL.getPerson(per1.getPersonID());
		assertNotNull(per2);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		PersonDAL.deletePerson(per1.getPersonID());
	}

	@Test
	public void TestGetAllPersons(){
		ArrayList<PersonDomainModel> person = PersonDAL.getPersons();
		assertNotNull(person);
	}
	
	@Test
	public void AddPersonTest(){
		PersonDAL.addPerson(per1);
		assertNotNull(PersonDAL.getPerson(per1.getPersonID()));
	}
	
	@Test
	public void GetPersonTest(){
		PersonDomainModel per2 = PersonDAL.getPerson(per1.getPersonID());
		assertEquals(per1.getFirstName(), per2.getFirstName());
	}
	
	@Test
	public void UpdatePersonTest(){
		PersonDomainModel per2 = PersonDAL.getPerson(per1.getPersonID());
		assertEquals(per1.getPersonID(), per2.getPersonID());
		per2.setLastName("Ade");
		PersonDAL.updatePerson(per2);
		PersonDomainModel per3 = PersonDAL.getPerson(per1.getPersonID());
		assertEquals(per2.getLastName(), per3.getLastName());
		assertNotEquals(per1.getLastName(), per2.getLastName());
	}
	
	@Test
	public void deletePersontest(){
		PersonDAL.deletePerson(per1.getPersonID());
		PersonDomainModel per4 = PersonDAL.getPerson(per1.getPersonID());
		assertNull(per4);
	}

	
}