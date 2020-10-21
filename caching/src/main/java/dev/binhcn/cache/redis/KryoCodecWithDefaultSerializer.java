package dev.binhcn.cache.redis;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import org.redisson.codec.Kryo5Codec;

public class KryoCodecWithDefaultSerializer extends Kryo5Codec {
  /**
   * Instantiates a new Kryo codec with default serializer.
   */
  public KryoCodecWithDefaultSerializer() {
    super();
  }

  @Override
  public Kryo createKryo(ClassLoader classLoader) {
    Kryo kryo = super.createKryo(classLoader);
    kryo.setDefaultSerializer(CompatibleFieldSerializer.class);
    return kryo;
  }
}
