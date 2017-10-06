package com.example.commonapi.events.product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductReserveFailedEvent {

  private Long orderId;
  @TargetAggregateIdentifier
  private Long productId;

}
