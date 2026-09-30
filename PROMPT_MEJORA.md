# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/bank/account/AccountApplication.java` — `com.bank.account.infrastructure.config.AppProperties`: El import com.bank.account.infrastructure.config.AppProperties usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/bank/account/AccountApplication.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/account/infrastructure/config/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/account/AccountApplication.java` — `AccountRepository.count`: Se invoca `count` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.getId`: Se invoca `getId` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.setId`: Se invoca `setId` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.setClientId`: Se invoca `setClientId` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.getClientId`: Se invoca `getClientId` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.setAccountNumber`: Se invoca `setAccountNumber` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.getAccountNumber`: Se invoca `getAccountNumber` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.setType`: Se invoca `setType` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.getType`: Se invoca `getType` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.setStatus`: Se invoca `setStatus` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.getStatus`: Se invoca `getStatus` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.setBalance`: Se invoca `setBalance` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.getBalance`: Se invoca `getBalance` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.setCreatedAt`: Se invoca `setCreatedAt` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.getCreatedAt`: Se invoca `getCreatedAt` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.setUpdatedAt`: Se invoca `setUpdatedAt` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.getUpdatedAt`: Se invoca `getUpdatedAt` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.getTransactions`: Se invoca `getTransactions` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.setId`: Se invoca `setId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.setAccountId`: Se invoca `setAccountId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.setType`: Se invoca `setType` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.setAmount`: Se invoca `setAmount` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.setDescription`: Se invoca `setDescription` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.setTransactionDate`: Se invoca `setTransactionDate` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.setRelatedAccountId`: Se invoca `setRelatedAccountId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.setAccount`: Se invoca `setAccount` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.setTransactions`: Se invoca `setTransactions` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.getId`: Se invoca `getId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.getAccountId`: Se invoca `getAccountId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.getType`: Se invoca `getType` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.getAmount`: Se invoca `getAmount` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.getDescription`: Se invoca `getDescription` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `TransactionEntity.getRelatedAccountId`: Se invoca `getRelatedAccountId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java` — `AccountEntity.addTransaction`: Se invoca `addTransaction` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/application/service/AccountService.java` — `AccountRepository.findAll`: Se invoca `findAll` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `UpdateStatusRequest.clientId`: Se invoca `clientId` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `UpdateStatusRequest.accountNumber`: Se invoca `accountNumber` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `UpdateStatusRequest.type`: Se invoca `type` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `UpdateStatusRequest.initialBalance`: Se invoca `initialBalance` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `AccountManagementUseCase.getAllAccounts`: Se invoca `getAllAccounts` sobre `AccountManagementUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `UpdateStatusRequest.amount`: Se invoca `amount` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `AccountManagementUseCase.transfer`: Se invoca `transfer` sobre `AccountManagementUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `UpdateStatusRequest.fromAccountId`: Se invoca `fromAccountId` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `UpdateStatusRequest.toAccountId`: Se invoca `toAccountId` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `AccountManagementUseCase.updateAccountStatus`: Se invoca `updateAccountStatus` sobre `AccountManagementUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `UpdateStatusRequest.status`: Se invoca `status` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `AccountManagementUseCase.deleteAccount`: Se invoca `deleteAccount` sobre `AccountManagementUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java` — `AccountManagementUseCase.getAccountBalance`: Se invoca `getAccountBalance` sobre `AccountManagementUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `AccountService.getAccountById`: Se invoca `getAccountById` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `Account.isPresent`: Se invoca `isPresent` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `Account.get`: Se invoca `get` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `Account.size`: Se invoca `size` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `AccountRepository.findAll`: Se invoca `findAll` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `Account.isEmpty`: Se invoca `isEmpty` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `AccountService.activateAccount`: Se invoca `activateAccount` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `AccountService.deactivateAccount`: Se invoca `deactivateAccount` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior

