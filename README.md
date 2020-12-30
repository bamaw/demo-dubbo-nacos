# 基于dubbo rpc框架集成nacos注册中心(极简案例)

### 1.版本说明：
* Spring Cloud Greenwich.SR2
* Spring Cloud Alibaba 2.1.2.RELEASE
* Spring Boot 2.1.1.RELEASE

### 2.项目结构介绍
* demo-dubbo-nacos-api 服务api接口
* demo-dubbo-nacos-provider 服务提供者
* demo-dubbo-nacos-consumer 服务消费者

### 3.项目依赖

#### 3.0 demo-dubbo-nacos 的 pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.daobili</groupId>
    <artifactId>demo-dubbo-nacos</artifactId>
    <version>Beikezhan.M1</version>
    <modules>
        <module>demo-dubbo-nacos-provider</module>
        <module>demo-dubbo-nacos-api</module>
        <module>demo-dubbo-nacos-consumer</module>
    </modules>
    <packaging>pom</packaging>


</project>
```

#### 3.1 demo-dubbo-nacos-api 的 pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>demo-dubbo-nacos</artifactId>
        <groupId>com.daobili</groupId>
        <version>Beikezhan.M1</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>demo-dubbo-nacos-api</artifactId>


</project>

```

#### 3.2 demo-dubbo-nacos-provider 的 pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>demo-dubbo-nacos</artifactId>
        <groupId>com.daobili</groupId>
        <version>Beikezhan.M1</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>demo-dubbo-nacos-provider</artifactId>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <spring-cloud.version>Greenwich.SR2</spring-cloud.version>
    </properties>


    <!--
        版本依赖：
        SpringCloud Greenwich.SR2
        SpringCloud Alibaba 2.1.1.RELEASE
        SpringBoot 2.1.1.RELEASE
      -->
    <dependencyManagement>
        <dependencies>

            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.1.1.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2.1.1.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

        </dependencies>
    </dependencyManagement>


    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter</artifactId>
            <version>2.1.2.RELEASE</version>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.cloud</groupId>
                    <artifactId>spring-cloud-context</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-dubbo</artifactId>
            <version>2.1.2.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-nacos-discovery</artifactId>
            <version>2.1.1.RELEASE</version>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.cloud</groupId>
                    <artifactId>spring-cloud-context</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>com.daobili</groupId>
            <artifactId>demo-dubbo-nacos-api</artifactId>
            <version>Beikezhan.M1</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-context</artifactId>
            <version>2.1.1.RELEASE</version>
        </dependency>

    </dependencies>

</project>
```

#### 3.3 demo-dubbo-nacos-consumer 的 pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>demo-dubbo-nacos</artifactId>
        <groupId>com.daobili</groupId>
        <version>Beikezhan.M1</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>demo-dubbo-nacos-consumer</artifactId>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>2.1.3.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter</artifactId>
            <version>2.1.2.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-dubbo</artifactId>
            <version>2.1.2.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>com.daobili</groupId>
            <artifactId>demo-dubbo-nacos-api</artifactId>
            <version>Beikezhan.M1</version>
        </dependency>

        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-nacos-discovery</artifactId>
            <version>2.1.1.RELEASE</version>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.cloud</groupId>
                    <artifactId>spring-cloud-context</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-context</artifactId>
            <version>2.1.1.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>4.5.5</version>
        </dependency>

    </dependencies>

</project>
```


### 4.项目配置

#### 4.0 demo-dubbo-nacos-api 的 application.yml
```yml
无
```

#### 4.1 demo-dubbo-nacos-provider 的 application.yml
```yml
server:
  port: 8866

spring:
  application:
    # 应用名称
    name: demo-dubbo-nacos-provider
  # 配置 Nacos 注册中心
  cloud:
    nacos:
      discovery:
        # Nacos 服务器地址-单机版
        server-addr: 127.0.0.1:8848

dubbo:
  # 提供方应用信息，用于计算依赖关系
  application:
    name: demo-dubbo-nacos-provider
  # 扫描需要暴露的服务，可以被 @EnableDubbo 注解替代
  scan:
    base-packages: com.daobili.provider

  # 用 dubbo 协议在 20880 端口暴露服务
  protocol:
    name: dubbo
    port: 20880
  # 使用 nacos 注册中心暴露服务地址
  registry:
    protocol: nacos
    address: spring-cloud://localhost
```

#### 4.2 demo-dubbo-nacos-consumer 的 application.yml
```yml
server:
  port: 8867

spring:
  application:
    # 应用名称
    name: demo-dubbo-nacos-consumer
  # 配置 Nacos 注册中心
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
dubbo:
  application:
    name: demo-dubbo-nacos-consumer
  cloud:
    # 用于消费者订阅提供方的应用名称列表
    subscribed-services: demo-dubbo-nacos-provider
  scan:
    base-packages: com.daobili.provider

```

### 5.项目测试代码

#### 5.0 demo-dubbo-nacos-api 的 DemoService
```java
public interface DemoService {

    String greet(String name);
}
```

#### 5.1 demo-dubbo-nacos-provider 的 DemoServiceImpl

```java
@Service(
        version = "1.0.0",
        interfaceClass = DemoService.class,
        cluster = "failfast",
        loadbalance = "roundrobin"
)
public class DemoServiceImpl implements DemoService {

    @Override
    public String greet(String name) {
        return "hello, "+ name;
    }
}
```

#### 5.2 demo-dubbo-nacos-consumer 的 TestController

```java
@RestController
public class TestController {


    @Reference(
            version = "1.0.0",
            interfaceClass = DemoService.class,
            cluster = "failfast"
    )
    private DemoService demoService;

    @GetMapping("/greet")
    public String greet(String name) {
        return demoService.greet(name);
    }

}
```

### 6.项目启动类
#### 6.0 demo-dubbo-nacos-provider 的 ProviderApplication

```java
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
```

#### 6.1 demo-dubbo-nacos-consumer 的 ConsumerApplication

```java
@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
```

### 6.测试
* 1.启动本地Nacos服务器，访问`http://localhost:8848/nacos/ `
控制台显示如下：
![](https://i.loli.net/2020/12/30/ZMo6aEcxHhz7dpL.png)
* 2.依次启动`demo-dubbo-nacos-provider`、`demo-dubbo-nacos-consumer`
![](https://i.loli.net/2020/12/30/pe9LtObP5IklAMr.png)
* 3.调试测试接口 `http://localhost:8867/greet?name=bamaw`
![](https://i.loli.net/2020/12/30/yFDfoMOgdR5ZwAH.png)




