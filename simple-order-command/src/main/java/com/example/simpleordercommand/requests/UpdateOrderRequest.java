package com.example.simpleordercommand.requests;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by kellen on 2017/9/13.
 */
@Data
@Accessors(chain = true)
public class UpdateOrderRequest implements Serializable {

  /**
   * 平台id
   */
  private Long appId;

  /**
   * 用户id
   */
  private Long userId;

  /**
   * 主订单id
   */
  private Long orderId;

  /**
   * 子订单状态
   */
  private Integer orderStatus;


  /**
   * 子订单id
   */
  private Long subOrderId;

  /**
   * 订单取消原因
   */
  private String cancelCause;

  private String logisticsInfo;

}
