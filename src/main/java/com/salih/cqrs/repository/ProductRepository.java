package com.salih.cqrs.repository;

import com.salih.cqrs.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
