package dev.binhcn.cache;

import java.util.Map;

public interface CacheManagement {

  Map<String, String> getMapData(String zpTransToken);

  void saveDataWithTimeout(Map<String, String> dataParams, String zpTransToken, int timeoutInMin);

  void cacheValue(String key, String value);

  String getValueFromCache(String key);
}
