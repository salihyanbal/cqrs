package com.salih.cqrs.api;

import com.salih.cqrs.cqrs.query.ProductSearchQuery;
import com.salih.cqrs.cqrs.query.ProductSearchQueryHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ProductQueryController {

    private final ProductSearchQueryHandler queryHandler;

    public ProductQueryController(ProductSearchQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping("search")
    public List<ProductSearchQuery> search() throws IOException {
        return queryHandler.searchProduct();
    }
}
