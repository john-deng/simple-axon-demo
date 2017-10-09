package com.example.simpleordercommand.config;

import com.example.simpleordercommand.saga.OrderSaga;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.config.SagaConfiguration;
import org.axonframework.messaging.interceptors.TransactionManagingInterceptor;
import org.axonframework.serialization.Serializer;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@Slf4j
public class AxonConfiguration {
    // listen to queue
    @Bean
    public SpringAMQPMessageSource springAMQPMessageSource(Serializer serializer) {
        return new SpringAMQPMessageSource(serializer) {
            @RabbitListener(queues = "moses-simpleproduct-command") // Saga depends on it.
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                log.info("message received: " + message.toString());
                super.onMessage(message, channel);
            }
        };
    }

    // Saga Configuration
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public SagaConfiguration<OrderSaga> orderSagaConfiguration(Serializer serializer) {
        SagaConfiguration<OrderSaga> sagaConfiguration = SagaConfiguration.subscribingSagaManager(
                OrderSaga.class,
                c -> springAMQPMessageSource(serializer));
        sagaConfiguration.registerHandlerInterceptor(c -> transactionManagingInterceptor());
        return sagaConfiguration;
    }

    @Bean
    public TransactionManagingInterceptor transactionManagingInterceptor() {
        return new TransactionManagingInterceptor(new SpringTransactionManager(transactionManager));
    }
}
