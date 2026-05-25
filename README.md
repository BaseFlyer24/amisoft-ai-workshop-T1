# Amisoft AI Workshop — Taller 1

Repo de ejercicio para el **Taller 1 — Fundamentos de la Programación con IA** del programa AI Coding Tools de Amisoft.

Este es un proyecto Spring Boot pequeño con un dominio neutro (gestión de tareas) que vas a usar durante el bloque de hands-on del taller. No toca el dominio judicial — es un sandbox.

---

## Stack

- **Java 11**
- **Spring Boot 2.7** (web + JPA + validation + Lombok)
- **Maven Wrapper** incluido (`mvnw`) — no necesitas tener Maven instalado en tu máquina
- **H2 en memoria** — sin setup de base de datos

---

## Pre-trabajo antes del taller

Tienes que llegar al Taller 1 con esto listo. Si llegas sin estos pasos hechos, vas a poder asistir y mirar la demo, pero no vas a poder participar del hands-on en código.

### 1. Instalar IntelliJ IDEA

Community o Ultimate, versión 2024.1 o superior. ([download](https://www.jetbrains.com/idea/download/))

### 2. Tener JDK 11

Si no lo tienes:
- macOS: `brew install openjdk@11`
- Linux: `sudo apt install openjdk-11-jdk`
- Windows: [Adoptium JDK 11](https://adoptium.net/temurin/releases/?version=11)

Verifica en IntelliJ: `File → Project Structure → SDKs`.

### 3. Instalar el plugin Gemini Code Assist

En IntelliJ:
1. `Settings → Plugins → Marketplace`
2. Buscar "Gemini Code Assist"
3. Install → Restart IDE
4. Abrir el panel lateral del plugin (ícono en la barra derecha) y hacer sign-in con tu **cuenta corporativa Gemini Pro**

### 4. Clonar este repo y verificar que compila

```bash
git clone https://github.com/BaseFlyer24/amisoft-ai-workshop-T1.git
cd amisoft-ai-workshop-T1
./mvnw clean compile
```

En Windows usa `mvnw.cmd` en lugar de `./mvnw`.

Tiene que terminar sin errores. Si falla acá, escríbenos antes del taller.

### 5. Abrir el proyecto en IntelliJ

`File → Open` y seleccionar la carpeta del proyecto. IntelliJ va a detectar Maven automáticamente y descargar dependencias. Espera a que termine de indexar.

### 6. Confirmar que estás listo

Cuando completes los 5 pasos, llena el form: **[link al form]**

Te va a pedir un screenshot de IntelliJ con el proyecto abierto y el panel de Gemini Code Assist visible.

**Deadline:** [día] antes de las 18:00.

---

## Qué vas a hacer en el taller

En el bloque de hands-on (~22 min cerca del final del taller) vas a:

- Usar Gemini Code Assist directamente en este proyecto
- Implementar un método sencillo con la ayuda del plugin
- Evaluar críticamente lo que te sugiere

No te adelantes — la idea es que llegues con el setup listo, no resuelto. El resto del taller (mental model, demos en vivo, casos prácticos) va a darle contexto al ejercicio.

---

## Si algo falla

**Antes del taller:**
- Avisa por el canal interno de Amisoft o escribe a Clemente directamente
- Prueba `./mvnw clean compile` desde la terminal antes de pelearte con IntelliJ — descarta problemas de Maven

**El día del taller:**
- La sala se abre 15 min antes del horario oficial específicamente para resolver problemas de setup
- Llega temprano si tu plugin no autenticó la noche anterior

---

## Estructura del proyecto

```
amisoft-ai-workshop-T1/
├── pom.xml
├── mvnw, mvnw.cmd
└── src/
    ├── main/
    │   ├── java/cl/amisoft/workshop/
    │   │   ├── WorkshopApplication.java     ← entry point
    │   │   ├── controller/TaskController.java
    │   │   ├── service/TaskService.java     ← acá vas a trabajar
    │   │   ├── model/Task.java
    │   │   ├── model/TaskStatus.java
    │   │   └── repository/TaskRepository.java
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/cl/amisoft/workshop/
            └── service/TaskServiceTest.java
```

Si quieres explorar antes, mira `TaskController` y `TaskService` para entender el dominio. No hace falta — el taller te lo introduce.

---

Nos vemos el miércoles.
