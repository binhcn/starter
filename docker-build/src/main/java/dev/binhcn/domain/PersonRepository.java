package dev.binhcn.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {

  private HashMap<String, Person> datas = new LinkedHashMap<>();

  public Collection<Person> findAll() {
    return datas.values();
  }

  public Person findById(String id) {
    return datas.getOrDefault(id, Person.builder().build());
  }

  public Person save(Person person) {
    String id = UUID.randomUUID().toString();
    person.setId(id);
    return datas.putIfAbsent(id, person);
  }

  public Person update(String id, Person person) {
    return datas.replace(id, person);
  }

  public Person delete(String id) {
    return datas.remove(id);
  }

}
