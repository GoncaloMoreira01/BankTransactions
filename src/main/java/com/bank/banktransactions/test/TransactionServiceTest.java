package com.bank.banktransactions.test;

import com.bank.banktransactions.dto.TransactionObject;
import com.bank.banktransactions.model.Transaction;
import com.bank.banktransactions.repository.ITransactionRepo;
import com.bank.banktransactions.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {
    @Mock
    private ITransactionRepo transactionRepo;
    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void createTransactionWithCorrectFee_A() {
        LocalDate today = LocalDate.now();
        TransactionObject transactionObject = new TransactionObject();
        transactionObject.setValue(500.0);
        transactionObject.setDate(today);

        when(transactionRepo.save(any(Transaction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Transaction result = transactionService.create(transactionObject);
        assertThat(result.getValue(), equalTo(518.0));
    }

    @Test
    public void updateTransactionWithCorrectFee_B() {
        Transaction existing = new Transaction();
        existing.setId(1L);
        existing.setValue(100.0);
        existing.setScheduleDate(LocalDate.now());

        when(transactionRepo.findById(1L))
                .thenReturn(Optional.of(existing));

        when(transactionRepo.save(any(Transaction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        TransactionObject updateDto = new TransactionObject();
        updateDto.setId(1L);
        updateDto.setValue(1010);
        updateDto.setDate(LocalDate.now().plusDays(8));


        double expectedFinalValue = updateDto.getValue() + (updateDto.getValue() * 0.09);
        Transaction result = transactionService.update(updateDto);
        assertThat(result.getValue(), is(expectedFinalValue));
        }
}
