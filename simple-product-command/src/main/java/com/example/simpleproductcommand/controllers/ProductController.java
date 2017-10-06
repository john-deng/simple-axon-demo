package com.example.simpleproductcommand.controllers;

import cn.vpclub.moses.core.model.response.BaseResponse;
import cn.vpclub.moses.utils.common.IdWorker;
import cn.vpclub.moses.utils.constant.ValidatorConditionType;
import cn.vpclub.moses.web.controller.AbstractController;
import com.example.commonapi.commands.product.CreateProductCommand;
import com.example.simpleproductcommand.requests.CreateProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController extends AbstractController {

    private CommandGateway commandGateway;

    public ProductController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public BaseResponse create(@RequestBody CreateProductRequest request) {
        Long productId = IdWorker.getId();
        CreateProductCommand command = new CreateProductCommand(productId, request.getName(),
                request.getPrice(), request.getStock());
        log.debug("Adding ProductRequest [{}] '{}' {}x{}", productId, request.getName(),
                request.getPrice(), request.getStock());
        return sendAndResponseData(command, ValidatorConditionType.CREATE, () -> {
            commandGateway.sendAndWait(command);
            return productId;
        });
    }
}
