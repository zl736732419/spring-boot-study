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


#####ehcache
需要在classpath下提供ehcache.xml配置文件，springboot将会使用EhCacheCacheManager对缓存进行管理
需要添加net.sf.ehcache依赖



