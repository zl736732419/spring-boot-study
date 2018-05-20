package com.zheng.springboot.cache.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * redis做缓存
 * @Author zhenglian
 * @Date 2018/5/19 13:48
 */
@Configuration
@EnableCaching
public class RedisCacheCustomer extends CachingConfigurerSupport {

    @Autowired
    private RedisCacheProperties redisCacheProperties;
    
//    @Override
//    public KeyGenerator keyGenerator() {
//
//        return new KeyGenerator() {
//            @Override
//            public Object generate(Object target, Method method, Object... params) {
//                StringBuilder sb = new StringBuilder();
//                sb.append(target.getClass().getName());
//                sb.append(method.getName());
//                for (Object obj : params) {
//                    sb.append(obj.toString());
//                }
//                return sb.toString();
//            }
//        };
//    }
    
    private RedisSerializer redisJsonSerializer() {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }
    
    private RedisCacheConfiguration redisCacheConfiguration() {
        Duration ttl = Duration.of(redisCacheProperties.getTtl(), ChronoUnit.SECONDS);
        Boolean cacheNullValues = redisCacheProperties.getCacheNullValues();

        RedisSerializer valueSerializer = redisJsonSerializer();
        RedisSerializationContext.SerializationPair<String> valuePair = RedisSerializationContext.SerializationPair
                .fromSerializer(valueSerializer);
        
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(ttl);
        if (Optional.ofNullable(cacheNullValues).isPresent()
                && !cacheNullValues) {
            configuration = configuration.disableCachingNullValues();
        }
        return configuration.serializeValuesWith(valuePair);
    }
    
    
    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(connectionFactory);
        builder.cacheDefaults(redisCacheConfiguration());
        RedisCacheManager cacheManager = builder.build();
        cacheManager.afterPropertiesSet();
        return cacheManager;
    }
    
}
