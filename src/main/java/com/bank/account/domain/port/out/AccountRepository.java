package com.bank.account.domain.port.out;


import com.bank.account.domain.model.AccountStatus;
import com.bank.account.domain.model.Account;
import com.bank.account.domain.exception.AccountException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de salida que define las operaciones de persistencia para cuentas.
 * Implementa el patrón Fabricación Pura (Pure Fabrication) de GRASP: esta interfaz
 * representa una clase completamente artificial que no representa un concepto del
 * dominio, sino que fue creada para resolver el problema de la persistencia.
 * 
 * El patrón Fabricación Pura se usa cuando necesitamos una clase que no representa
 * un objeto del dominio pero que es necesaria para resolver problemas técnicos como
 * la persistencia. Esta interfaz define el contrato que será implementado por un
 * adaptador de infraestructura (JpaAccountRepository).
 */
public interface AccountRepository {
    
    /**
     * Persiste una cuenta en el almacenamiento.
     * 
     * @param account La cuenta a persistir
     * @return La cuenta persistida con su identificador asignado
     */
    Account save(Account account);
    
    /**
     * Busca una cuenta por su identificador único.
     * 
     * @param id Identificador de la cuenta
     * @return Optional con la cuenta si existe, vacío si no
     */
    Optional<Account> findById(UUID id);
    
    /**
     * Busca una cuenta por su número único.
     * 
     * @param accountNumber Número de cuenta
     * @return Optional con la cuenta si existe, vacío si no
     */
    Optional<Account> findByAccountNumber(String accountNumber);
    
    /**
     * Busca todas las cuentas de un cliente específico.
     * 
     * @param clientId Identificador del cliente
     * @return Lista de cuentas del cliente
     */
    List<Account> findByClientId(UUID clientId);
    
    /**
     * Verifica si existe una cuenta con el número proporcionado.
     * 
     * @param accountNumber Número de cuenta a verificar
     * @return true si existe una cuenta con ese número
     */
    boolean existsByAccountNumber(String accountNumber);
    
    /**
     * Elimina una cuenta del almacenamiento.
     * 
     * @param account La cuenta a eliminar
     * @throws AccountException si la cuenta no existe
     */
    void delete(Account account) throws AccountException;
    
    /**
     * Busca todas las cuentas con un estado específico.
     * 
     * @param status Estado de las cuentas a buscar
     * @return Lista de cuentas con el estado especificado
     */
    List<Account> findByStatus(com.bank.account.domain.model.AccountStatus status);
}