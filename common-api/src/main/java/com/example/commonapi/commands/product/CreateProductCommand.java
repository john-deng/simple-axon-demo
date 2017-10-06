package com.example.commonapi.commands.product;

import cn.vpclub.moses.utils.validator.AbstractGenericParameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by kellen on 2017/9/5.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreateProductCommand extends AbstractGenericParameter {

  // here @TargetAggregateIdentifier annotation is optional because it's a construct command
  // but if using DistributeCommandBus, @TargetAggregateIdentifier must be set!
  @TargetAggregateIdentifier
  private Long id;
  private String name;
  private long price;
  private int stock;

}

