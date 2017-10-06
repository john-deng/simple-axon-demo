package com.example.commonapi.events.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.eventhandling.scheduling.ScheduleToken;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderConfirmedEvent {

  private Long id;

  private ScheduleToken closeScheduleToken;
}
