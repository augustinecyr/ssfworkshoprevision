package sg.edu.nus.iss.workshoprevision.services;

import java.util.ArrayList;
import java.util.List;



import org.springframework.stereotype.Service;

import sg.edu.nus.iss.workshoprevision.models.Person;

@Service // annotation 
public class PersonService {
    
    private List<Person> persons = new ArrayList<Person>();

    // constructor
    public PersonService() {
        persons.add(new Person("Mark", "Zuckerberg"));
        persons.add(new Person("Bill", "Gates"));

    }
    // function 1
    public List<Person> getPersons(){
        return this.persons;
    }
    // function 2
    public void addPersons(Person p){
        persons.add(new Person(p.getFirstName(), p.getLastName()));
    }
}
