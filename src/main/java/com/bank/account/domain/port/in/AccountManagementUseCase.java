package com.bank.account.domain.port.in;

import com.bank.account.domain.model.Account;
import com.bank.account.domain.model.AccountStatus;
import com.bank.account.domain.model.Transaction;
import com.bank.account.domain.exception.AccountException;
import com.bank.account.domain.exception.InsufficientFundsException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Puerto de entrada que define las operaciones de gestión de cuentas.
 * Implementa el patrón Controlador (Controller) de GRASP: esta interfaz actúa como
 * el controlador que orquesta las operaciones sobre las cuentas, coordinando las
 * interacciones entre los diferentes objetos del dominio.
 * 
 * El patrón Controlador asigna la responsabilidad de manejar eventos del sistema a
 * una clase que representa un escenario de caso de uso. Esta interfaz define el
 * contrato para todas las operaciones de gestión de cuentas.
 */
public interface AccountManagementUseCase {
    
    /**
     * Realiza un depósito en una cuenta existente.
     * 
     * @param accountId Identificador de la cuenta
     * @param amount Monto a depositar
     * @return La cuenta actualizada con el nuevo saldo
     * @throws AccountException si la cuenta no existe o está inactiva
     */
    Account deposit(UUID accountId, BigDecimal amount) throws AccountException;
    
    /**
     * Realiza un retiro de una cuenta existente.
     * 
     * @param accountId Identificador de la cuenta
     * @param amount Monto a retirar
     * @return La cuenta actualizada con el nuevo saldo
     * @throws AccountException si la cuenta no existe o está inactiva
     * @throws InsufficientFundsException si el saldo es insuficiente
     */
    Account withdraw(UUID accountId, BigDecimal amount) throws AccountException, InsufficientFundsException;
    
    /**
     * Consulta el saldo actual de una cuenta.
     * 
     * @param accountId Identificador de la cuenta
     * @return El saldo actual de la cuenta
     * @throws AccountException si la cuenta no existe
     */
    BigDecimal getBalance(UUID accountId) throws AccountException;
    
    /**
     * Consulta una cuenta por su identificador.
     * 
     * @param accountId Identificador de la cuenta
     * @return La cuenta encontrada
     * @throws AccountException si la cuenta no existe
     */
    Account getAccount(UUID accountId) throws AccountException;
    
    /**
     * Consulta una cuenta por su número.
     * 
     * @param accountNumber Número de cuenta
     * @return La cuenta encontrada
     * @throws AccountException si la cuenta no existe
     */
    Account getAccountByNumber(String accountNumber) throws AccountException;
    
    /**
     * Cambia el estado de una cuenta.
     * 
     * @param accountId Identificador de la cuenta
     * @param status Nuevo estado para la cuenta
     * @return La cuenta con el estado actualizado
     * @throws AccountException si la cuenta no existe
     */
    Account changeStatus(UUID accountId, AccountStatus status) throws AccountException;
    
    /**
     * Lista todas las transacciones de una cuenta.
     * 
     * @param accountId Identificador de la cuenta
     * @return Lista de transacciones ordenadas por fecha descendente
     * @throws AccountException si la cuenta no existe
     */
    List<Transaction> getTransactions(UUID accountId) throws AccountException;
    
    /**
     * Lista todas las cuentas de un cliente.
     * 
     * @param clientId Identificador del cliente
     * @return Lista de cuentas del cliente
     */
    List<Account> getAccountsByClient(UUID clientId);
}