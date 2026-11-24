package com.example.otuspringproject.shell;

import com.example.otuspringproject.model.Product;
import com.example.otuspringproject.repository.ProductRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class ProductShellCommands {

    private final ProductRepository productRepository;

    public ProductShellCommands(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @ShellMethod(key = "list-products", value = "Вывести список всех товаров в консоль")
    public String listProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return "В базе данных нет товаров.";
        }

        StringBuilder sb = new StringBuilder("=== СПИСОК ТОВАРОВ ===\n");
        for (Product p : products) {
            sb.append(String.format("ID: %d | %s | Цена: %.2f руб.\n", p.getId(), p.getName(), p.getPrice()));
        }
        return sb.toString();
    }
}