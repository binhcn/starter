package dev.binhcn.config;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {
  private String name;
  private String description;
  private String uploadDir;

  private Duration connectTimeout = Duration.ofMillis(1000);
  private Duration readTimeout = Duration.ofSeconds(30);

  @Valid
  private final Security security = new Security();

  @Data
  public static class Security {
    private String username;
    private String password;
    private List<String> roles = new ArrayList<>();
    private boolean enabled;
    private Map<String, Boolean> permissions = new HashMap<>();
  }
}
