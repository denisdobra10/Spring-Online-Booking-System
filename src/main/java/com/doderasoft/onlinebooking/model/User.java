package com.doderasoft.onlinebooking.model;

import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.UUID;

public class User {

    private final UUID id;
    @NonNull
    private final String username;
    @NonNull
    private final String password;
    private final LocalDate dob;
    private float balance = 0.00f;

    public User(UUID id, String username, String password, LocalDate dob, float balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void addMoneyToBalance(float amount) {
        this.balance += amount;
    }

    public void deductMoneyFromBalance(float amount) {
        this.balance -= amount;
    }


}
