package com.example.simpleproductcommand.aggregates;

import com.example.commonapi.commands.product.CreateProductCommand;
import com.example.commonapi.events.product.ProductCreatedEvent;
import com.example.commonapi.events.product.ProductNotEnoughEvent;
import com.example.commonapi.events.product.ProductReservedEvent;
import com.example.commonapi.events.product.ReserveCancelledEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Slf4j
@Aggregate
public class ProductAggregate {


  @AggregateIdentifier
  private Long id;
  private String name;
  private int stock;
  private long price;

  public ProductAggregate() {
  }

  public ProductAggregate(Long id, String name, int stock, long price) {
    apply(new ProductCreatedEvent(id, name, price, stock));
  }

  @CommandHandler
  public ProductAggregate(CreateProductCommand command) {
    apply(new ProductCreatedEvent(command.getId(), command.getName(), command.getPrice(),
        command.getStock()));
  }

  @EventHandler
  public void on(ProductCreatedEvent event) {
    this.id = event.getId();
    this.name = event.getName();
    this.price = event.getPrice();
    this.stock = event.getStock();
    log.debug("ProductRequest [{}] {} {}x{} is created.", id, name, price, stock);
  }

  public void reserve(Long orderId, int amount) {
    if (stock >= amount) {
      apply(new ProductReservedEvent(orderId, id, amount));

    } else {
      apply(new ProductNotEnoughEvent(orderId, id));
    }
  }

  public void cancellReserve(Long orderId, int amount) {
    apply(new ReserveCancelledEvent(orderId, id, amount));
  }

  @EventHandler
  public void on(ProductReservedEvent event) {
    int oriStock = stock;
    stock = stock - event.getAmount();
    log.info("ProductRequest {} stock change {} -> {}", id, oriStock, stock);
  }

  @EventHandler
  public void on(ReserveCancelledEvent event) {
    stock += event.getAmount();
    log.info("Reservation rollback, product {} stock changed to {}", id, stock);
  }

  @EventHandler
  public void on(ProductNotEnoughEvent event) {
    log.info("product not enough, product,{} stock,{}", id, stock);
  }

  public String getName() {
    return name;
  }

  public int getStock() {
    return stock;
  }

  public long getPrice() {
    return price;
  }
}

