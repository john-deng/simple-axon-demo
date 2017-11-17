package com.example.simpleordercommand.handlers;

import com.example.commonapi.events.order.OrderConfirmedEvent;
import com.example.commonapi.events.product.ProductReservedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
@ProcessingGroup("message")
public class OrderEventHandlers {

    @EventHandler
    public void on(ProductReservedEvent event) {
        log.info("product {} reserved", event.getProductId());
    }
    @EventHandler
    public void on(OrderConfirmedEvent event) {
        log.info("order {} confirmed", event.getOrderId());
    }
}
