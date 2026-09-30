package com.bank.account.application.service;

import com.bank.account.domain.exception.InsufficientFundsException;
import com.bank.account.domain.model.Account;
import com.bank.account.domain.model.Account.AccountStatus;
import com.bank.account.domain.model.Account.AccountType;
import com.bank.account.domain.model.Transaction;
import com.bank.account.domain.model.Transaction.TransactionType;
import com.bank.account.domain.port.in.AccountManagementUseCase;
import com.bank.account.domain.port.in.CreateAccountUseCase;
import com.bank.account.domain.port.out.AccountRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccountService implements CreateAccountUseCase, AccountManagementUseCase {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(UUID clientId, String accountNumber, AccountType type, BigDecimal initialBalance) {
        if (clientId == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("El número de cuenta no puede estar vacío");
        }
        if (type == null) {
            throw new IllegalArgumentException("El tipo de cuenta no puede ser nulo");
        }
        if (initialBalance == null || initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }

        Optional<Account> existingAccount = accountRepository.findByAccountNumber(accountNumber);
        if (existingAccount.isPresent()) {
            throw new IllegalArgumentException(String.format("Ya existe una cuenta con el número: %s", accountNumber));
        }

        Account account = new Account(
            UUID.randomUUID(),
            clientId,
            accountNumber,
            type,
            initialBalance
        );

        if (initialBalance.compareTo(BigDecimal.ZERO) > 0) {
            Transaction initialTransaction = new Transaction(
                UUID.randomUUID(),
                account.getId(),
                TransactionType.DEPOSIT,
                initialBalance,
                "Depósito inicial de apertura"
            );
            account.addTransaction(initialTransaction);
        }

        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccount(UUID accountId) {
        if (accountId == null) {
            throw new IllegalArgumentException("El ID de la cuenta no puede ser nulo");
        }
        return accountRepository.findById(accountId);
    }

    @Override
    public Optional<Account> getAccountByNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("El número de cuenta no puede estar vacío");
        }
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public List<Account> getAccountsByClient(UUID clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }
        return accountRepository.findByClientId(clientId);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account deposit(UUID accountId, BigDecimal amount) {
        if (accountId == null) {
            throw new IllegalArgumentException("El ID de la cuenta no puede ser nulo");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del depósito debe ser mayor a cero");
        }

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        if (account.getStatus() != AccountStatus.ACTIVE) {
            throw new IllegalStateException("No se puede depositar en una cuenta que no está activa");
        }

        account.deposit(amount);

        Transaction transaction = new Transaction(
            UUID.randomUUID(),
            accountId,
            TransactionType.DEPOSIT,
            amount,
            "Depósito en cuenta"
        );
        account.addTransaction(transaction);

        return accountRepository.save(account);
    }

    @Override
    public Account withdraw(UUID accountId, BigDecimal amount) {
        if (accountId == null) {
            throw new IllegalArgumentException("El ID de la cuenta no puede ser nulo");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del retiro debe ser mayor a cero");
        }

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        if (account.getStatus() != AccountStatus.ACTIVE) {
            throw new IllegalStateException("No se puede retirar de una cuenta que no está activa");
        }

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException(
                account.getBalance(),
                amount,
                String.format("Saldo insuficiente para realizar el retiro. Saldo actual: %s, monto solicitado: %s", 
                    account.getBalance(), amount)
            );
        }

        account.withdraw(amount);

        Transaction transaction = new Transaction(
            UUID.randomUUID(),
            accountId,
            TransactionType.WITHDRAW,
            amount,
            "Retiro de cuenta"
        );
        account.addTransaction(transaction);

        return accountRepository.save(account);
    }

    @Override
    public Account transfer(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {
        if (fromAccountId == null || toAccountId == null) {
            throw new IllegalArgumentException("Los IDs de cuenta no pueden ser nulos");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto de la transferencia debe ser mayor a cero");
        }
        if (fromAccountId.equals(toAccountId)) {
            throw new IllegalArgumentException("No se puede transferir a la misma cuenta");
        }

        Account fromAccount = accountRepository.findById(fromAccountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta de origen no encontrada"));
        Account toAccount = accountRepository.findById(toAccountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta de destino no encontrada"));

        if (fromAccount.getStatus() != AccountStatus.ACTIVE || toAccount.getStatus() != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Ambas cuentas deben estar activas para realizar la transferencia");
        }

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException(
                fromAccount.getBalance(),
                amount,
                "Saldo insuficiente para realizar la transferencia"
            );
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        Transaction debitTransaction = new Transaction(
            UUID.randomUUID(),
            fromAccountId,
            TransactionType.TRANSFER,
            amount,
            "Transferencia enviada",
            toAccountId
        );
        fromAccount.addTransaction(debitTransaction);

        Transaction creditTransaction = new Transaction(
            UUID.randomUUID(),
            toAccountId,
            TransactionType.TRANSFER,
            amount,
            "Transferencia recibida",
            fromAccountId
        );
        toAccount.addTransaction(creditTransaction);

        accountRepository.save(fromAccount);
        return accountRepository.save(toAccount);
    }

    @Override
    public Account updateAccountStatus(UUID accountId, AccountStatus newStatus) {
        if (accountId == null) {
            throw new IllegalArgumentException("El ID de la cuenta no puede ser nulo");
        }
        if (newStatus == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo");
        }

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        account.setStatus(newStatus);
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(UUID accountId) {
        if (accountId == null) {
            throw new IllegalArgumentException("El ID de la cuenta no puede ser nulo");
        }

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        if (account.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalStateException("No se puede eliminar una cuenta con saldo diferente a cero");
        }

        accountRepository.delete(accountId);
    }

    @Override
    public BigDecimal getAccountBalance(UUID accountId) {
        if (accountId == null) {
            throw new IllegalArgumentException("El ID de la cuenta no puede ser nulo");
        }

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        return account.getBalance();
    }
}