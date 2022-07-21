package sg.edu.nus.iss.workshoprevision.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.nus.iss.workshoprevision.models.Person;
import sg.edu.nus.iss.workshoprevision.models.PersonForm;
import sg.edu.nus.iss.workshoprevision.services.PersonService;

@Controller
public class PersonController {
    private List<Person> personList = new ArrayList<Person>();

    @Autowired
    PersonService perSvc;

    // retrieve from application.properties
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMsg;

    @RequestMapping(value = {"/", "/home", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message); // inject from welcome.message
        return "index";
    }

    @RequestMapping(value = "/testRetrieve", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Person> getAllPersons() {
        personList = perSvc.getPersons();

        return personList;
    }

    @RequestMapping(value="/personList", method = RequestMethod.GET)
    public String personList(Model model) {
        personList = perSvc.getPersons();
        model.addAttribute("persons", personList);
        return "personList"; // case sensitive to html file
    }

    @RequestMapping(value="/addPerson" , method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {
        PersonForm pForm = new PersonForm();
        model.addAttribute("personForm", pForm);
        return "addPerson";
    }

    @RequestMapping(value="/addPerson" , method =RequestMethod.POST)
    public String savePerson(Model model, @ModelAttribute("personForm") PersonForm personForm){
        
        String fname = personForm.getFirstName();
        String lname = personForm.getLastName();

        if(fname != null && fname.length() > 0 && lname != null && lname.length() > 0) {
            Person newPerson = new Person(fname, lname);
            perSvc.addPersons(newPerson);

            return "redirect:/personList"; // returns personList after loop
        }
      
        model.addAttribute("errorMsg", errorMsg);
        return "addPerson";

    }
}
