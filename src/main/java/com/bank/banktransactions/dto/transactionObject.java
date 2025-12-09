package com.bank.banktransactions.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class transactionObject {
    private double value;
    private LocalDate date;
}
