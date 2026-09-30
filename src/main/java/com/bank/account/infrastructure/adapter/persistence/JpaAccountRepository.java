package com.bank.account.infrastructure.adapter.persistence;

import com.bank.account.domain.model.Account;
import com.bank.account.domain.model.Account.AccountStatus;
import com.bank.account.domain.model.Account.AccountType;
import com.bank.account.domain.model.Transaction;
import com.bank.account.domain.model.Transaction.TransactionType;
import com.bank.account.domain.port.out.AccountRepository;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaAccountRepository implements AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account save(Account account) {
        if (account.getId() == null) {
            entityManager.persist(toEntity(account));
            return account;
        } else {
            return toDomain(entityManager.merge(toEntity(account)));
        }
    }

    @Override
    public Optional<Account> findById(UUID id) {
        AccountEntity entity = entityManager.find(AccountEntity.class, id);
        return Optional.ofNullable(entity).map(this::toDomain);
    }

    @Override
    public List<Account> findByClientId(UUID clientId) {
        TypedQuery<AccountEntity> query = entityManager.createQuery(
            "SELECT a FROM AccountEntity a WHERE a.clientId = :clientId",
            AccountEntity.class
        );
        query.setParameter("clientId", clientId);
        return query.getResultList().stream()
            .map(this::toDomain)
            .toList();
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        TypedQuery<AccountEntity> query = entityManager.createQuery(
            "SELECT a FROM AccountEntity a WHERE a.accountNumber = :accountNumber",
            AccountEntity.class
        );
        query.setParameter("accountNumber", accountNumber);
        List<AccountEntity> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(toDomain(results.get(0)));
    }

    @Override
    public void delete(Account account) {
        entityManager.remove(toEntity(account));
    }

    @Override
    public List<Account> findAll() {
        TypedQuery<AccountEntity> query = entityManager.createQuery(
            "SELECT a FROM AccountEntity a",
            AccountEntity.class
        );
        return query.getResultList().stream()
            .map(this::toDomain)
            .toList();
    }

    private AccountEntity toEntity(Account account) {
        AccountEntity entity = new AccountEntity();
        entity.setId(account.getId());
        entity.setClientId(account.getClientId());
        entity.setAccountNumber(account.getAccountNumber());
        entity.setType(Enum.valueOf(AccountTypeEntity.class, account.getType().name()));
        entity.setStatus(Enum.valueOf(AccountStatusEntity.class, account.getStatus().name()));
        entity.setBalance(account.getBalance());
        entity.setCreatedAt(account.getCreatedAt());
        entity.setUpdatedAt(account.getUpdatedAt());
        
        List<TransactionEntity> transactionEntities = new ArrayList<>();
        if (account.getTransactions() != null) {
            for (Transaction tx : account.getTransactions()) {
                TransactionEntity txEntity = new TransactionEntity();
                txEntity.setId(tx.getId());
                txEntity.setAccountId(tx.getAccountId());
                txEntity.setType(Enum.valueOf(TransactionTypeEntity.class, tx.getType().name()));
                txEntity.setAmount(tx.getAmount());
                txEntity.setDescription(tx.getDescription());
                txEntity.setTransactionDate(tx.getTransactionDate());
                txEntity.setRelatedAccountId(tx.getRelatedAccountId());
                txEntity.setAccount(entity);
                transactionEntities.add(txEntity);
            }
        }
        entity.setTransactions(transactionEntities);
        return entity;
    }

    private Account toDomain(AccountEntity entity) {
        List<Transaction> transactions = new ArrayList<>();
        if (entity.getTransactions() != null) {
            for (TransactionEntity txEntity : entity.getTransactions()) {
                Transaction tx = new Transaction(
                    txEntity.getId(),
                    txEntity.getAccountId(),
                    TransactionType.valueOf(txEntity.getType().name()),
                    txEntity.getAmount(),
                    txEntity.getDescription(),
                    txEntity.getRelatedAccountId()
                );
                transactions.add(tx);
            }
        }
        
        Account account = new Account(
            entity.getId(),
            entity.getClientId(),
            entity.getAccountNumber(),
            AccountType.valueOf(entity.getType().name()),
            entity.getBalance()
        );
        
        for (Transaction tx : transactions) {
            account.addTransaction(tx);
        }
        
        return account;
    }

    @Entity
    @Table(name = "accounts")
    private static class AccountEntity {
        @Id
        private UUID id;
        
        @Column(name = "client_id", nullable = false)
        private UUID clientId;
        
        @Column(name = "account_number", unique = true, nullable = false)
        private String accountNumber;
        
        @Enumerated(EnumType.STRING)
        @Column(name = "type", nullable = false)
        private AccountTypeEntity type;
        
        @Enumerated(EnumType.STRING)
        @Column(name = "status", nullable = false)
        private AccountStatusEntity status;
        
        @Column(name = "balance", nullable = false)
        private BigDecimal balance;
        
        @Column(name = "created_at", nullable = false)
        private LocalDateTime createdAt;
        
        @Column(name = "updated_at")
        private LocalDateTime updatedAt;
        
        @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<TransactionEntity> transactions;

        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public UUID getClientId() { return clientId; }
        public void setClientId(UUID clientId) { this.clientId = clientId; }
        public String getAccountNumber() { return accountNumber; }
        public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
        public AccountTypeEntity getType() { return type; }
        public void setType(AccountTypeEntity type) { this.type = type; }
        public AccountStatusEntity getStatus() { return status; }
        public void setStatus(AccountStatusEntity status) { this.status = status; }
        public BigDecimal getBalance() { return balance; }
        public void setBalance(BigDecimal balance) { this.balance = balance; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
        public LocalDateTime getUpdatedAt() { return updatedAt; }
        public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
        public List<TransactionEntity> getTransactions() { return transactions; }
        public void setTransactions(List<TransactionEntity> transactions) { this.transactions = transactions; }
    }

    @Entity
    @Table(name = "transactions")
    private static class TransactionEntity {
        @Id
        private UUID id;
        
        @Column(name = "account_id", nullable = false)
        private UUID accountId;
        
        @Enumerated(EnumType.STRING)
        @Column(name = "type", nullable = false)
        private TransactionTypeEntity type;
        
        @Column(name = "amount", nullable = false)
        private BigDecimal amount;
        
        @Column(name = "description")
        private String description;
        
        @Column(name = "transaction_date", nullable = false)
        private LocalDateTime transactionDate;
        
        @Column(name = "related_account_id")
        private UUID relatedAccountId;
        
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "account_id", insertable = false, updatable = false)
        private AccountEntity account;

        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public UUID getAccountId() { return accountId; }
        public void setAccountId(UUID accountId) { this.accountId = accountId; }
        public TransactionTypeEntity getType() { return type; }
        public void setType(TransactionTypeEntity type) { this.type = type; }
        public BigDecimal getAmount() { return amount; }
        public void setAmount(BigDecimal amount) { this.amount = amount; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public LocalDateTime getTransactionDate() { return transactionDate; }
        public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
        public UUID getRelatedAccountId() { return relatedAccountId; }
        public void setRelatedAccountId(UUID relatedAccountId) { this.relatedAccountId = relatedAccountId; }
        public AccountEntity getAccount() { return account; }
        public void setAccount(AccountEntity account) { this.account = account; }
    }

    private enum AccountTypeEntity { SAVINGS, CHECKING, CREDIT }
    private enum AccountStatusEntity { ACTIVE, INACTIVE, BLOCKED, CLOSED }
    private enum TransactionTypeEntity { DEPOSIT, WITHDRAWAL, TRANSFER, FEE, INTEREST }
}