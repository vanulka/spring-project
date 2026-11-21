package com.example.otuspringproject.repository;

import com.example.otuspringproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}