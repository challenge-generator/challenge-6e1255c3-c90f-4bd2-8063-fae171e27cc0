package com.bank.account.domain.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private final UUID id;

    @NotNull(message = "El ID de la cuenta origen no puede ser nulo")
    private final UUID accountId;

    @NotNull(message = "El tipo de transacción no puede ser nulo")
    private final TransactionType type;

    @NotNull(message = "El monto no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El monto debe ser positivo")
    private final BigDecimal amount;

    @NotNull(message = "La descripción no puede ser nula")
    @Size(max = 200, message = "La descripción no puede exceder 200 caracteres")
    private final String description;

    @NotNull(message = "La fecha de la transacción no puede ser nula")
    private final LocalDateTime transactionDate;

    private final UUID relatedAccountId;

    public Transaction(UUID id, UUID accountId, TransactionType type, BigDecimal amount, String description) {
        this(id, accountId, type, amount, description, null);
    }

    public Transaction(UUID id, UUID accountId, TransactionType type, BigDecimal amount, String description, UUID relatedAccountId) {
        if (id == null) {
            throw new IllegalArgumentException("El ID de la transacción no puede ser nulo");
        }
        if (accountId == null) {
            throw new IllegalArgumentException("El ID de la cuenta no puede ser nulo");
        }
        if (type == null) {
            throw new IllegalArgumentException("El tipo de transacción no puede ser nulo");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        if (description == null || description.length() > 200) {
            throw new IllegalArgumentException("Descripción inválida");
        }

        this.id = id;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.transactionDate = LocalDateTime.now();
        this.relatedAccountId = relatedAccountId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public UUID getRelatedAccountId() {
        return relatedAccountId;
    }

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER_IN, TRANSFER_OUT
    }
}