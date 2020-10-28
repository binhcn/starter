package dev.binhcn.infra;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.binhcn.domain.Person;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonPayload {

  @NotBlank
  private String name;

  @NotNull
  @Positive
  private Integer age;

  @JsonIgnore
  public Person toPerson() {
    return Person.builder()
        .name(getName())
        .age(getAge())
        .build();
  }

}