package com.salih.cqrs.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.salih.cqrs.cqrs.command.CreateProductCommand;
import com.salih.cqrs.cqrs.command.CreateProductCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class ProductCommandController {

    private final CreateProductCommandHandler commandHandler;

    public ProductCommandController(CreateProductCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping("create/product")
    public void createProduct(@RequestBody CreateProductCommand request) throws JsonProcessingException {
        commandHandler.createProductHandler(request);
    }
}
