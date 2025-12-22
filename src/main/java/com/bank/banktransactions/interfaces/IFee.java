package com.bank.banktransactions.interfaces;

public interface IFee {
    boolean isDaysAndValueCorrectToFee(double value, long daysBetweenTodayAndSchedule);
    Double calculate(double value, long daysBetweenTodayAndSchedule);
}
