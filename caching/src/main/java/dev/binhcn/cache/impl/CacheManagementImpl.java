package dev.binhcn.cache.impl;

import dev.binhcn.cache.CacheManagement;
import dev.binhcn.cache.redis.RedisCache;
import java.util.Map;

public class CacheManagementImpl implements CacheManagement {

  private RedisCache redisCache;

  public CacheManagementImpl() {
    redisCache = new RedisCache();
  }

  @Override
  public Map<String, String> getMapData(String zpTransToken) {
//    String keyMap = redisCache.genCashierKey(zpTransToken);
    return redisCache.getDataMapFromCache(zpTransToken);
  }

  @Override
  public void saveDataWithTimeout(Map<String, String> dataParams, String zpTransToken, int timeoutInMin) {
//    String mapKey = redisCache.genCashierKey(zpTransToken);
    redisCache.cacheValueWithTimeout(zpTransToken, dataParams, timeoutInMin);
  }

  @Override
  public void cacheValue(String key, String value) {
    redisCache.cacheValue(key, value);
  }

  @Override
  public String getValueFromCache(String key) {
    return redisCache.getValueFromCache(key);
  }
}
