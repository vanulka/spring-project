package com.example.otuspringproject.service;

import com.example.otuspringproject.model.Product;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {

    private List<Product> items = new ArrayList<>();

    @PostConstruct
    public void init() {
        System.out.println("ShoppingCart создан (начало сессии)");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("ShoppingCart уничтожен (конец сессии). Товаров в корзине было: " + items.size());
    }

    public void addItem(Product product) {
        items.add(product);
    }

    public void clear() {
        items.clear();
    }

    public List<Product> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(Product::getPrice).sum();
    }
}