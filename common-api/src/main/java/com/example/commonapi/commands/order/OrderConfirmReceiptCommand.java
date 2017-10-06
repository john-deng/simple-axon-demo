package com.example.commonapi.commands.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by kellen on 2017/9/14.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderConfirmReceiptCommand {

  /**
   * 主订单id
   */
  @TargetAggregateIdentifier
  private Long orderId;

  /**
   * 子订单id
   */
  private Long subOrderId;

}