### Brecha de conocimiento
Aplica al menos dos patrones GRASP (Patrones de Software para la Asignación de Responsabilidades Generales) en el diseño y desarrollo de un sistema. Entre ellos: Experto en Información, Creador, Controlador, Alta Cohesión y Bajo Acoplamiento, Polimorfismo, Fabricación Pura, Indirección y Variaciones Protegidas.

### Misión / candidato
Candidato Senior con experiencia en desarrollo backend con Java.

### Reto
- Tema: aplicación de patrones grasp en el desarrollo de sistemas
- Seniority: senior-l2
- Tipo: practical
- Título: Implementación de Patrones GRASP en un Sistema de Gestión de Cuentas
- Tiempo estimado: 20 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Exploración y Modelado Inicial — objetivo: Comprender los requisitos del sistema y modelar las entidades y relaciones iniciales. — entregable (NO resolver): Diagrama de clases inicial y documento de requisitos.
- Fase 2: Aplicación de Patrones GRASP — objetivo: Aplicar al menos dos patrones GRASP en el diseño del sistema. — entregable (NO resolver): Documento de diseño con la aplicación de patrones GRASP y justificación.
- Fase 3: Refactorización y Optimización — objetivo: Refactorizar el diseño para mejorar la cohesión y reducir el acoplamiento. — entregable (NO resolver): Documento de refactorización con justificación y evaluación de impacto.
- Fase 4: Implementación y Verificación — objetivo: Implementar el diseño refactorizado y verificar su correcto funcionamiento. — entregable (NO resolver): Código implementado y documento de verificación.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.bank</groupId>
    <artifactId>account</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>account</name>
    <description>Sistema de gestión de cuentas bancarias con aplicación de patrones GRASP</description>

    <properties>
        <java.version>21</java.version>
        <springdoc-openapi-ui.version>2.9.1</springdoc-openapi-ui.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.30</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.2.224</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc-openapi-ui.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/bank/account/AccountApplication.java ===
package com.bank.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.bank.account.infrastructure.config.AppProperties;
import com.bank.account.domain.port.out.AccountRepository;
import com.bank.account.application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bank.account.infrastructure.adapter.persistence")
@ComponentScan(basePackages = {"com.bank.account"})
@EnableConfigurationProperties(AppProperties.class)
@EnableAsync
public class AccountApplication {

