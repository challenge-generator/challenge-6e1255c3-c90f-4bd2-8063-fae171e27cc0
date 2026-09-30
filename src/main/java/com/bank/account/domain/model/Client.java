package com.bank.account.domain.model;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public class Client {
    private final UUID id;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private final String name;

    @NotNull(message = "El apellido no puede ser nulo")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    private final String lastName;

    @NotNull(message = "El tipo de identificación no puede ser nulo")
    private final IdentificationType identificationType;

    @NotNull(message = "El número de identificación no puede ser nulo")
    @Pattern(regexp = "^[A-Z]?\\d{6,20}$", message = "Número de identificación inválido")
    private final String identificationNumber;

    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private final LocalDate birthDate;

    @NotNull(message = "El email no puede ser nulo")
    @Email(message = "Email debe ser válido")
    private final String email;

    @NotNull(message = "El teléfono no puede ser nulo")
    @Pattern(regexp = "^\\+\\d{10,15}$", message = "Teléfono debe tener formato internacional")
    private final String phone;

    @NotNull(message = "La dirección no puede ser nula")
    @Size(min = 10, max = 200, message = "La dirección debe tener entre 10 y 200 caracteres")
    private final String address;

    private final List<Account> accounts;

    public Client(UUID id, String name, String lastName, IdentificationType identificationType,
                 String identificationNumber, LocalDate birthDate, String email, String phone, String address) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }
        if (name == null || name.length() < 2 || name.length() > 100) {
            throw new IllegalArgumentException("Nombre inválido");
        }
        if (lastName == null || lastName.length() < 2 || lastName.length() > 100) {
            throw new IllegalArgumentException("Apellido inválido");
        }
        if (identificationType == null) {
            throw new IllegalArgumentException("El tipo de identificación no puede ser nulo");
        }
        if (identificationNumber == null || !identificationNumber.matches("^[A-Z]?\\d{6,20}$")) {
            throw new IllegalArgumentException("Número de identificación inválido");
        }
        if (birthDate == null || !birthDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Fecha de nacimiento inválida");
        }
        if (email == null || !email.matches("^[^@]+@[^@]+\\.[^@]+")) {
            throw new IllegalArgumentException("Email inválido");
        }
        if (phone == null || !phone.matches("^\\+\\d{10,15}$")) {
            throw new IllegalArgumentException("Teléfono inválido");
        }
        if (address == null || address.length() < 10 || address.length() > 200) {
            throw new IllegalArgumentException("Dirección inválida");
        }

        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.identificationType = identificationType;
        this.identificationNumber = identificationNumber;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("La cuenta no puede ser nula");
        }
        this.accounts.add(account);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return name + " " + lastName;
    }

    public IdentificationType getIdentificationType() {
        return identificationType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public enum IdentificationType {
        CC, CE, PASSPORT, NIT
    }
}