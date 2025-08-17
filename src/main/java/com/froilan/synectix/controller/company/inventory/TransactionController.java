package com.froilan.synectix.controller.company.inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.froilan.synectix.model.dto.request.company.InventoryTransactionCreateBody;


@RestController
@RequestMapping("/api/company/inventory/transaction")
public class TransactionController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public String createTransaction(@Valid @RequestBody InventoryTransactionCreateBody transactionCreateBody) {
        logger.info("Creating a new transaction");
        // Logic to create a new transaction
        return "Transaction created successfully";
    }
}
