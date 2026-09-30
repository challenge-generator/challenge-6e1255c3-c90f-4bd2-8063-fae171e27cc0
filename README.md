# Implementación de Patrones GRASP en un Sistema de Gestión de Cuentas

Debes aplicar al menos dos patrones GRASP en el diseño y desarrollo de un sistema de gestión de cuentas para un banco. Los patrones a aplicar incluyen Experto en Información, Creador, Controlador, Alta Cohesión y Bajo Acoplamiento, Polimorfismo, Fabricación Pura, Indirección y Variaciones Protegidas. El sistema debe manejar la creación, modificación y consulta de cuentas bancarias, asegurando que cada patrón se aplique correctamente y que el diseño resultante sea coherente y mantenible.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | aplicación de patrones grasp en el desarrollo de sistemas |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 20 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Exploración y Modelado Inicial

**Objetivo:** Comprender los requisitos del sistema y modelar las entidades y relaciones iniciales.

**Tiempo estimado:** 5 horas

**Instrucciones:**

- Identifica los actores y las operaciones clave en el dominio de gestión de cuentas bancarias.
- Modela las entidades principales (Cuenta, Cliente, Transacción) y sus relaciones.
- Define las reglas de negocio y los invariantes que deben mantenerse.

**Entregable:** Diagrama de clases inicial y documento de requisitos.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo los patrones GRASP pueden aplicarse a las entidades modeladas.
- Piensa en cómo cada entidad puede ser responsable de su propia información.

</details>

### Fase 2: Aplicación de Patrones GRASP

**Objetivo:** Aplicar al menos dos patrones GRASP en el diseño del sistema.

**Tiempo estimado:** 8 horas

**Instrucciones:**

- Selecciona al menos dos patrones GRASP y aplica cada uno a las entidades y relaciones modeladas en la fase anterior.
- Documenta cómo cada patrón se aplica y justifica tu elección.
- Identifica posibles mejoras en la cohesión y el acoplamiento del sistema.

**Entregable:** Documento de diseño con la aplicación de patrones GRASP y justificación.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo el patrón Experto en Información puede aplicarse a la entidad Cuenta.
- Piensa en cómo el patrón Creador puede ser utilizado para la creación de nuevas cuentas.

</details>

### Fase 3: Refactorización y Optimización

**Objetivo:** Refactorizar el diseño para mejorar la cohesión y reducir el acoplamiento.

**Tiempo estimado:** 5 horas

**Instrucciones:**

- Revisa el diseño actual y realiza refactorizaciones necesarias para mejorar la cohesión y reducir el acoplamiento.
- Documenta los cambios realizados y justifica cada decisión.
- Evalúa el impacto de las refactorizaciones en la mantenibilidad y escalabilidad del sistema.

**Entregable:** Documento de refactorización con justificación y evaluación de impacto.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo el patrón Alta Cohesión y Bajo Acoplamiento puede mejorar el diseño.
- Piensa en cómo el patrón Polimorfismo puede ser utilizado para manejar diferentes tipos de cuentas.

</details>

### Fase 4: Implementación y Verificación

**Objetivo:** Implementar el diseño refactorizado y verificar su correcto funcionamiento.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Implementa el diseño refactorizado en código.
- Verifica que el sistema cumple con los requisitos y que los patrones GRASP se han aplicado correctamente.
- Documenta cualquier desafío encontrado y cómo lo resolviste.

**Entregable:** Código implementado y documento de verificación.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo probar cada patrón GRASP aplicado para asegurar su correcto funcionamiento.
- Piensa en cómo manejar posibles errores o excepciones durante la implementación.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son los patrones GRASP y por qué son importantes en el diseño de sistemas?
- **paraQueSirve**: ¿Cómo se aplican los patrones GRASP en un sistema de gestión de cuentas bancarias?
- **comoSeUsa**: ¿Cómo se pueden identificar y corregir errores comunes en la aplicación de patrones GRASP?
- **erroresComunes**: ¿Cuáles son los errores comunes al aplicar patrones GRASP y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones de diseño implica la aplicación de patrones GRASP en un sistema?

## Criterios de Evaluacion

- Aplicación correcta de al menos dos patrones GRASP en el diseño del sistema.
- Documentación clara y justificada de la aplicación de patrones GRASP.
- Refactorización del diseño para mejorar la cohesión y reducir el acoplamiento.
- Verificación del correcto funcionamiento del sistema implementado.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