    private static final Logger logger = LoggerFactory.getLogger(AccountApplication.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @PostConstruct
    public void init() {
        logger.info("Inicializando aplicación de gestión de cuentas bancarias");
        try {
            // Verificación de conexión con la base de datos
            accountRepository.count();
            logger.info("Conexión con la base de datos verificada exitosamente");

            // Inicialización de datos de prueba si es necesario
            initializeSampleData();
        } catch (Exception e) {
            logger.error("Error durante la inicialización de la aplicación", e);
            throw new RuntimeException("Fallo en la inicialización de la aplicación", e);
        }
    }

    private void initializeSampleData() {
        try {
            // Creación de datos de prueba para demostración
            if (accountRepository.count() == 0) {
                logger.info("Creando datos de prueba para cuentas bancarias");
                // En un entorno real, esto se manejaría con migraciones o scripts
                // Aquí se omite la implementación detallada para mantener el foco en la arquitectura
            }
        } catch (Exception e) {
            logger.warn("Error al crear datos de prueba", e);
        }
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .maxAge(3600);
            }
        };
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
server:
  port: 8080
  servlet:
    context-path: /api

spring:
  application:
    name: account-service
  datasource:
    url: jdbc:h2:mem:accountdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    driver-class-name: org.h2.Driver
    username: sa
    password: ''
    hikari:
      connection-timeout: 20000
      maximum-pool-size: 5
  h2:
    console:
      enabled: true
      path: /h2-console
      settings:
        web-allow-others: true
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        use_sql_comments: true
        jdbc:
          batch_size: 20
        order_inserts: true
        order_updates: true
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher

logging:
  level:
    root: INFO
    com.bank.account: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
    org.springframework.web: INFO
    org.springframework.transaction: INFO

springdoc:
  api-docs:
    path: /v3/api-docs
  swagger-ui:
    path: /swagger-ui.html
    tagsSorter: alpha
    operationsSorter: alpha
    docExpansion: none
    enabled: true

app:
  async:
    core-pool-size: 5
    max-pool-size: 10
    queue-capacity: 100
  security:
    jwt:
      secret: ${JWT_SECRET:defaultSecretKeyForDevelopmentOnly}
      expiration: 86400000

// === ARCHIVO: src/main/java/com/bank/account/domain/model/Account.java ===
package com.bank.account.domain.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public class Account {
    private final UUID id;
    private final UUID clientId;

    @NotNull(message = "El número de cuenta no puede ser nulo")
    @Pattern(regexp = "^[A-Z]{2}\\d{10}$", message = "El número de cuenta debe seguir el formato AA1234567890")
    private final String accountNumber;

    @NotNull(message = "El tipo de cuenta no puede ser nulo")
    private final AccountType type;

    @NotNull(message = "El estado de la cuenta no puede ser nulo")
    private AccountStatus status;

    @NotNull(message = "El saldo no puede ser nulo")
    @DecimalMin(value = "0.00", message = "El saldo no puede ser negativo")
    private BigDecimal balance;

    @NotNull(message = "La fecha de creación no puede ser nula")
    private final LocalDateTime createdAt;

    @NotNull(message = "La fecha de modificación no puede ser nula")
    private LocalDateTime updatedAt;

    private final List<Transaction> transactions;

    public Account(UUID id, UUID clientId, String accountNumber, AccountType type, BigDecimal initialBalance) {
        if (id == null) {
            throw new IllegalArgumentException("El ID de la cuenta no puede ser nulo");
        }
        if (clientId == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }
        if (accountNumber == null || !accountNumber.matches("^[A-Z]{2}\\d{10}$")) {
            throw new IllegalArgumentException("Número de cuenta inválido");
        }
        if (type == null) {
            throw new IllegalArgumentException("El tipo de cuenta no puede ser nulo");
        }
        if (initialBalance == null || initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }

        this.id = id;
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.type = type;
        this.status = AccountStatus.ACTIVE;
        this.balance = initialBalance;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.transactions = new ArrayList<>();
    }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo");
        }
        if (this.status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("No se puede depositar en una cuenta no activa");
        }
        this.balance = this.balance.add(amount);
        this.updatedAt = LocalDateTime.now();
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo");
        }
        if (this.status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("No se puede retirar de una cuenta no activa");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Saldo insuficiente para realizar el retiro");
        }
        this.balance = this.balance.subtract(amount);
        this.updatedAt = LocalDateTime.now();
    }

    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("La transacción no puede ser nula");
        }
        this.transactions.add(transaction);
    }

    public UUID getId() {
        return id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountType getType() {
        return type;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public void setStatus(AccountStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("El estado de la cuenta no puede ser nulo");
        }
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public enum AccountType {
        SAVINGS, CHECKING, FIXED_TERM
    }

    public enum AccountStatus {
        ACTIVE, BLOCKED, CLOSED
    }
}

// === ARCHIVO: src/main/java/com/bank/account/domain/model/Client.java ===
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

// === ARCHIVO: src/main/java/com/bank/account/domain/model/Transaction.java ===
package com.bank.account.domain.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private final UUID id;

    @NotNull(message = "El ID de la cuenta origen no puede ser nulo")
    private final UUID accountId;

    @NotNull(message = "El tipo de transacción no puede ser nulo")
    private final TransactionType type;

    @NotNull(message = "El monto no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El monto debe ser positivo")
    private final BigDecimal amount;

    @NotNull(message = "La descripción no puede ser nula")
    @Size(max = 200, message = "La descripción no puede exceder 200 caracteres")
    private final String description;

    @NotNull(message = "La fecha de la transacción no puede ser nula")
    private final LocalDateTime transactionDate;

    private final UUID relatedAccountId;

    public Transaction(UUID id, UUID accountId, TransactionType type, BigDecimal amount, String description) {
        this(id, accountId, type, amount, description, null);
    }

    public Transaction(UUID id, UUID accountId, TransactionType type, BigDecimal amount, String description, UUID relatedAccountId) {
        if (id == null) {
            throw new IllegalArgumentException("El ID de la transacción no puede ser nulo");
        }
        if (accountId == null) {
            throw new IllegalArgumentException("El ID de la cuenta no puede ser nulo");
        }
        if (type == null) {
            throw new IllegalArgumentException("El tipo de transacción no puede ser nulo");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        if (description == null || description.length() > 200) {
            throw new IllegalArgumentException("Descripción inválida");
        }

        this.id = id;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.transactionDate = LocalDateTime.now();
        this.relatedAccountId = relatedAccountId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public UUID getRelatedAccountId() {
        return relatedAccountId;
    }

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER_IN, TRANSFER_OUT
    }
}

// === ARCHIVO: src/main/java/com/bank/account/domain/port/in/CreateAccountUseCase.java ===
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

// === ARCHIVO: src/main/java/com/bank/account/domain/port/in/AccountManagementUseCase.java ===
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

// === ARCHIVO: src/main/java/com/bank/account/domain/port/out/AccountRepository.java ===
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

// === ARCHIVO: src/main/java/com/bank/account/infrastructure/adapter/persistence/JpaAccountRepository.java ===
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

// === ARCHIVO: src/main/java/com/bank/account/domain/exception/AccountException.java ===
package com.bank.account.domain.exception;

public class AccountException extends RuntimeException {
    private final String errorCode;
    private final String accountId;

    public AccountException(String message) {
        super(message);
        this.errorCode = "ACCOUNT_ERROR";
        this.accountId = null;
    }

    public AccountException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.accountId = null;
    }

    public AccountException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "ACCOUNT_ERROR";
        this.accountId = null;
    }

    public AccountException(String message, String errorCode, String accountId) {
        super(message);
        this.errorCode = errorCode;
        this.accountId = accountId;
    }

    public AccountException(String message, Throwable cause, String errorCode, String accountId) {
        super(message, cause);
        this.errorCode = errorCode;
        this.accountId = accountId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getAccountId() {
        return accountId;
    }

    public static AccountException accountNotFound(String accountId) {
        return new AccountException(
            "Cuenta no encontrada con ID: " + accountId,
            "ACCOUNT_NOT_FOUND",
            accountId
        );
    }

    public static AccountException invalidAccountStatus(String currentStatus, String requestedOperation) {
        return new AccountException(
            "No se puede realizar la operación '" + requestedOperation + 
            "' en una cuenta con estado: " + currentStatus,
            "INVALID_ACCOUNT_STATUS",
            null
        );
    }

    public static AccountException accountAlreadyExists(String accountNumber) {
        return new AccountException(
            "Ya existe una cuenta con el número: " + accountNumber,
            "ACCOUNT_ALREADY_EXISTS",
            null
        );
    }

    public static AccountException invalidOperation(String message) {
        return new AccountException(message, "INVALID_OPERATION", null);
    }
}

