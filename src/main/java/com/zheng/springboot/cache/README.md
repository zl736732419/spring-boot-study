缓存实现的核心就是Cache和CacheManager，可以通过CacheManagerCustomizer将缓存注册到springboot中

springboot默认内部就实现了一套缓存功能，通过添加@EnableCaching和@Cacheable("piDecimals")
就能实现方法调用的缓存，如果没有指定具体的缓存实现，springboot使用concurrent maps并缓存到内存中

当然我们可以提供多种缓存实现，springboot按照下面的顺序查找缓存实现：
```
Generic
JCache (JSR-107) (EhCache 3, Hazelcast, Infinispan, and others)
EhCache 2.x
Hazelcast
Infinispan
Couchbase
Redis
Caffeine
Simple
None
```

各个缓存的实现对应的CacheManager
```
CacheManager	    描述
SimpleCacheManager	使用简单的Collection来存储缓存，主要用来测试用途
ConcurrentMapCacheManager	使用ConcurrentMap来存储
NoOpCacheManager	仅测试用途，不会市级存储缓存
EhCacheCacheManager	使用EhCache作为缓存技术
GuavaCacheManager	使用Google Guava的GuavaCache作为缓存技术
HazelcastCacheManager	使用Hazelcast作为缓存技术
JCacheCacheManager	支持JCache(JSR-107)标准的实现作为缓存技术，如Apache Commons JCS
RedisCacheManager	使用Redis作为缓存技术
```

这里我将实现jcache, ehcache, redis, simple, None几种方式

需要引入spring-boot-starter-cache依赖

springboot缓存的实现方式采用了统一的缓存注解，所以整体缓存使用上不会有变化，只需要提供具体类型的缓存实现即可


##### ehcache2x
需要在classpath下提供ehcache.xml配置文件，springboot将会使用EhCacheCacheManager对缓存进行管理
需要添加net.sf.ehcache依赖
```
Spring框架的缓存标注：
@Cacheable("a_cache_name")，作用于被缓冲的方法，对方法执行结果的缓存
@CacheEvict，作用于被缓冲的方法，将方法执行的结果从缓存中移除
@CachePut，更新缓存
@Caching，将作用于一个方法的多个缓存操作打包为一个整体
@CacheConfig，作用于Java类，设置通用的缓存相关参数
```

##### jcache ehcache3x
需要引入依赖
```
<dependency>
  <groupId>org.ehcache</groupId>
  <artifactId>ehcache</artifactId>
  <version>3.1.3</version>
</dependency>
<!-- JSR107 API -->
<dependency>
  <groupId>javax.cache</groupId>
  <artifactId>cache-api</artifactId>
</dependency>
```
指定配置文件路径
spring.cache.jcache.config=classpath:cache/ehcache3x.xml
在使用jcache ehcache时，缓存注解需要引用jsr定义的而不是spring提供的,具体参考CacheService
```
JSR-107 (JCache)标准已经定义了缓存的标准标注，标准标注与Spring框架的缓存标注的对应关系如下：
@CacheResult，类似于Spring的@Cacheable
@CachePut，类似于Spring的@CachePut
@CacheRemove，类似于Spring的@CacheEvict
@CacheRemoveAll，类似于Spring的@CacheEvict(allEntries=true)
@CacheDefaults，类似于Spring的@CacheConfig
@CacheKey
@CacheValue
```
注意要使jcache生效，需要将ehcache2的注入注释掉

##### redis cache
使用redis cache需要借助前面与redis的整合，这里直接采用的redisTemplate来获取redis连接
需要注册RedisCacheManager

注意，在使用缓存注解时，方法只会操作方法执行的结果，比如如果我们在int UserService.save(User user)方法上添加了
@CachePut(value="user", key="#user.id"),最终缓存的只会是(userId, changeRecordCount)
所以在使用findById(userId)进行查找时就会报错：int can not cast to User type
