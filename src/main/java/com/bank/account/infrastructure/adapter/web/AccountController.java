package com.bank.account.infrastructure.adapter.web;

import com.bank.account.domain.model.Account;
import com.bank.account.domain.model.Account.AccountStatus;
import com.bank.account.domain.model.Account.AccountType;
import com.bank.account.domain.port.in.AccountManagementUseCase;
import com.bank.account.domain.port.in.CreateAccountUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
@Tag(name = "Gestión de Cuentas", description = "API para la gestión de cuentas bancarias")
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;
    private final AccountManagementUseCase accountManagementUseCase;

    public AccountController(CreateAccountUseCase createAccountUseCase, 
                             AccountManagementUseCase accountManagementUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.accountManagementUseCase = accountManagementUseCase;
    }

    @PostMapping
    @Operation(summary = "Crear nueva cuenta", description = "Crea una nueva cuenta bancaria para un cliente existente")
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountRequest request) {
        Account account = createAccountUseCase.createAccount(
            request.clientId(),
            request.accountNumber(),
            request.type(),
            request.initialBalance()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cuenta por ID", description = "Recupera los detalles de una cuenta bancaria por su identificador")
    public ResponseEntity<Account> getAccount(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID id) {
        return accountManagementUseCase.getAccount(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/number/{accountNumber}")
    @Operation(summary = "Obtener cuenta por número", description = "Recupera los detalles de una cuenta bancaria por su número")
    public ResponseEntity<Account> getAccountByNumber(
            @Parameter(description = "Número de cuenta") @PathVariable String accountNumber) {
        return accountManagementUseCase.getAccountByNumber(accountNumber)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Listar cuentas por cliente", description = "Recupera todas las cuentas asociadas a un cliente")
    public ResponseEntity<List<Account>> getAccountsByClient(
            @Parameter(description = "ID del cliente") @RequestParam UUID clientId) {
        List<Account> accounts = accountManagementUseCase.getAccountsByClient(clientId);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/all")
    @Operation(summary = "Listar todas las cuentas", description = "Recupera todas las cuentas bancarias del sistema")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountManagementUseCase.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/{id}/deposit")
    @Operation(summary = "Depositar fondos", description = "Realiza un depósito en una cuenta bancaria")
    public ResponseEntity<Account> deposit(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID id,
            @RequestBody DepositRequest request) {
        Account account = accountManagementUseCase.deposit(id, request.amount());
        return ResponseEntity.ok(account);
    }

    @PostMapping("/{id}/withdraw")
    @Operation(summary = "Retirar fondos", description = "Realiza un retiro de una cuenta bancaria")
    public ResponseEntity<Account> withdraw(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID id,
            @RequestBody WithdrawRequest request) {
        Account account = accountManagementUseCase.withdraw(id, request.amount());
        return ResponseEntity.ok(account);
    }

    @PostMapping("/transfer")
    @Operation(summary = "Transferir fondos", description = "Realiza una transferencia entre dos cuentas")
    public ResponseEntity<Account> transfer(@RequestBody TransferRequest request) {
        Account account = accountManagementUseCase.transfer(
            request.fromAccountId(),
            request.toAccountId(),
            request.amount()
        );
        return ResponseEntity.ok(account);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Actualizar estado de cuenta", description = "Cambia el estado de una cuenta bancaria")
    public ResponseEntity<Account> updateAccountStatus(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID id,
            @RequestBody UpdateStatusRequest request) {
        Account account = accountManagementUseCase.updateAccountStatus(id, request.status());
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cuenta", description = "Elimina una cuenta bancaria del sistema")
    public ResponseEntity<Void> deleteAccount(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID id) {
        accountManagementUseCase.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/balance")
    @Operation(summary = "Consultar saldo", description = "Consulta el saldo actual de una cuenta")
    public ResponseEntity<BigDecimal> getBalance(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID id) {
        BigDecimal balance = accountManagementUseCase.getAccountBalance(id);
        return ResponseEntity.ok(balance);
    }

    public record CreateAccountRequest(
        UUID clientId,
        String accountNumber,
        AccountType type,
        BigDecimal initialBalance
    ) {}

    public record DepositRequest(BigDecimal amount) {}

    public record WithdrawRequest(BigDecimal amount) {}

    public record TransferRequest(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {}

    public record UpdateStatusRequest(AccountStatus status) {}
}