package com.example.otuspringproject.controller;

import com.example.otuspringproject.service.ReceiptGenerator;
import com.example.otuspringproject.service.ShoppingCart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final ShoppingCart shoppingCart; // Session scope
    private final ReceiptGenerator receiptGenerator; // Prototype scope

    public MainController(ShoppingCart shoppingCart, ReceiptGenerator receiptGenerator) {
        this.shoppingCart = shoppingCart;
        this.receiptGenerator = receiptGenerator;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("cartItems", shoppingCart.getItems());
        model.addAttribute("receiptId", receiptGenerator.getReceiptId());
        return "index";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam String itemName) {
        shoppingCart.addItem(itemName);
        return "redirect:/";
    }
}