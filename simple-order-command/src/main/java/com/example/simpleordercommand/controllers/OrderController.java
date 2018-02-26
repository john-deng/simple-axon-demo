package com.example.simpleordercommand.controllers;

import cn.vpclub.moses.core.enums.ReturnCodeEnum;
import cn.vpclub.moses.core.model.response.BackResponseUtil;
import cn.vpclub.moses.core.model.response.BaseResponse;
import cn.vpclub.moses.utils.common.IdWorker;
import cn.vpclub.moses.web.controller.AbstractController;
import com.example.commonapi.commands.order.CreateOrderCommand;
import com.example.commonapi.commands.order.NotifyPaymentCommand;
import com.example.commonapi.commands.order.OrderDeliverCommand;
import com.example.simpleordercommand.requests.CreateOrderRequest;
import com.example.simpleordercommand.requests.PaymentPaidCallback;
import com.example.simpleordercommand.requests.UpdateOrderRequest;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
@Slf4j
@AllArgsConstructor
public class OrderController extends AbstractController {

  private CommandGateway commandGateway;

  private QueryGateway queryGateway;

  @PostMapping(value = "/create")
  public BaseResponse create(@RequestBody CreateOrderRequest request,
      HttpServletRequest servletRequest) {

    return sendAndResponseData(request, "create", () -> {
      Long orderId = IdWorker.getId();
      CreateOrderCommand command = new CreateOrderCommand(orderId,
          request.getAppId(),
          request.getUsername(),
          request.getOrderProducts());

      commandGateway.send(command);

      return orderId;
    });
  }


  @PostMapping("/paid")
  public void callBack(@RequestBody PaymentPaidCallback request) {
    NotifyPaymentCommand command = new NotifyPaymentCommand(request.getOrderId(),
        request.getTransactionNo(), request.getPayType());
    commandGateway.sendAndWait(command);
  }

  @PostMapping("/delivery")
  public BaseResponse delivery(@RequestBody UpdateOrderRequest request) {
    String logisticsInfo = request.getLogisticsInfo();
    OrderDeliverCommand command = new OrderDeliverCommand(request.getOrderId(),
        request.getSubOrderId(), logisticsInfo);
    commandGateway.sendAndWait(command);
    return BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
  }

  @GetMapping(value = "query/{orderId}")
  public BaseResponse query(@PathVariable String orderId)
      throws ExecutionException, InterruptedException {
    CompletableFuture future = queryGateway.send(orderId, BaseResponse.class);
    return (BaseResponse) future.get();
  }


}