// === ARCHIVO: src/main/java/com/bank/account/domain/exception/InsufficientFundsException.java ===
package com.bank.account.domain.exception;

import java.math.BigDecimal;

public class InsufficientFundsException extends AccountException {
    private final BigDecimal currentBalance;
    private final BigDecimal requestedAmount;
    private final String accountNumber;

    public InsufficientFundsException(BigDecimal currentBalance, BigDecimal requestedAmount) {
        super(
            String.format(
                "Fondos insuficientes. Saldo actual: %.2f, Monto solicitado: %.2f",
                currentBalance,
                requestedAmount
            ),
            "INSUFFICIENT_FUNDS",
            null
        );
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
        this.accountNumber = null;
    }

    public InsufficientFundsException(BigDecimal currentBalance, BigDecimal requestedAmount, String accountNumber) {
        super(
            String.format(
                "Fondos insuficientes en cuenta %s. Saldo actual: %.2f, Monto solicitado: %.2f",
                accountNumber,
                currentBalance,
                requestedAmount
            ),
            "INSUFFICIENT_FUNDS",
            accountNumber
        );
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
        this.accountNumber = accountNumber;
    }

    public InsufficientFundsException(String message, BigDecimal currentBalance, BigDecimal requestedAmount) {
        super(message, "INSUFFICIENT_FUNDS", null);
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
        this.accountNumber = null;
    }

