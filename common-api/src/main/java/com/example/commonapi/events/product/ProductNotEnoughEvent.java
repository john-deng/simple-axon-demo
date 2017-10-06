package com.example.commonapi.events.product;


import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class ProductNotEnoughEvent {

  private Long orderId;
  @TargetAggregateIdentifier
  private Long productId;

  public ProductNotEnoughEvent() {
  }

  public ProductNotEnoughEvent(Long orderId, Long productId) {
    this.orderId = orderId;
    this.productId = productId;
  }

  public Long getOrderId() {
    return orderId;
  }

  public Long getProductId() {
    return productId;
  }
}
