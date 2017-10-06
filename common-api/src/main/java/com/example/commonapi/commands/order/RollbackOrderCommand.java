package com.example.commonapi.commands.order;


import lombok.*;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RollbackOrderCommand {

  @TargetAggregateIdentifier
  private Long orderId;

}
