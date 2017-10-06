package com.example.simpleordercommand.requests;

import cn.vpclub.moses.utils.validator.AbstractGenericParameter;
import cn.vpclub.moses.utils.validator.annotations.NotNull;
import com.example.commonapi.domain.OrderProduct;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


@Data
@Accessors(chain = true)
public class CreateOrderRequest extends AbstractGenericParameter {

  @NotNull
  private Long appId;

  @NotNull
  private String username;

  @NotNull
  private List<OrderProduct> orderProducts;
}
