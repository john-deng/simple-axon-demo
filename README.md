# Simple Axon Demo

## 获取代码

```bash
git clone git@gitlab.vpclub:moses-demos/simple-axon-demo.git 
```

```xml
	<parent>
		<groupId>cn.vpclub</groupId>
		<artifactId>spring-boot-starters</artifactId>
		<version>1.5.20-SNAPSHOT</version>
	</parent>
```

```yaml

spring:
  profiles:
    include:
    - web
    - swagger
    - locale
    - logging
    - jpa
    - druid
    - axon-jgroups
    - axon-amqp
    - axon-eventstore
    - axon-scheduler
    - axon-saga

axon:
  eventstore:
    aggregate-package: com.example.simpleordercommand.aggregates
  saga:
    saga-class: com.example.simpleordercommand.saga.OrderSaga

```

```java


```