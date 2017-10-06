package com.example.commonapi.events.pay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.io.Serializable;

/**
 * Created by kellen on 2017/9/13.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaidEvent implements Serializable {

  @TargetAggregateIdentifier
  private Long orderId;

  /**
   * 支付平台交易流水号
   */
  private String transactionNo;

  private Integer payType;

}
