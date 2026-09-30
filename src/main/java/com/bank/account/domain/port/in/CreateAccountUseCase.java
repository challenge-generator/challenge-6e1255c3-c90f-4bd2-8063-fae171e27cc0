package com.bank.account.domain.port.in;

import com.bank.account.domain.model.Account;
import com.bank.account.domain.model.AccountType;
import com.bank.account.domain.exception.AccountException;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Puerto de entrada que define el caso de uso para la creación de cuentas.
 * Implementa el patrón Experto en Información: esta interfaz representa el conocimiento
 * necesario para crear una cuenta, coordinando la información del cliente y los datos
 * de la cuenta nueva.
 * 
 * El patrón Experto en Información (Information Expert) establece que la responsabilidad
 * de crear una cuenta recae en la clase que posee la información necesaria para hacerlo.
 * Esta interfaz define el contrato que el servicio de aplicación implementará.
 */
public interface CreateAccountUseCase {
    
    /**
     * Crea una nueva cuenta bancaria para un cliente existente.
     * 
     * @param clientId Identificador único del cliente propietario de la cuenta
     * @param accountNumber Número de cuenta único generado para la nueva cuenta
     * @param type Tipo de cuenta (AHORROS, CORRIENTE, etc.)
     * @param initialBalance Saldo inicial de la cuenta
     * @return La cuenta recién creada con todos sus datos inicializados
     * @throws AccountException si los datos proporcionados son inválidos o el cliente no existe
     */
    Account createAccount(UUID clientId, String accountNumber, AccountType type, BigDecimal initialBalance) 
        throws AccountException;
    
    /**
     * Valida que los datos necesarios para crear una cuenta sean correctos
     * antes de proceder con la creación.
     * 
     * @param clientId Identificador del cliente
     * @param accountNumber Número de cuenta propuesto
     * @param type Tipo de cuenta seleccionado
     * @param initialBalance Saldo inicial propuesto
     * @return true si los datos son válidos para crear la cuenta
     */
    boolean validateAccountData(UUID clientId, String accountNumber, AccountType type, BigDecimal initialBalance);
}