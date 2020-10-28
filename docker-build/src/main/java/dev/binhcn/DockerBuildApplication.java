package dev.binhcn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DockerBuildApplication {

  public static void main(String[] args) {
    SpringApplication.run(DockerBuildApplication.class, args);
    log.info("The application is using {} mb", (Runtime.getRuntime().totalMemory() / 1024 / 1024));
  }

}
