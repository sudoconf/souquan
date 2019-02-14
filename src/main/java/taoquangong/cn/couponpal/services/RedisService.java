package taoquangong.cn.couponpal.services;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Brian
 */

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;
    private ValueOperations<String, Object> valueOperations;

    @SuppressWarnings("unchecked")
    public RedisService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
    }

    public Object getObj(String key) {
        return valueOperations.get(key);
    }

    public void setObj(String key, Object o, Long expire) {
        valueOperations.set(key, o, expire, TimeUnit.HOURS);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
