package dev.binhcn;

import java.util.List;
import lombok.Data;

@Data
public class Student {
  private String name;
  private int age;
  private List<String> skills;
  private Family family;
}
