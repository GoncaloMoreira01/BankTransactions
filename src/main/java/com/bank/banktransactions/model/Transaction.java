package com.bank.banktransactions.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private Double value;

    @Column(name = "schedule_date")
    private LocalDate scheduleDate;

    public Transaction(double value, LocalDate scheduleDate) {
        this.value = value;
        this.scheduleDate = scheduleDate;
    }

    public Transaction() {

    }
}
