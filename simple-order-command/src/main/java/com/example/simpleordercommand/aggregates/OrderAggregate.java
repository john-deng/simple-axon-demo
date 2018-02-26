package com.example.simpleordercommand.aggregates;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.axonframework.commandhandling.model.AggregateLifecycle.markDeleted;

import com.example.commonapi.domain.OrderProduct;
import com.example.commonapi.events.order.OrderCancelledEvent;
import com.example.commonapi.events.order.OrderConfirmReceiptEvent;
import com.example.commonapi.events.order.OrderConfirmedEvent;
import com.example.commonapi.events.order.OrderCreatedEvent;
import com.example.commonapi.events.order.OrderDeliveredEvent;
import com.example.commonapi.events.pay.OrderPaidEvent;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.scheduling.ScheduleToken;
import org.axonframework.eventhandling.scheduling.quartz.QuartzEventScheduler;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * Created by kellen on 2017/9/12.
 */
@NoArgsConstructor
@ToString
@Slf4j
@Aggregate
@Getter
public class OrderAggregate {

  @AggregateIdentifier
  private Long orderId;

  /**
   * 主订单编号
   */
  private String mainOrderNo;

  /**
   * 主订单状态
   */
  private Integer mainStatus;

  /**
   * 支付流水号
   */
  private String serialCode;

  /**
   * 支付时间
   */
  private Long payTime;
  /**
   * 用户信息
   */
  private String username;
  /**
   * 待付款金额
   */
  private Long paymentAmount;

  @AggregateMember
  private List<OrderProduct> orderLine;

  private Long appId;

  private String postIp;

  private Long postTime;

  private ScheduleToken closeScheduleToken;

  public OrderAggregate(Long id, String username, List<OrderProduct> orderLine, Long appId,
      String postIp, String mainOrderNo) {
    apply(new OrderCreatedEvent(id, orderLine, username, appId, postIp, mainOrderNo));
  }


  @EventHandler
  public void on(OrderCreatedEvent event) {
    this.appId = event.getAppId();
    this.orderId = event.getOrderId();
    this.username = event.getUsername();
    this.orderLine = event.getProducts();
    this.postIp = event.getPostIp();
    this.mainOrderNo = event.getMainOrderNo();
    computePrice();
    event.setPaymentAmount(paymentAmount);
    event.setMainStatus(mainStatus);
  }

  public void confirm(ScheduleToken token) {
    apply(new OrderConfirmedEvent(orderId, token));
  }

  @EventHandler
  public void on(OrderConfirmedEvent event) {
    log.info("on OrderConfirmedEvent");
    this.closeScheduleToken = event.getCloseScheduleToken();
    this.postTime = System.currentTimeMillis();
  }

  public void delete(String cancelCause) {
    markDeleted();
    apply(new OrderCancelledEvent(orderId, cancelCause));
  }

  private void computePrice() {
    paymentAmount = orderLine.stream().mapToLong(OrderProduct::getAmount).sum();
  }

  public void pay(String transactionNo, Integer payType, QuartzEventScheduler eventScheduler) {
    apply(new OrderPaidEvent(orderId, transactionNo, payType));
  }

  @EventHandler
  public void on(OrderPaidEvent event) {
    this.payTime = System.currentTimeMillis();
    this.serialCode = event.getTransactionNo();
  }

  public void confirmReceipt(Long subOrderId) {
    apply(new OrderConfirmReceiptEvent(orderId, subOrderId));
  }

  public void delivery(Long subOrderId, String logisticsInfo) {
    apply(new OrderDeliveredEvent(orderId, subOrderId, logisticsInfo));
  }
}
