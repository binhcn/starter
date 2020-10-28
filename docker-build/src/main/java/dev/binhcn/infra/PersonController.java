package dev.binhcn.infra;

import dev.binhcn.domain.Person;
import dev.binhcn.domain.PersonService;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping
  public ResponseEntity<Collection<Person>> getPersons() {
    Collection<Person> persons = personService.getPersons();
    return ResponseEntity.ok(persons);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Person> getPerson(@PathVariable String id) {
    Person person = personService.getPerson(id);
    return ResponseEntity.ok(person);
  }

  @PostMapping
  public ResponseEntity<Person> createPerson(@RequestBody @Valid PersonPayload personPayload) {
    Person person = personService.createPerson(personPayload.toPerson());
    return ResponseEntity.status(HttpStatus.CREATED).body(person);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Person> createPerson(@PathVariable String id,
      @RequestBody @Valid PersonPayload personPayload) {
    Person person = personService.updatePerson(id, personPayload.toPerson());
    return ResponseEntity.ok(person);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Person> deletePerson(@PathVariable String id) {
    Person person = personService.delete(id);
    return ResponseEntity.ok(person);
  }

}