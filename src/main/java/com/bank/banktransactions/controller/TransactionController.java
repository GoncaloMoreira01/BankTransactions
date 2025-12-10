package com.bank.banktransactions.controller;

import com.bank.banktransactions.dto.TransactionObject;
import com.bank.banktransactions.model.Transaction;
import com.bank.banktransactions.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionService transactionService;


    @GetMapping("/getList")
    public List<Transaction> getTransactions() {
        try {
            return transactionService.getList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/create")
    public Transaction createTransaction(@RequestBody TransactionObject transaction) {
        try {
            return transactionService.create(transaction);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/update")
    public Transaction updateTransaction(@RequestBody TransactionObject transaction) {
        try {
            return transactionService.update(transaction);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete")
    public void deleteTransaction(Long id) {
        try {
            transactionService.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
