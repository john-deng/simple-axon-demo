package com.example.simpleproductcommand.requests;

import cn.vpclub.moses.utils.validator.AbstractGenericParameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreateProductRequest extends AbstractGenericParameter {
    private String name;
    private long price;
    private int stock;
}

