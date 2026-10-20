package com.bank.banktransactions.strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FeeStrategyCoverageTest {

    @Test
    void appliesTaxAToAnyValueScheduledForToday() {
        assertTrue(new TaxAStrategy().isDaysAndValueCorrectToFee(5_000, 0));
    }

    @Test
    void appliesTaxBToAnyValueScheduledWithinTenDays() {
        assertTrue(new TaxBStrategy().isDaysAndValueCorrectToFee(500, 1));
        assertTrue(new TaxBStrategy().isDaysAndValueCorrectToFee(5_000, 10));
    }

    @Test
    void appliesTaxCToAnyValueScheduledAfterTenDays() {
        assertTrue(new TaxCStrategy().isDaysAndValueCorrectToFee(500, 11));
        assertTrue(new TaxCStrategy().isDaysAndValueCorrectToFee(5_000, 41));
    }

    @Test
    void doesNotApplyFeesToPastDates() {
        assertFalse(new TaxAStrategy().isDaysAndValueCorrectToFee(500, -1));
        assertFalse(new TaxBStrategy().isDaysAndValueCorrectToFee(500, -1));
        assertFalse(new TaxCStrategy().isDaysAndValueCorrectToFee(500, -1));
    }
}
