package com.example.commonapi.commands.product;


import lombok.*;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RollbackReservationCommand {

  @TargetAggregateIdentifier
  private Long orderId;
  private Long skuId;
  private int number;
}
