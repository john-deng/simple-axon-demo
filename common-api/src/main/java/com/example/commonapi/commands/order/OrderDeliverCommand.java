package com.example.commonapi.commands.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.io.Serializable;

/**
 * Created by kellen on 2017/9/15.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDeliverCommand implements Serializable {

  /**
   * 主订单id
   */
  @TargetAggregateIdentifier
  private Long orderId;

  /**
   * 子订单id
   */
  private Long subOrderId;

  /**
   * 发货物流信息
   */
  private String logisticsInfo;


}
