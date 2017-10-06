package com.example.commonapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Getter
@Setter
@AllArgsConstructor
public class OrderProduct {
    @TargetAggregateIdentifier
    private Long id;
    private String name;
    private long price;
    private int quantity;
    private long amount;
    private boolean reserved;
}