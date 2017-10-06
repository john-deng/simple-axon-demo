package com.example.commonapi.commands.order;

import cn.vpclub.moses.utils.validator.AbstractGenericParameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by kellen on 2017/9/13.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderCommand extends AbstractGenericParameter {

  @TargetAggregateIdentifier
  private Long orderId;

  private Integer orderStatus;

  private Long subOrderId;
}
