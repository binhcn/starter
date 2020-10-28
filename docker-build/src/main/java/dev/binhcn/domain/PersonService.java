package dev.binhcn.domain;

import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonService {

  private final PersonRepository personRepository;

  @Autowired
  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public Collection<Person> getPersons() {
    log.info("Getting all persons");
    return personRepository.findAll();
  }

  public Person getPerson(String id) {
    log.info("Getting person by id {}", id);
    return personRepository.findById(id);
  }

  public Person delete(String id) {
    log.info("Deleting person by id {}", id);
    return personRepository.delete(id);
  }

  public Person createPerson(Person person) {
    log.info("Creating {}", person);
    return personRepository.save(person);
  }

  public Person updatePerson(String id, Person person) {
    log.info("Updating person by id {}", id);
    return personRepository.update(id, person);
  }

}