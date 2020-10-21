package dev.binhcn.cache.config;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class RedisConfig {
  private boolean cluster = true;
  private int scanInterval = 2000;
  private int slaveConnectionMinimumIdleSize = 10;
  private int slaveConnectionPoolSize = 200;
  private int masterConnectionMinimumIdleSize = 10;
  private int masterConnectionPoolSize = 200;
  private int idleConnectionTimeout = 10000;
  private int connectTimeout = 10000;
  private int responseTimeout = 10000;
  private int retryAttempts = 3;
  private int retryInterval = 1000;
  private int reconnectionTimeout = 3000;
  private int failedAttempts = 3;
  private String readMode = "MASTER";
  private boolean kryoCodec = true;
  private List<String> redisNodes = new ArrayList<String>() {
    {
      add("redis://10.60.45.5:8000");
      add("redis://10.60.45.5:8001");
      add("redis://10.60.45.5:8002");
      add("redis://10.60.45.5:8003");
      add("redis://10.60.45.5:8004");
      add("redis://10.60.45.5:8005");
    }
  };
  private int cacheExpireMinute = 30;
  private int lockAcquireTimeMillisecond = 10;
  private String codecType = "kryo";
}