    public InsufficientFundsException(String message, BigDecimal currentBalance, BigDecimal requestedAmount, Throwable cause) {
        super(message, cause, "INSUFFICIENT_FUNDS", null);
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
        this.accountNumber = null;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getDeficit() {
        if (currentBalance == null || requestedAmount == null) {
            return BigDecimal.ZERO;
        }
        return requestedAmount.subtract(currentBalance);
    }

    public boolean canCoverAmount(BigDecimal amount) {
        if (currentBalance == null || amount == null) {
            return false;
        }
        return currentBalance.compareTo(amount) >= 0;
    }

    public static InsufficientFundsException create(BigDecimal currentBalance, BigDecimal requestedAmount) {
        return new InsufficientFundsException(currentBalance, requestedAmount);
    }

    public static InsufficientFundsException create(BigDecimal currentBalance, BigDecimal requestedAmount, String accountNumber) {
        return new InsufficientFundsException(currentBalance, requestedAmount, accountNumber);
    }
}

// === ARCHIVO: src/main/java/com/bank/account/domain/exception/ClientNotFoundException.java ===
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

// === ARCHIVO: src/main/java/com/bank/account/application/service/AccountService.java ===
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

// === ARCHIVO: src/main/java/com/bank/account/infrastructure/adapter/web/AccountController.java ===
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

// === ARCHIVO: src/main/java/com/bank/account/infrastructure/config/GlobalExceptionHandler.java ===
package com.bank.account.infrastructure.config;

import com.bank.account.domain.exception.AccountException;
import com.bank.account.domain.exception.ClientNotFoundException;
import com.bank.account.domain.exception.InsufficientFundsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorResponse> handleAccountException(AccountException ex, WebRequest request) {
        logger.error("Excepción de cuenta: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            request.getDescription(false).replace("uri=", ""),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientFundsException(
            InsufficientFundsException ex, WebRequest request) {
        logger.warn("Fondos insuficientes: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            ex.getMessage(),
            request.getDescription(false).replace("uri=", ""),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClientNotFoundException(
            ClientNotFoundException ex, WebRequest request) {
        logger.error("Cliente no encontrado: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            request.getDescription(false).replace("uri=", ""),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        logger.warn("Error de validación: {}", ex.getMessage());
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
            .collect(Collectors.toMap(
                FieldError::getField,
                error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Valor inválido",
                (existing, replacement) -> existing
            ));
        ValidationErrorResponse errorResponse = new ValidationErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Error de validación en los datos proporcionados",
            request.getDescription(false).replace("uri=", ""),
            LocalDateTime.now(),
            errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        logger.error("Argumento ilegal: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            request.getDescription(false).replace("uri=", ""),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        logger.error("Excepción no manejada: ", ex);
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error interno del servidor. Contacte al administrador.",
            request.getDescription(false).replace("uri=", ""),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    public record ErrorResponse(
        int status,
        String message,
        String path,
        LocalDateTime timestamp
    ) {}

    public record ValidationErrorResponse(
        int status,
        String message,
        String path,
        LocalDateTime timestamp,
        Map<String, String> errors
    ) {}
}

// === ARCHIVO: src/test/java/com/bank/account/application/service/AccountServiceTest.java ===
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
```
