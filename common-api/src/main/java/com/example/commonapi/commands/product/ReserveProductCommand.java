package com.example.commonapi.commands.product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by kellen on 2017/9/5.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReserveProductCommand {

  @TargetAggregateIdentifier
  private Long orderId;
  private Long skuId;
  private int quantity;
}
