####application.properties
#####默认配置
application.properties配置文件通常用于重新一些默认变量值或者定义自己的环境变量
默认配置文件加载位置：
1. 当前目录/config
2. 当前目录
3. classpath/config
4. classpath

1.2点适合生产环境，比如打包成jar运行，当前目录指的是jar包目录，运行时必须先路由到jar包命令再执行java -jar demo.jar命令
3.4点适合在开发环境中使用，优先级从1->4依次递减
#####自定义配置文件名称和目录
默认配置文件名称为:application.properties,可以通过spring.config.name修改配置文件名称
配置文件的目录默认为上面4个不同目录，可以通过spring.config.location指定目录
spring.config.name和spring.config.location需要通过命令行变量的方式来指定，比如
java -jar demo.jar --spring.config.name=demo
java -jar demo.jar --spring.config.location=classpath:/default.properties,classpath:/override.properties
如果spring.config.location指定的是目录而不是文件，那么需要以/结尾，比如
spring.config.location=classpath:/,classpath:/config/,file:./,file:./config/
需要注意的是spring.config.location配置的目录加载顺序是相反的，上面的目录配置，在进行查找时则会变成：
1. file:./config/
2. file:./
3. classpath:/config/
4. classpath:/

spring.config.location会覆盖掉默认配置的目录，如果只是想再添加几个目录，可以使用
spring.config.additional-location,使用它指定的目录会优先于默认的目录
比如spring.config.additional-location=file:./custom-config/,classpath:custom-config/,最后的优先级为
1. classpath:custom-config/
2. file:./custom-config/
3. file:./config/
4. file:./
5. classpath:/config/
6. classpath:/

spring.config.additional-location也应该在jar包启动的时候通过命令行指定

####profile-specific properties
可以根据不同的运行环境匹配不同的配置文件，profile配置文件与默认配置文件一样，具有相同的配置目录
当使用profile配置文件时，它总是会替换掉非profile配置文件中的配置项，比如
classpath:config/application.properties
name=default
classpath:application-dev.properties
name=dev
最终name的取值dev
profile环境通过spring.profiles.active参数指定，java -jar demo.jar --spring.profiles.active=dev
需要注意的是如果在使用profile时指定了spring.config.location，那么要想使指定的location中的配置文件对profile生效，location
只能设置为目录的形式，而不能指定配置文件路径

在配置文件中也可以通过${var_name}直接使用前面定义的变量