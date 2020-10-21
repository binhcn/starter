package dev.binhcn;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Map;

public class ObjectToMapExample {
  public static void main(String[] args) {

    ObjectMapper oMapper = new ObjectMapper();

    Family family = new Family();
    family.setSize(5);
    family.setAddress("Tien Giang");

    Student obj = new Student();
    obj.setName("mkyong");
    obj.setAge(34);
    obj.setSkills(Arrays.asList("java","node"));
    obj.setFamily(family);

    // object -> Map
    Map<String, Object> map = oMapper.convertValue(obj, Map.class);
    Map<String, String> map2 = oMapper.convertValue(obj, Map.class);
    System.out.println(map);
    System.out.println(map2);

  }
}
