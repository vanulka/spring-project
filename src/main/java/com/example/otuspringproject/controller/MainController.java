package com.example.otuspringproject.controller;

import com.example.otuspringproject.model.Product;
import com.example.otuspringproject.repository.ProductRepository;
import com.example.otuspringproject.service.ReceiptGenerator;
import com.example.otuspringproject.service.ShoppingCart;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final ShoppingCart shoppingCart;
    private final ProductRepository productRepository;
    private final ObjectProvider<ReceiptGenerator> receiptGeneratorProvider;

    public MainController(ShoppingCart shoppingCart,
                          ProductRepository productRepository,
                          ObjectProvider<ReceiptGenerator> receiptGeneratorProvider) {
        this.shoppingCart = shoppingCart;
        this.productRepository = productRepository;
        this.receiptGeneratorProvider = receiptGeneratorProvider;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("cartItems", shoppingCart.getItems());
        model.addAttribute("cartTotal", shoppingCart.getTotalPrice());
        return "index";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        shoppingCart.addItem(product);
        return "redirect:/";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam Long productId) {
        shoppingCart.removeItem(productId);
        return "redirect:/";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        if (shoppingCart.getItems().isEmpty()) {
            return "redirect:/";
        }

        ReceiptGenerator newReceipt = receiptGeneratorProvider.getObject();

        String receiptId = newReceipt.getReceiptId();
        double total = shoppingCart.getTotalPrice();

        shoppingCart.clear();

        model.addAttribute("receiptId", receiptId);
        model.addAttribute("total", total);
        return "receipt";
    }
}