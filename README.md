# El Torneo de los Inmortales: la elección de los Dos Santos Supremos

Trabajo Práctico N° 2 de la materia **Paradigmas de Programación** (Comisión 01-2900) — Departamento de Ingeniería e Investigaciones Tecnológicas (DIIT), **Universidad Nacional de La Matanza (UNLaM)**.

---

## Descripción del Proyecto

Simulación orientada a objetos en **Java** de un torneo eliminatorio de combate por parejas entre cultivadores de distintas escuelas. Las parejas se enfrentan en rondas sucesivas hasta que una única pareja permanece en pie y sus integrantes son proclamados los **Dos Santos Supremos**.

El diseño prioriza el polimorfismo, el encapsulamiento, la elección justificada de colecciones y la extensibilidad sin modificar código existente (permitiendo agregar nuevos tipos de combatientes, técnicas, escuelas, equipamientos e interesados de eventos).

---

## Arquitectura del Sistema

El proyecto está organizado en paquetes según su responsabilidad dentro del dominio:

| Paquete | Clases principales | Responsabilidad |
| :--- | :--- | :--- |
| **`combatientes`** | `Combatiente`, `CultivadorDeLaEspada`, `MonjeDelCuerpoCelestial`, `HechiceroDelVacio`, `TipoCombatiente`, `Escuela`, `CreadorCombatientes` | Modela a los participantes, sus estadísticas vitales, inventario, aprendizaje de técnicas y preparación inicial según su escuela. |
| **`equipamiento`** | `Equipamiento`, `TipoEquipamiento`, `EspadaCelestial`, `ArmaduraDorada`, `AnilloEspiritual`, `SelloDelFenix` | Define los objetos equipables (máximo 3 por combatiente, sin repetir categoría) y sus bonificadores porcentuales. |
| **`tecnicas`** | `Accion`, `AtaqueBasico`, `Tecnica`, `TipoTecnica`, `CorteDeLosMilAmaneceres`, `MuroDelCuerpoDorado`, `DominioDeLaNada`, `RugidoDeLaBestiaPrimordial`, `MeditacionDelInmortal` | Modela las acciones ejecutables en combate, su costo energético y sus efectos sobre ejecutores y objetivos. |
| **`combate`** | `Pareja`, `Combate`, `CriterioAccion`, `CriterioDefecto` | Gestiona las parejas, la regla de uso compartido de técnicas entre compañeros y la simulación determinista de asaltos y turnos. |
| **`torneo`** | `Torneo`, `Ronda`, `Cruce` | Administra las inscripciones, valida la cantidad de participantes (4 u 8 parejas) y resuelve las llaves eliminatorias. |
| **`eventos`** | `PublicadorAcontecimiento`, `Interesado`, `Acontecimiento`, `InicioDeCombate`, `AccionRealizada`, `Eliminacion`, `ResultadoCombate`, `Proclamacion`, `HeraldoImperial`, `CronistaDeLaSecta`, `TablaDePosiciones`, `Posicion` | Implementa la comunicación desacoplada de hechos del torneo, el registro del historial, la tabla de posiciones y la presentación por pantalla. |
| **`excepciones`** | `TorneoException`, `InscripcionDuplicadaException`, `CantidadDeParejasInvalidaException`, `EquipamientoInvalidoException`, `TipoDeCombatienteDesconocidoException` | Jerarquía de excepciones no verificadas (`RuntimeException`) para proteger las invariantes y reglas del dominio. |

---

## Patrones de Diseño y Decisiones Técnicas

* **Observer (`PublicadorAcontecimiento`, `Interesado`, `Acontecimiento`):** Desacopla el motor del torneo y del combate de quienes consumen los eventos. El `HeraldoImperial` es la única clase que escribe en salida estándar (`PrintStream` configurable), mientras que el `CronistaDeLaSecta` registra el historial inmutable y actualiza la `TablaDePosiciones` mediante polimorfismo (`acontecimiento.aplicarA(tabla)`), sin utilizar `instanceof`.
* **Simple Factory (`CreadorCombatientes`):** Centraliza la instanciación de combatientes, asigna identificadores únicos autoincrementales y delega la configuración inicial en `Escuela.preparar(...)`.
* **Polimorfismo sin `instanceof`:** Las diferencias entre clases de combatientes (daño provocado, daño recibido y costo de energía) y entre equipamientos surgen de la sobrescritura de métodos en sus respectivas jerarquías.
* **Determinismo:** El orden de actuación (menor ID primero), la selección de objetivos (menor vida actual, desempate por menor ID), el orden de técnicas (`LinkedHashMap`) y el orden de notificación de interesados (`ArrayList`) garantizan que dos torneos configurados de igual manera produzcan exactamente el mismo resultado e historial.

---

## Parámetros y Constantes Definidas

### Estadísticas Base y Bonificadores de Combatientes
* **Vida inicial:** `150` puntos | **Energía inicial:** `300` puntos | **Usos por técnica en el torneo:** `5`
* **Cultivador de la Espada:** `+30%` de daño provocado (`0.30`).
* **Monje del Cuerpo Celestial:** `-25%` de daño recibido (`0.25`).
* **Hechicero del Vacío:** `-25%` de costo de energía al ejecutar técnicas (`0.25`).

### Equipamiento (Bonos Porcentuales)
* **Espada Celestial (`ARMA`):** `+30%` daño infligido (`0.30`).
* **Armadura Dorada (`ARMADURA`):** `-50%` daño recibido (`0.50`).
* **Anillo Espiritual (`ANILLO`):** `+25%` energía máxima (`0.25`).
* **Sello del Fénix (`SELLO`):** `+40%` vida máxima (`0.40`).

### Reglas de Combate por Defecto
* **Límite de asaltos por combate:** `100` asaltos.
* **Recuperación de energía por asalto:** `10` puntos (para combatientes en pie, sin superar el máximo).
* **Ataque básico:** `10` puntos de daño base, costo `0` de energía y usos ilimitados.

---

## Requisitos y Ejecución

* **Lenguaje:** Java 21 (Compliance Level `21`).
* **Framework de Pruebas:** JUnit 5 (Jupiter).

### Ejecución de Pruebas
Las pruebas unitarias y de integración están organizadas por paquete siguiendo la convención `ClaseTest` (pruebas aisladas) y `ClaseAClaseBTest` (integración entre módulos). Todas las verificaciones de combate y torneo se realizan inspeccionando el estado de los objetos o el historial del `CronistaDeLaSecta`, sin depender de la salida por consola.

---

## Integrantes — Grupo PI ($\pi$)

* **Máximo Andino**
* **Jeremias Octavio Eksztein Rolon**
* **Agustín Ezequiel Jaime García**
* **Camila Nicole Kobak**
* **Guillermo Damian Miranda**
* **Facundo Agustín Villar**

**Docentes:** Gasior, Federico Mauro — Lanzilota, Hernán Gabriel
