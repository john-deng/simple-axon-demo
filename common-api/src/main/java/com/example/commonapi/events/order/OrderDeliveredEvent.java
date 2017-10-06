package com.example.commonapi.events.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by kellen on 2017/9/15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDeliveredEvent {

  /**
   * 主订单id
   */
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
