package com.bank.account.domain.exception;

import java.math.BigDecimal;

public class InsufficientFundsException extends AccountException {
    private final BigDecimal currentBalance;
    private final BigDecimal requestedAmount;
    private final String accountNumber;

    public InsufficientFundsException(BigDecimal currentBalance, BigDecimal requestedAmount) {
        super(
            String.format(
                "Fondos insuficientes. Saldo actual: %.2f, Monto solicitado: %.2f",
                currentBalance,
                requestedAmount
            ),
            "INSUFFICIENT_FUNDS",
            null
        );
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
        this.accountNumber = null;
    }

    public InsufficientFundsException(BigDecimal currentBalance, BigDecimal requestedAmount, String accountNumber) {
        super(
            String.format(
                "Fondos insuficientes en cuenta %s. Saldo actual: %.2f, Monto solicitado: %.2f",
                accountNumber,
                currentBalance,
                requestedAmount
            ),
            "INSUFFICIENT_FUNDS",
            accountNumber
        );
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
        this.accountNumber = accountNumber;
    }

    public InsufficientFundsException(String message, BigDecimal currentBalance, BigDecimal requestedAmount) {
        super(message, "INSUFFICIENT_FUNDS", null);
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
        this.accountNumber = null;
    }

    public InsufficientFundsException(String message, BigDecimal currentBalance, BigDecimal requestedAmount, Throwable cause) {
        super(message, cause, "INSUFFICIENT_FUNDS", null);
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
        this.accountNumber = null;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getDeficit() {
        if (currentBalance == null || requestedAmount == null) {
            return BigDecimal.ZERO;
        }
        return requestedAmount.subtract(currentBalance);
    }

    public boolean canCoverAmount(BigDecimal amount) {
        if (currentBalance == null || amount == null) {
            return false;
        }
        return currentBalance.compareTo(amount) >= 0;
    }

    public static InsufficientFundsException create(BigDecimal currentBalance, BigDecimal requestedAmount) {
        return new InsufficientFundsException(currentBalance, requestedAmount);
    }

    public static InsufficientFundsException create(BigDecimal currentBalance, BigDecimal requestedAmount, String accountNumber) {
        return new InsufficientFundsException(currentBalance, requestedAmount, accountNumber);
    }
}