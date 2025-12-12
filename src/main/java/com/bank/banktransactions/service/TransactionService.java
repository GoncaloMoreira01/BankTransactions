package com.bank.banktransactions.service;

import com.bank.banktransactions.dto.TransactionObject;
import com.bank.banktransactions.model.Transaction;
import com.bank.banktransactions.repository.ITransactionRepo;
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

        if (value <= 1000 && daysBetweenTodayAndSchedule == 0) {
            return value * 0.03 + 3;
        }
        if (value >= 1001 && value <= 2000 && daysBetweenTodayAndSchedule >= 1 && daysBetweenTodayAndSchedule <= 10) {
            return value * 0.09;
        }
        if (value > 2000) {
            if (daysBetweenTodayAndSchedule >= 11 && daysBetweenTodayAndSchedule <= 20) return value * 0.082;
            if (daysBetweenTodayAndSchedule >= 21 && daysBetweenTodayAndSchedule <= 30) return value * 0.069;
            if (daysBetweenTodayAndSchedule >= 31 && daysBetweenTodayAndSchedule <= 40) return value * 0.047;
            if (daysBetweenTodayAndSchedule > 40) return value * 0.017;
        }

        throw new IllegalArgumentException("Transaction does not match fee rules");
    }
}
