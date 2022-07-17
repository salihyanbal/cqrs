package com.salih.cqrs.cqrs.query;

import com.salih.cqrs.elastic.ElasticListener;
import com.salih.cqrs.elastic.ElasticSearchService;
import com.salih.cqrs.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.salih.cqrs.elastic.ElasticListener.INDEX_NAME;


@Service
public class ProductSearchQueryHandler {

    private final ElasticSearchService elasticSearchService;

    @Autowired
    public ProductSearchQueryHandler(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }

    public List<ProductSearchQuery> searchProduct() throws IOException {
        return elasticSearchService.search(INDEX_NAME, Product.class)
                .stream()
                .map(response -> new ProductSearchQuery(response.hits().getId(), response.hits().getName()))
                .collect(Collectors.toList());
    }
}
