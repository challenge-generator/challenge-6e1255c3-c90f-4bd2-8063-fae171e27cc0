# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de Patrones GRASP en un Sistema de Gestión de Cuentas**.

| | |
|---|---|
| Tema | aplicación de patrones grasp en el desarrollo de sistemas |
| Nivel | senior-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 20 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web 3.5.6
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-test n/a
- org.projectlombok:lombok 1.18.30
- com.h2database:h2 2.2.224
- org.springdoc:springdoc-openapi-starter-webmvc-ui 2.9.1

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Exploración y Modelado Inicial**: Diagrama de clases inicial y documento de requisitos.
- **Fase 2 — Aplicación de Patrones GRASP**: Documento de diseño con la aplicación de patrones GRASP y justificación.
- **Fase 3 — Refactorización y Optimización**: Documento de refactorización con justificación y evaluación de impacto.
- **Fase 4 — Implementación y Verificación**: Código implementado y documento de verificación.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Referencias colgando (60)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/bank/account/AccountApplication.java` — `com.bank.account.infrastructure.config.AppProperties`
      El import com.bank.account.infrastructure.config.AppProperties usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/bank/account/AccountApplication.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/account/infrastructure/config/GlobalExceptionHandler.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/account/AccountApplication.java` — `AccountRepository.count`
      Se invoca `count` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.getId`
      Se invoca `getId` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.setId`
      Se invoca `setId` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.setClientId`
      Se invoca `setClientId` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.getClientId`
      Se invoca `getClientId` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.setAccountNumber`
      Se invoca `setAccountNumber` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.getAccountNumber`
      Se invoca `getAccountNumber` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.setType`
      Se invoca `setType` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.getType`
      Se invoca `getType` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.setStatus`
      Se invoca `setStatus` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.getStatus`
      Se invoca `getStatus` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.setBalance`
      Se invoca `setBalance` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.getBalance`
      Se invoca `getBalance` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.setCreatedAt`
      Se invoca `setCreatedAt` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.getCreatedAt`
      Se invoca `getCreatedAt` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.setUpdatedAt`
      Se invoca `setUpdatedAt` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.getUpdatedAt`
      Se invoca `getUpdatedAt` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.getTransactions`
      Se invoca `getTransactions` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.setId`
      Se invoca `setId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.setAccountId`
      Se invoca `setAccountId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.setType`
      Se invoca `setType` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.setAmount`
      Se invoca `setAmount` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.setDescription`
      Se invoca `setDescription` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.setTransactionDate`
      Se invoca `setTransactionDate` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.setRelatedAccountId`
      Se invoca `setRelatedAccountId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.setAccount`
      Se invoca `setAccount` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.setTransactions`
      Se invoca `setTransactions` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.getId`
      Se invoca `getId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.getAccountId`
      Se invoca `getAccountId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.getType`
      Se invoca `getType` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.getAmount`
      Se invoca `getAmount` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.getDescription`
      Se invoca `getDescription` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.getRelatedAccountId`
      Se invoca `getRelatedAccountId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.addTransaction`
      Se invoca `addTransaction` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/application/service/AccountService.java` — `AccountRepository.findAll`
      Se invoca `findAll` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `UpdateStatusRequest.clientId`
      Se invoca `clientId` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `UpdateStatusRequest.accountNumber`
      Se invoca `accountNumber` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `UpdateStatusRequest.type`
      Se invoca `type` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `UpdateStatusRequest.initialBalance`
      Se invoca `initialBalance` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `AccountManagementUseCase.getAllAccounts`
      Se invoca `getAllAccounts` sobre `AccountManagementUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `UpdateStatusRequest.amount`
      Se invoca `amount` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `AccountManagementUseCase.transfer`
      Se invoca `transfer` sobre `AccountManagementUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `UpdateStatusRequest.fromAccountId`
      Se invoca `fromAccountId` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `UpdateStatusRequest.toAccountId`
      Se invoca `toAccountId` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `AccountManagementUseCase.updateAccountStatus`
      Se invoca `updateAccountStatus` sobre `AccountManagementUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `UpdateStatusRequest.status`
      Se invoca `status` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `AccountManagementUseCase.deleteAccount`
      Se invoca `deleteAccount` sobre `AccountManagementUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `AccountManagementUseCase.getAccountBalance`
      Se invoca `getAccountBalance` sobre `AccountManagementUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `AccountService.getAccountById`
      Se invoca `getAccountById` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `Account.isPresent`
      Se invoca `isPresent` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `Account.get`
      Se invoca `get` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `Account.size`
      Se invoca `size` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `AccountRepository.findAll`
      Se invoca `findAll` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `Account.isEmpty`
      Se invoca `isEmpty` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `AccountService.activateAccount`
      Se invoca `activateAccount` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `AccountService.deactivateAccount`
      Se invoca `deactivateAccount` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (17)

- `pom.xml`
- `src/main/java/com/bank/account/AccountApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/bank/account/domain/model/Account.java`
- `src/main/java/com/bank/account/domain/model/Client.java`
- `src/main/java/com/bank/account/domain/model/Transaction.java`
- `src/main/java/com/bank/account/domain/port/in/CreateAccountUseCase.java`
- `src/main/java/com/bank/account/domain/port/in/AccountManagementUseCase.java`
- `src/main/java/com/bank/account/domain/port/out/AccountRepository.java`
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java`
- `src/main/java/com/bank/account/domain/exception/AccountException.java`
- `src/main/java/com/bank/account/domain/exception/InsufficientFundsException.java`
- `src/main/java/com/bank/account/domain/exception/ClientNotFoundException.java`
- `src/main/java/com/bank/account/application/service/AccountService.java`
- `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java`
- `src/main/java/com/bank/account/infrastructure/config/GlobalExceptionHandler.java`
- `src/test/java/com/bank/account/application/service/AccountServiceTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/bank/account/domain`
- `src/main/java/com/bank/account/application`
- `src/main/java/com/bank/account/infrastructure`
- `src/main/resources`
- `src/test/java/com/bank/account`

## Verificacion

```bash
mvn clean compile
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior
- Brecha que el reto ataca: Aplica al menos dos patrones GRASP (Patrones de Software para la Asignación de Responsabilidades Generales) en el diseño y desarrollo de un sistema. Entre ellos: Experto en Información, Creador, Controlador, Alta Cohesión y Bajo Acoplamiento, Polimorfismo, Fabricación Pura, Indirección y Variaciones Protegidas.
- Mision: Candidato Senior con experiencia en desarrollo backend con Java.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
