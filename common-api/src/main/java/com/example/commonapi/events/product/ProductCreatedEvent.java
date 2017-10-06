package com.example.commonapi.events.product;

import lombok.Getter;
import lombok.Setter;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Getter
@Setter
public class ProductCreatedEvent {

  @TargetAggregateIdentifier
  private Long id;
  private String name;
  private long price;
  private int stock;

  public ProductCreatedEvent() {
  }

  public ProductCreatedEvent(Long id, String name, long price, int stock) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.stock = stock;
  }

}
