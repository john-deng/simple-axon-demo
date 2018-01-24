package com.example.simpleproductquery.config;

import cn.vpclub.spring.boot.axon.autoconfigure.EventSourcingRepositoryFactory;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class AxonConfiguration {
    // listen to queue
    @Autowired
    private EventSourcingRepositoryFactory repositoryFactory;

    @Bean
    public SpringAMQPMessageSource springAMQPMessageSource(Serializer serializer) {
        return new SpringAMQPMessageSource(serializer) {
            @RabbitListener(queues = "moses-simpleproduct-query")
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                log.info("message received: " + message.toString());
                super.onMessage(message, channel);
            }
        };
    }
}
