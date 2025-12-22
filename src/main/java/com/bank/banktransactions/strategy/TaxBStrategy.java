package com.bank.banktransactions.strategy;

import com.bank.banktransactions.interfaces.IFee;

public class TaxBStrategy implements IFee {

    public boolean isDaysAndValueCorrectToFee(double value, long daysBetweenTodayAndSchedule) {
        return value >= 1001 && value <= 2000 && daysBetweenTodayAndSchedule >= 1 && daysBetweenTodayAndSchedule <= 10;
    }

    @Override
    public Double calculate(double value, long daysBetweenTodayAndSchedule) {
        return value * 0.09;
    }
}
