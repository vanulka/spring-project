package com.example.otuspringproject.service;

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

    private List<String> items = new ArrayList<>();

    @PostConstruct
    public void init() {
        System.out.println("ShoppingCart создан (начало сессии)");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("ShoppingCart уничтожен (конец сессии). Товаров в корзине было: " + items.size());
    }

    public void addItem(String item) {
        items.add(item);
    }

    public List<String> getItems() {
        return items;
    }
}