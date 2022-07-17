package com.salih.cqrs.elastic;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.salih.cqrs.model.Product;
import org.apache.logging.log4j.Logger;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public record ElasticListener(ElasticSearchService elasticsearchService,
        ObjectMapper objectMapper) {

private static byte RETRY_COUNT = 0;

private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(ElasticListener.class);

public static String INDEX_NAME = "product";

@KafkaListener(topics = "cqrs-events", groupId = "group_id")
public void listen(String event) {
                try {
                        Product product = objectMapper.readValue(event, Product.class);
                        elasticsearchService.createIndex(INDEX_NAME, product.getId(), product);
                }
                catch (Exception e) {
                        e.printStackTrace();
                        RETRY_COUNT++;
                        if (RETRY_COUNT <= 3) {
                                RETRY_COUNT = 4;
                         }
                        else {
                                LOGGER.info("Failed to save product");
                                e.printStackTrace();
                        }
                }
        }
}