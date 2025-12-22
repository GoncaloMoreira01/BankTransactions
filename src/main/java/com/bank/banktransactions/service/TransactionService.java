package com.bank.banktransactions.service;

import com.bank.banktransactions.dto.TransactionObject;
import com.bank.banktransactions.interfaces.IFee;
import com.bank.banktransactions.model.Transaction;
import com.bank.banktransactions.repository.ITransactionRepo;
import com.bank.banktransactions.strategy.TaxAStrategy;
import com.bank.banktransactions.strategy.TaxBStrategy;
import com.bank.banktransactions.strategy.TaxCStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
@AllArgsConstructor
public class TransactionService {

    private final ITransactionRepo iTransactionRepo;

    public List<Transaction> getList() {
        return iTransactionRepo.getTransactions();
    }

    public Transaction create(TransactionObject transactionDto) {
        double fee = calculateFee(transactionDto.getValue(), transactionDto.getDate());
        Transaction transaction = new Transaction(transactionDto.getValue() + fee, transactionDto.getDate());
        return iTransactionRepo.save(transaction);
    }

    public Transaction update(TransactionObject transactionDto) {
        Transaction transaction = iTransactionRepo.findById(transactionDto.getId()).orElseThrow(() -> new RuntimeException("Transaction not found"));
        double fee = calculateFee(transactionDto.getValue(), transactionDto.getDate());

        transaction.setValue(transactionDto.getValue() + fee);
        transaction.setScheduleDate(transactionDto.getDate());

        return iTransactionRepo.save(transaction);
    }

    public void delete(Long id) {
        if (!iTransactionRepo.existsById(id)) {
            throw new RuntimeException("Transaction not found");
        }
        iTransactionRepo.deleteById(id);
    }

    private double calculateFee(double value, LocalDate scheduleDate) {
        long daysBetweenTodayAndSchedule = ChronoUnit.DAYS.between(LocalDate.now(), scheduleDate);
        List<IFee> strategies = List.of(new TaxAStrategy(), new TaxBStrategy(), new TaxCStrategy());
        for (IFee strategy : strategies) {
            if (strategy.isDaysAndValueCorrectToFee(value, daysBetweenTodayAndSchedule)) {
                return strategy.calculate(value, daysBetweenTodayAndSchedule);
            }
        }

        throw new IllegalArgumentException("Transaction does not match fee rules");
    }
}
