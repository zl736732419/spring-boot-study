缓存实现的核心就是Cache和CacheManager，可以通过CacheManagerCustomizer将缓存注册到springboot中

springboot默认内部就实现了一套缓存功能，通过添加@EnableCaching和@Cacheable("piDecimals")
就能实现方法调用的缓存，如果没有指定具体的缓存实现，springboot使用concurrent maps并缓存到内存中

当然我们可以提供多种缓存实现：
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
这里我将实现ehcache, redis, simple, None4种方式

需要引入spring-boot-starter-cache依赖


