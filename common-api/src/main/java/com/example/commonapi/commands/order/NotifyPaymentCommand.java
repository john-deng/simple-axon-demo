package com.example.commonapi.commands.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by kellen on 2017/9/13.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifyPaymentCommand {

  @TargetAggregateIdentifier
  private Long orderId;

  private String transactionNo;

  private Integer payType;

}
