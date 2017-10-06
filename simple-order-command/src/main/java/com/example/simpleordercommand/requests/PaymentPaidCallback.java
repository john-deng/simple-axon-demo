package com.example.simpleordercommand.requests;


import cn.vpclub.moses.utils.validator.BaseAbstractParameter;
import cn.vpclub.moses.utils.validator.annotations.NotNull;
import lombok.Data;

@Data
public class PaymentPaidCallback extends BaseAbstractParameter {
    @NotNull(when = {"callBack"})
    private Long orderId;
    @NotNull(when = {"callBack"})
    private String transactionNo;
    @NotNull(when = {"callBack"})
    private Integer payType;
}
