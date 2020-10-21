package dev.binhcn.cache.redis;

import dev.binhcn.cache.config.RedisConfig;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.connection.balancer.LoadBalancer;
import org.redisson.connection.balancer.RoundRobinLoadBalancer;

public class RedisCache {

  private RedisConfig config;

  private RedissonClient redisson;

  public RedisCache() {
    Config redisConfig = new Config();
    this.config = new RedisConfig();
    if (config.isKryoCodec()) {
      String codecType = config.getCodecType();
      Codec codec = null;
      switch (codecType) {
        case "kryo":
          codec = new KryoCodecWithDefaultSerializer();
          break;
        default:
          codec = new JsonJacksonCodec();
      }
      redisConfig.setCodec(codec);
    }

    if (config.isCluster()) {
      String[] nodeAddress = config.getRedisNodes().toArray(new String[0]);
      LoadBalancer loadBalancer = new RoundRobinLoadBalancer();
      redisConfig.useClusterServers().addNodeAddress(nodeAddress)
          .setScanInterval(config.getScanInterval())
          .setSlaveConnectionMinimumIdleSize(config.getSlaveConnectionMinimumIdleSize())
          .setSlaveConnectionPoolSize(config.getSlaveConnectionPoolSize())
          .setMasterConnectionMinimumIdleSize(config.getMasterConnectionMinimumIdleSize())
          .setMasterConnectionPoolSize(config.getMasterConnectionPoolSize())
          .setIdleConnectionTimeout(config.getIdleConnectionTimeout())
          .setConnectTimeout(config.getConnectTimeout()).setTimeout(config.getResponseTimeout())
          .setRetryAttempts(config.getRetryAttempts()).setRetryInterval(config.getRetryInterval())
          .setFailedSlaveReconnectionInterval(config.getReconnectionTimeout())
          .setFailedSlaveCheckInterval(config.getFailedAttempts())
          .setReadMode(ReadMode.valueOf(config.getReadMode()))
          .setLoadBalancer(loadBalancer)
          .setPingConnectionInterval(1000);
    } else {
      redisConfig.useSingleServer().setAddress(config.getRedisNodes().get(0))
          .setConnectionMinimumIdleSize(config.getMasterConnectionMinimumIdleSize())
          .setConnectionPoolSize(config.getMasterConnectionPoolSize())
          .setIdleConnectionTimeout(config.getIdleConnectionTimeout())
          .setConnectTimeout(config.getConnectTimeout()).setTimeout(config.getResponseTimeout())
          .setRetryAttempts(config.getRetryAttempts()).setRetryInterval(config.getRetryInterval());
    }

    redisson = Redisson.create(redisConfig.setNettyThreads(64));

    System.out.println("Start Redisson success");
  }

  public <K, V> void cacheValueWithTimeout(String key, Map<K, V> map, long timeInMinutes) {
    RMap<K, V> rMap = redisson.getMap(key);
    for (Map.Entry<K, V> entry : map.entrySet())
      rMap.put(entry.getKey(), entry.getValue());
  }

  public <K, V> Map<K, V> getDataMapFromCache(String keyMap) {
    RMap<K, V> rMap = redisson.getMap(keyMap);
    return rMap.readAllMap();
  }

  public String genCashierKey(String zpTransToken) {
    StringBuilder builder = new StringBuilder();
    builder.append("cashier-integration")
        .append('_')
        .append(zpTransToken);
    return builder.toString();
  }

  public <T> void cacheValue(String key, T value) {
    redisson.getBucket(key).set(value, config.getCacheExpireMinute(), TimeUnit.MINUTES);
  }

  public <T> T getValueFromCache(String key) {
    RBucket<T> bucket = redisson.getBucket(key);
    return bucket.get();
  }

}
