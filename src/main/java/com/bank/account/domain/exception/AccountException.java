package com.bank.account.domain.exception;

public class AccountException extends RuntimeException {
    private final String errorCode;
    private final String accountId;

    public AccountException(String message) {
        super(message);
        this.errorCode = "ACCOUNT_ERROR";
        this.accountId = null;
    }

    public AccountException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.accountId = null;
    }

    public AccountException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "ACCOUNT_ERROR";
        this.accountId = null;
    }

    public AccountException(String message, String errorCode, String accountId) {
        super(message);
        this.errorCode = errorCode;
        this.accountId = accountId;
    }

    public AccountException(String message, Throwable cause, String errorCode, String accountId) {
        super(message, cause);
        this.errorCode = errorCode;
        this.accountId = accountId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getAccountId() {
        return accountId;
    }

    public static AccountException accountNotFound(String accountId) {
        return new AccountException(
            "Cuenta no encontrada con ID: " + accountId,
            "ACCOUNT_NOT_FOUND",
            accountId
        );
    }

    public static AccountException invalidAccountStatus(String currentStatus, String requestedOperation) {
        return new AccountException(
            "No se puede realizar la operación '" + requestedOperation + 
            "' en una cuenta con estado: " + currentStatus,
            "INVALID_ACCOUNT_STATUS",
            null
        );
    }

    public static AccountException accountAlreadyExists(String accountNumber) {
        return new AccountException(
            "Ya existe una cuenta con el número: " + accountNumber,
            "ACCOUNT_ALREADY_EXISTS",
            null
        );
    }

    public static AccountException invalidOperation(String message) {
        return new AccountException(message, "INVALID_OPERATION", null);
    }
}