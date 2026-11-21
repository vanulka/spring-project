package com.example.otuspringproject.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope("prototype")
public class ReceiptGenerator {

    private final String receiptId = UUID.randomUUID().toString();

    public String getReceiptId() {
        return receiptId;
    }
}