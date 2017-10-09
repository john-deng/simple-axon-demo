package com.example.simpleproductquery.handlers;

import com.example.commonapi.events.order.OrderConfirmedEvent;
import com.example.commonapi.events.product.ProductCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
@ProcessingGroup("message")
public class ProductEventHandlers {

    @EventHandler
    public void handle(ProductCreatedEvent event) {
        log.info("product {} added", event.getId());
    }

    // once order is confirmed in simple-order-command, we can listen OrderConfirmedEvent here.
    @EventHandler
    public void handle(OrderConfirmedEvent event) {
        log.info("order {} confirmed", event.getOrderId());
    }
}
