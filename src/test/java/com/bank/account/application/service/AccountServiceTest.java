package com.bank.account.application.service;

import com.bank.account.domain.exception.ClientNotFoundException;
import com.bank.account.domain.exception.InsufficientFundsException;
import com.bank.account.domain.model.Account;
import com.bank.account.domain.model.Account.AccountStatus;
import com.bank.account.domain.model.Account.AccountType;
import com.bank.account.domain.model.Client;
import com.bank.account.domain.model.Client.IdentificationType;
import com.bank.account.domain.model.Transaction.TransactionType;
import com.bank.account.domain.port.out.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AccountService - Pruebas Unitarias")
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Client testClient;
    private Account testAccount;
    private UUID clientId;
    private UUID accountId;

    @BeforeEach
    void setUp() {
        clientId = UUID.randomUUID();
        accountId = UUID.randomUUID();
        
        testClient = new Client(
            clientId,
            "Juan",
            "Pérez",
            IdentificationType.DNI,
            "12345678",
            LocalDate.of(1985, 5, 15),
            "juan.perez@email.com",
            "+5491155555555",
            "Calle Falsa 123",
            new ArrayList<>()
        );
        
        testAccount = new Account(
            accountId,
            clientId,
            "ACC-001",
            AccountType.CAJA_AHORRO,
            BigDecimal.valueOf(1000)
        );
    }

    @Nested
    @DisplayName("Creación de Cuentas")
    class CreateAccountTests {

        @Test
        @DisplayName("Crear cuenta exitosamente con datos válidos")
        void createAccount_Success() {
            when(accountRepository.save(any(Account.class))).thenReturn(testAccount);
            
            Account result = accountService.createAccount(
                clientId,
                "ACC-001",
                AccountType.CAJA_AHORRO,
                BigDecimal.valueOf(1000)
            );
            
            assertNotNull(result);
            assertEquals("ACC-001", result.getAccountNumber());
            assertEquals(AccountType.CAJA_AHORRO, result.getType());
            assertEquals(BigDecimal.valueOf(1000), result.getBalance());
            verify(accountRepository, times(1)).save(any(Account.class));
        }

        @Test
        @DisplayName("Crear cuenta con balance inicial cero")
        void createAccount_WithZeroBalance() {
            when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> {
                Account acc = invocation.getArgument(0);
                return acc;
            });
            
            Account result = accountService.createAccount(
                clientId,
                "ACC-002",
                AccountType.CUENTA_CORRIENTE,
                BigDecimal.ZERO
            );
            
            assertNotNull(result);
            assertEquals(BigDecimal.ZERO, result.getBalance());
        }

        @Test
        @DisplayName("Crear cuenta lanza excepción con cliente nulo")
        void createAccount_WithNullClient_ThrowsException() {
            assertThrows(ClientNotFoundException.class, () -> {
                accountService.createAccount(
                    null,
                    "ACC-003",
                    AccountType.CAJA_AHORRO,
                    BigDecimal.valueOf(500)
                );
            });
        }
    }

    @Nested
    @DisplayName("Operaciones de Depósito")
    class DepositTests {

        @Test
        @DisplayName("Depósito exitoso incrementa el balance")
        void deposit_Success() {
            BigDecimal initialBalance = testAccount.getBalance();
            BigDecimal depositAmount = BigDecimal.valueOf(500);
            
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
            when(accountRepository.save(any(Account.class))).thenReturn(testAccount);
            
            Account result = accountService.deposit(accountId, depositAmount);
            
            assertNotNull(result);
            assertTrue(result.getBalance().compareTo(initialBalance) > 0);
            verify(accountRepository, times(1)).save(any(Account.class));
        }

        @Test
        @DisplayName("Depósito de monto negativo lanza excepción")
        void deposit_NegativeAmount_ThrowsException() {
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
            
            assertThrows(IllegalArgumentException.class, () -> {
                accountService.deposit(accountId, BigDecimal.valueOf(-100));
            });
        }

        @Test
        @DisplayName("Depósito en cuenta inexistente lanza excepción")
        void deposit_NonExistentAccount_ThrowsException() {
            UUID nonExistentId = UUID.randomUUID();
            when(accountRepository.findById(nonExistentId)).thenReturn(Optional.empty());
            
            assertThrows(Exception.class, () -> {
                accountService.deposit(nonExistentId, BigDecimal.valueOf(100));
            });
        }
    }

    @Nested
    @DisplayName("Operaciones de Retiro")
    class WithdrawTests {

        @Test
        @DisplayName("Retiro exitoso con fondos suficientes")
        void withdraw_Success() {
            BigDecimal initialBalance = testAccount.getBalance();
            BigDecimal withdrawAmount = BigDecimal.valueOf(300);
            
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
            when(accountRepository.save(any(Account.class))).thenReturn(testAccount);
            
            Account result = accountService.withdraw(accountId, withdrawAmount);
            
            assertNotNull(result);
            assertTrue(result.getBalance().compareTo(initialBalance.subtract(withdrawAmount)) == 0);
        }

        @Test
        @DisplayName("Retiro mayor al balance lanza InsufficientFundsException")
        void withdraw_InsufficientFunds_ThrowsException() {
            BigDecimal largeAmount = BigDecimal.valueOf(5000);
            
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
            
            assertThrows(InsufficientFundsException.class, () -> {
                accountService.withdraw(accountId, largeAmount);
            });
        }

        @Test
        @DisplayName("Retiro de cuenta inactiva lanza excepción")
        void withdraw_InactiveAccount_ThrowsException() {
            testAccount.setStatus(AccountStatus.INACTIVE);
            
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
            
            assertThrows(Exception.class, () -> {
                accountService.withdraw(accountId, BigDecimal.valueOf(100));
            });
        }
    }

    @Nested
    @DisplayName("Consultas de Cuenta")
    class AccountQueryTests {

        @Test
        @DisplayName("Obtener cuenta por ID exitosamente")
        void getAccountById_Success() {
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
            
            Optional<Account> result = accountService.getAccountById(accountId);
            
            assertTrue(result.isPresent());
            assertEquals(accountId, result.get().getId());
        }

        @Test
        @DisplayName("Obtener cuenta por ID inexistente retorna vacío")
        void getAccountById_NotFound_ReturnsEmpty() {
            UUID nonExistentId = UUID.randomUUID();
            when(accountRepository.findById(nonExistentId)).thenReturn(Optional.empty());
            
            Optional<Account> result = accountService.getAccountById(nonExistentId);
            
            assertFalse(result.isPresent());
        }

        @Test
        @DisplayName("Obtener cuentas por cliente retorna lista")
        void getAccountsByClient_Success() {
            List<Account> accounts = List.of(testAccount);
            when(accountRepository.findByClientId(clientId)).thenReturn(accounts);
            
            List<Account> result = accountService.getAccountsByClient(clientId);
            
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(clientId, result.get(0).getClientId());
        }

        @Test
        @DisplayName("Obtener todas las cuentas retorna lista")
        void getAllAccounts_Success() {
            List<Account> accounts = List.of(testAccount);
            when(accountRepository.findAll()).thenReturn(accounts);
            
            List<Account> result = accountService.getAllAccounts();
            
            assertNotNull(result);
            assertFalse(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Cambio de Estado de Cuenta")
    class AccountStatusTests {

        @Test
        @DisplayName("Activar cuenta exitosamente")
        void activateAccount_Success() {
            testAccount.setStatus(AccountStatus.INACTIVE);
            
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> {
                Account acc = invocation.getArgument(0);
                return acc;
            });
            
            Account result = accountService.activateAccount(accountId);
            
            assertEquals(AccountStatus.ACTIVE, result.getStatus());
        }

        @Test
        @DisplayName("Desactivar cuenta exitosamente")
        void deactivateAccount_Success() {
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> {
                Account acc = invocation.getArgument(0);
                return acc;
            });
            
            Account result = accountService.deactivateAccount(accountId);
            
            assertEquals(AccountStatus.INACTIVE, result.getStatus());
        }
    }

    @Nested
    @DisplayName("Transferencias")
    class TransferTests {

        @Test
        @DisplayName("Transferencia entre cuentas exitosamente")
        void transfer_Success() {
            UUID targetAccountId = UUID.randomUUID();
            Account targetAccount = new Account(
                targetAccountId,
                clientId,
                "ACC-TARGET",
                AccountType.CAJA_AHORRO,
                BigDecimal.valueOf(500)
            );
            BigDecimal transferAmount = BigDecimal.valueOf(200);
            
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));
            
            assertDoesNotThrow(() -> {
                accountService.transfer(accountId, targetAccountId, transferAmount);
            });
            
            verify(accountRepository, times(2)).save(any(Account.class));
        }

        @Test
        @DisplayName("Transferencia con fondos insuficientes lanza excepción")
        void transfer_InsufficientFunds_ThrowsException() {
            UUID targetAccountId = UUID.randomUUID();
            BigDecimal largeAmount = BigDecimal.valueOf(10000);
            
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(testAccount));
            
            assertThrows(InsufficientFundsException.class, () -> {
                accountService.transfer(accountId, targetAccountId, largeAmount);
            });
        }
    }
}