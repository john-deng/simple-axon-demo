package com.example.simpleordercommand.handlers;

import com.example.commonapi.commands.order.ConfirmOrderCommand;
import com.example.commonapi.commands.order.CreateOrderCommand;
import com.example.commonapi.commands.order.OrderDeliverCommand;
import com.example.commonapi.commands.order.PaymentPaidCommand;
import com.example.commonapi.events.order.OrderAutoCancelledEvent;
import com.example.commonapi.events.order.OrderAutoReceivedEvent;
import com.example.simpleordercommand.aggregates.OrderAggregate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.scheduling.ScheduleToken;
import org.axonframework.eventhandling.scheduling.quartz.QuartzEventScheduler;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Created by kellen on 2017/9/12.
 */
@Component
@Slf4j
@AllArgsConstructor
public class OrderCommondHandler {

  private Repository<OrderAggregate> repository;

  private QuartzEventScheduler quartzEventScheduler;

  @CommandHandler
  public void handle(CreateOrderCommand command) throws Exception {
    repository.newInstance(
        () -> new OrderAggregate(command.getOrderId(), command.getUsername(),
            command.getOrderProducts(), command.getAppId(), "",
            ""));
  }

  @CommandHandler
  public void handle(ConfirmOrderCommand command) {
    Aggregate<OrderAggregate> aggregate = repository
            .load(command.getOrderId().toString());
    Instant closeTime = Instant.now().plus(1, ChronoUnit.DAYS);
    ScheduleToken closeScheduleToken = quartzEventScheduler
            .schedule(closeTime, new OrderAutoCancelledEvent(command.getOrderId(), "Payment timeout in 24 hours"));
    aggregate.execute(aggregateRoot -> aggregateRoot.confirm(closeScheduleToken));
  }

  @CommandHandler
  public void handle(PaymentPaidCommand command) {
    Aggregate<OrderAggregate> aggregate = repository.load(command.getOrderId().toString());
    aggregate.execute(aggregateRoot -> aggregateRoot.pay(command.getTransactionNo(), command.getPayType(), quartzEventScheduler));
  }

  @CommandHandler
  public void handle(OrderDeliverCommand command) {
    Aggregate<OrderAggregate> aggregate = repository.load(command.getOrderId().toString());
    Instant autoReceiveTime = Instant.now().plus(30, ChronoUnit.SECONDS);
    quartzEventScheduler.schedule(autoReceiveTime,
            new OrderAutoReceivedEvent(command.getOrderId(), command.getSubOrderId()));
    aggregate.execute(aggregateRoot -> aggregateRoot
            .delivery(command.getSubOrderId(), command.getLogisticsInfo()));
  }
}
