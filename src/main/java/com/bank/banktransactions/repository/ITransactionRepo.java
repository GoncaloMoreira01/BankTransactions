package com.bank.banktransactions.repository;

import com.bank.banktransactions.model.Transaction;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITransactionRepo extends JpaRepository<Transaction, Long> {

    @Lock(LockModeType.NONE)
    @Query(value = "SELECT t FROM Transaction t")
    List<Transaction> getTransactions();
}
