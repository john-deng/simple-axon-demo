package com.example.simpleproductcommand.handlers;

import com.example.commonapi.commands.product.CreateProductCommand;
import com.example.commonapi.commands.product.ReserveProductCommand;
import com.example.commonapi.commands.product.RollbackReservationCommand;
import com.example.simpleproductcommand.aggregates.ProductAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ProductCommondHandler {


  @Autowired
  private Repository<ProductAggregate> repository;

  @CommandHandler
  public void on(CreateProductCommand command) throws Exception {
    log.info("CreateProductCommand,{}", command);
    repository.newInstance(
        () -> new ProductAggregate(command.getId(), command.getName(), command.getStock(),
            command.getPrice()));
  }

  @CommandHandler
  public void on(ReserveProductCommand command) {
    log.info("ReserveProductCommand,{}", command);
    Aggregate<ProductAggregate> aggregate = repository.load(command.getSkuId().toString());
    aggregate
        .execute(aggregateRoot -> aggregateRoot.reserve(command.getOrderId(), command.getQuantity()));
  }

  @CommandHandler
  public void on(RollbackReservationCommand command) {
    log.info("RollbackReservationCommand,{}", command);
    Aggregate<ProductAggregate> aggregate = repository.load(command.getSkuId().toString());
    aggregate.execute(
        aggregateRoot -> aggregateRoot.cancellReserve(command.getOrderId(), command.getNumber()));
  }
}
