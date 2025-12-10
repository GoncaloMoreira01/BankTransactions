package com.bank.banktransactions.controller;

import com.bank.banktransactions.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/getList")
    public void getTransactions() {
    }

    @PostMapping("/create")
    public void createTransaction() {
    }

    @PutMapping("/update")
    public void updateTransaction() {
    }

    @DeleteMapping("/delete")
    public void deleteTransaction() {
    }
}
