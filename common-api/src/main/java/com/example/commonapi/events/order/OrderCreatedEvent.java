package com.example.commonapi.events.order;

import com.example.commonapi.domain.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderCreatedEvent {

  private Long orderId;

  private List<OrderProduct> products;
  /**
   * 用户id
   */
  private String username;

  private Long appId;

  private String postIp;

  private Long paymentAmount;

  private Integer mainStatus;

  private String mainOrderNo;

  public OrderCreatedEvent(Long orderId,
                           List<OrderProduct> products, String username, Long appId, String postIp,
                           String mainOrderNo) {
    this.orderId = orderId;
    this.products = products;
    this.username = username;
    this.appId = appId;
    this.postIp = postIp;
    this.mainOrderNo = mainOrderNo;
  }
}
