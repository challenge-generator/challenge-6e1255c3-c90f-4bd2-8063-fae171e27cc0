package com.bank.account.domain.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public class Account {
    private final UUID id;
    private final UUID clientId;

    @NotNull(message = "El número de cuenta no puede ser nulo")
    @Pattern(regexp = "^[A-Z]{2}\\d{10}$", message = "El número de cuenta debe seguir el formato AA1234567890")
    private final String accountNumber;

    @NotNull(message = "El tipo de cuenta no puede ser nulo")
    private final AccountType type;

    @NotNull(message = "El estado de la cuenta no puede ser nulo")
    private AccountStatus status;

    @NotNull(message = "El saldo no puede ser nulo")
    @DecimalMin(value = "0.00", message = "El saldo no puede ser negativo")
    private BigDecimal balance;

    @NotNull(message = "La fecha de creación no puede ser nula")
    private final LocalDateTime createdAt;

    @NotNull(message = "La fecha de modificación no puede ser nula")
    private LocalDateTime updatedAt;

    private final List<Transaction> transactions;

    public Account(UUID id, UUID clientId, String accountNumber, AccountType type, BigDecimal initialBalance) {
        if (id == null) {
            throw new IllegalArgumentException("El ID de la cuenta no puede ser nulo");
        }
        if (clientId == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }
        if (accountNumber == null || !accountNumber.matches("^[A-Z]{2}\\d{10}$")) {
            throw new IllegalArgumentException("Número de cuenta inválido");
        }
        if (type == null) {
            throw new IllegalArgumentException("El tipo de cuenta no puede ser nulo");
        }
        if (initialBalance == null || initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }

        this.id = id;
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.type = type;
        this.status = AccountStatus.ACTIVE;
        this.balance = initialBalance;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.transactions = new ArrayList<>();
    }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo");
        }
        if (this.status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("No se puede depositar en una cuenta no activa");
        }
        this.balance = this.balance.add(amount);
        this.updatedAt = LocalDateTime.now();
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo");
        }
        if (this.status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("No se puede retirar de una cuenta no activa");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Saldo insuficiente para realizar el retiro");
        }
        this.balance = this.balance.subtract(amount);
        this.updatedAt = LocalDateTime.now();
    }

    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("La transacción no puede ser nula");
        }
        this.transactions.add(transaction);
    }

    public UUID getId() {
        return id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountType getType() {
        return type;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public void setStatus(AccountStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("El estado de la cuenta no puede ser nulo");
        }
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public enum AccountType {
        SAVINGS, CHECKING, FIXED_TERM
    }

    public enum AccountStatus {
        ACTIVE, BLOCKED, CLOSED
    }
}