package com.example.commonapi.events.product;


import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class ReserveCancelledEvent {

  private Long orderId;
  @TargetAggregateIdentifier
  private Long productId;
  private int amount;

  public ReserveCancelledEvent() {
  }

  public ReserveCancelledEvent(Long orderId, Long productId, int amount) {
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
