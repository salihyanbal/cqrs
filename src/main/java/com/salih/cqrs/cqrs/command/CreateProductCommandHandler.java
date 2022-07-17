package com.salih.cqrs.cqrs.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salih.cqrs.model.Product;
import com.salih.cqrs.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CreateProductCommandHandler {

    private final ProductRepository productRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public CreateProductCommandHandler(ProductRepository productRepository, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.productRepository = productRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void createProductHandler(CreateProductCommand request) throws JsonProcessingException {
        Product product = objectMapper.convertValue(request, Product.class);
        productRepository.save(product);
        kafkaTemplate.send("cqrs-events", objectMapper.writeValueAsString(product))
                .addCallback(
                        (success) -> System.out.println("success"),
                        (error) -> System.out.println("error!")
                );
    }
}
