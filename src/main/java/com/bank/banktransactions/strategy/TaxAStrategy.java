package com.bank.banktransactions.strategy;

import com.bank.banktransactions.interfaces.IFee;

public class TaxAStrategy implements IFee {
    @Override
    public boolean isDaysAndValueCorrectToFee(double value, long daysBetweenTodayAndSchedule) {
        return value <= 1000 && daysBetweenTodayAndSchedule == 0;
    }

    @Override
    public Double calculate(double value, long daysBetweenTodayAndSchedule) {
        return value * 0.03 + 3;
    }
}
