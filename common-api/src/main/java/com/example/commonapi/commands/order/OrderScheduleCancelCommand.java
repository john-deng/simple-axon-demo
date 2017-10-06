package com.example.commonapi.commands.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.io.Serializable;

/**
 * Created by kellen on 2017/9/20.
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderScheduleCancelCommand implements Serializable {

  @TargetAggregateIdentifier
  private Long orderId;
}
