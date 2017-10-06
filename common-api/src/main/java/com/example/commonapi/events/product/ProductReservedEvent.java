package com.example.commonapi.events.product;


import lombok.ToString;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@ToString
public class ProductReservedEvent {

  private Long orderId;
  @TargetAggregateIdentifier
  private Long productId;
  private int amount;

  public ProductReservedEvent() {
  }

  public ProductReservedEvent(Long orderId, Long productId, int amount) {
    this.orderId = orderId;
    this.productId = productId;
    this.amount = amount;
  }

  public Long getOrderId() {
    return orderId;
  }

  public Long getProductId() {
    return productId;
  }

  public int getAmount() {
    return amount;
  }
}
