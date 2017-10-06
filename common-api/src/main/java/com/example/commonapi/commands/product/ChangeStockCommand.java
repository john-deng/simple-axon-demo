package com.example.commonapi.commands.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by kellen on 2017/9/5.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeStockCommand {

  @TargetAggregateIdentifier
  protected Long id;
  protected int number;

}
