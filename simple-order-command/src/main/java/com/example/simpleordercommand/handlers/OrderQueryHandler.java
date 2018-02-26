package com.example.simpleordercommand.handlers;

import cn.vpclub.moses.core.enums.ReturnCodeEnum;
import cn.vpclub.moses.core.model.response.BackResponseUtil;
import cn.vpclub.moses.core.model.response.BaseResponse;
import com.example.simpleordercommand.aggregates.OrderAggregate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

/**
 * Created by kellen on 2018/2/26.
 * query bus demo
 */
@Component
@Slf4j
@AllArgsConstructor
public class OrderQueryHandler {

  private Repository<OrderAggregate> repository;

  /**
   * a simple query handler
   */
  @QueryHandler
  public BaseResponse query(String orderId) {
    log.info("query orderId:{}", orderId);
    Aggregate<OrderAggregate> orderAggregate = repository.load(orderId);
    String buyerName = orderAggregate.invoke(OrderAggregate::getUsername);
    BaseResponse baseResponse = BackResponseUtil
        .getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
    baseResponse.setDataInfo(buyerName);
    return baseResponse;
  }

}
