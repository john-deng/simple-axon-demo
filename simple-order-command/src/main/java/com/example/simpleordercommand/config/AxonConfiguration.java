package com.example.simpleordercommand.config;

import cn.vpclub.spring.boot.axon.autoconfigure.EventSourcingRepositoryFactory;
import cn.vpclub.spring.boot.axon.exceptions.SagaClassNotFoundException;
import cn.vpclub.spring.boot.axon.properties.AxonSagaProperties;
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
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@Slf4j
@EnableConfigurationProperties({AxonSagaProperties.class})
public class AxonConfiguration {
    // Saga Configuration
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private AxonSagaProperties axonSagaProperties;

    @Autowired
    private EventSourcingRepositoryFactory repositoryFactory;

    @Bean
    public SpringAMQPMessageSource springAMQPMessageSource(Serializer serializer) {
        return new SpringAMQPMessageSource(serializer) {
            @RabbitListener(queues = "moses-simpleorder-command") // Saga depends on it.
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                log.info("message received: {}", message.toString());
                super.onMessage(message, channel);
            }
        };
    }

    @Bean
    @ConditionalOnProperty(prefix = "axon.saga", name = "enabled", havingValue = "true")
    public SagaConfiguration orderSagaConfiguration(Serializer serializer) {

        Class<?> sagaClass = axonSagaProperties.getSagaClass();

        if ( null == sagaClass ) {
            throw new SagaClassNotFoundException();
        }

        SagaConfiguration sagaConfiguration = SagaConfiguration.subscribingSagaManager(
                sagaClass,
                c -> springAMQPMessageSource(serializer));
        sagaConfiguration.registerHandlerInterceptor(c -> transactionManagingInterceptor());
        return sagaConfiguration;
    }

    @Bean
    @ConditionalOnProperty(prefix = "axon.saga", name = "enabled", havingValue = "true")
    public TransactionManagingInterceptor transactionManagingInterceptor() {
        return new TransactionManagingInterceptor(new SpringTransactionManager(transactionManager));
    }
}
