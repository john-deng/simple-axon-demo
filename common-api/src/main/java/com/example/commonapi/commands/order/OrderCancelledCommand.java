package com.example.commonapi.commands.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by kellen on 2017/9/13.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCancelledCommand {

  @TargetAggregateIdentifier
  private Long orderId;

  /**
   * 取消原因
   */
  private String cancelCause;

}
