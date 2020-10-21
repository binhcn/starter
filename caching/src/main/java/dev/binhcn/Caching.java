package dev.binhcn;

import dev.binhcn.cache.CacheManagement;
import dev.binhcn.cache.impl.CacheManagementImpl;
import java.util.HashMap;
import java.util.Map;

public class Caching {
  public static void main(String[] args) {
    CacheManagement cacheManagement = new CacheManagementImpl();

    Map<String, String> map = new HashMap<>();
    map.put("binh1", "nguyen");
    map.put("dien1", "minh");

    cacheManagement.saveDataWithTimeout(map, "123", 1);
    Map<String, String> result = cacheManagement.getMapData("123");
    System.out.println(result.toString());

    cacheManagement.cacheValue("thai", "binh");
    String str = cacheManagement.getValueFromCache("thai");
    System.out.println(str);

  }
}

