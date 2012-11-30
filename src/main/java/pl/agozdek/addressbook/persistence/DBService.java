package pl.agozdek.addressbook.persistence;

import java.util.ArrayList;
import java.util.List;

import pl.agozdek.addressbook.domain.Person;

public class DBService {
	
	private List<Person> people = new ArrayList<Person>();
	
	public List<Person> getAllPeople(){
		return people;
	}
	
	public void addPerson(Person person){
		people.add(person);
	}	
}
