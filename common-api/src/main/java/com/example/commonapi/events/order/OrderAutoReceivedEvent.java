package com.example.commonapi.events.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.io.Serializable;

/**
 * Created by kellen on 2017/9/21.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAutoReceivedEvent implements Serializable {

  @TargetAggregateIdentifier
  private Long orderId;

  /**
   * 子订单id
   */
  private Long subOrderId;

}
