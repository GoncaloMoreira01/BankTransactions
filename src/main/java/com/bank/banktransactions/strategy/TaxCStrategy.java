package com.bank.banktransactions.strategy;

import com.bank.banktransactions.interfaces.IFee;

public class TaxCStrategy implements IFee {
    @Override
    public boolean isDaysAndValueCorrectToFee(double value, long daysBetweenTodayAndSchedule) {
        return value > 2000 && daysBetweenTodayAndSchedule >= 11;
    }

    @Override
    public Double calculate(double value, long daysBetweenTodayAndSchedule) {
        if (daysBetweenTodayAndSchedule <= 20) return value * 0.082;
        if (daysBetweenTodayAndSchedule <= 30) return value * 0.069;
        if (daysBetweenTodayAndSchedule <= 40) return value * 0.047;
        return value * 0.017;
    }
}
