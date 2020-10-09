package dev.binhcn.controller;

import dev.binhcn.config.AppProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

  @Autowired
  private AppProperties appProperties;

  @Value("${app.security.roles}")
  private List<String> roles = new ArrayList<>();

  @GetMapping("/full")
  public AppProperties getAppProperties() {
    return appProperties;
  }

  @GetMapping("/part")
  public Map<String, String> getAppDetails() {
    Map<String, String> appDetails = new HashMap<>();
    appDetails.put("name", appProperties.getName());
    appDetails.put("description", appProperties.getDescription());
    return appDetails;
  }

  @GetMapping("/value")
  public List<String> getValue() {
    return roles;
  }

}
