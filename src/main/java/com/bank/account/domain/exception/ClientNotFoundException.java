package com.bank.account.domain.exception;

import java.util.UUID;

public class ClientNotFoundException extends AccountException {

    private final UUID clientId;
    private final String identificationNumber;

    public ClientNotFoundException(UUID clientId) {
        super(String.format("Cliente no encontrado con ID: %s", clientId));
        this.clientId = clientId;
        this.identificationNumber = null;
    }

    public ClientNotFoundException(String identificationNumber) {
        super(String.format("Cliente no encontrado con identificación: %s", identificationNumber));
        this.clientId = null;
        this.identificationNumber = identificationNumber;
    }

    public ClientNotFoundException(String message, UUID clientId, String identificationNumber) {
        super(message);
        this.clientId = clientId;
        this.identificationNumber = identificationNumber;
    }

    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.clientId = null;
        this.identificationNumber = null;
    }

    public UUID getClientId() {
        return clientId;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }
}