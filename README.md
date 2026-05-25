# Amisoft AI Workshop — T1 Repo de Ejemplo

Repo sintético para el hands-on del Taller 1 ("Fundamentos de la Programación con IA") del programa de AI coding tools para Amisoft.

**Stack:** Java 11 + Spring Boot 2.7.x + Maven. Dominio: API de gestión de tareas (Task Manager) — neutral por diseño, NO toca el dominio judicial para no quemar los tickets reales que vienen en T2/T3.

---

## Para Clemente (facilitador) — cómo usar este repo

### 1. Crear el repo en GitHub
Subir a un repo público (o privado al que los 25 devs tengan acceso) bajo la cuenta de JP o tuya. Sugiero público: simplifica el clone para los devs y el contenido es genérico, sin riesgo.

### 2. Crear las 3 branches

El estado actual del código equivale a la branch **`final`** (todo implementado, tests pasan).

```bash
# Estado completo
git checkout -b final
git push -u origin final

# Branch `start` — el que los devs clonan
git checkout -b start main
# Comentar el cuerpo de TaskService.findByStatus() (dejar el TODO)
# Comentar el cuerpo de TaskController.searchTasks() (dejar el TODO)
# Borrar TaskServiceTest.testFindByStatus_*
# Borrar TaskServiceTest.testSearchTasks_*
git commit -am "start: huecos para ejercicio T1"
git push -u origin start

# Branch `checkpoint-1` — punto de reseteo si alguien se atasca
git checkout -b checkpoint-1 final
# Eliminar solo los tests del ceiling, dejar las implementaciones
git commit -am "checkpoint-1: floor resuelto, ceiling pendiente"
git push -u origin checkpoint-1
```

Más sencillo aún: haz 3 versiones del proyecto a mano y cada una vivirá en su branch.

### 3. Pre-trabajo de los devs (48h antes)
Email les pide:
1. IntelliJ IDEA instalado
2. Plugin Gemini Code Assist instalado y autenticado con cuenta corporativa
3. JDK 11 instalado
4. Clone del repo, branch `start`, abrir en IntelliJ, verificar que importa Maven y compila

```bash
git clone <URL>
cd amisoft-ai-workshop-T1
git checkout start
./mvnw clean compile
```

Form de Google con un check "estoy listo" + screenshot del IDE con el proyecto abierto.

---

## Estructura

```
amisoft-ai-workshop-T1/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   └── java/cl/amisoft/workshop/
    │       ├── WorkshopApplication.java
    │       ├── controller/TaskController.java
    │       ├── service/TaskService.java
    │       ├── model/Task.java
    │       ├── model/TaskStatus.java
    │       └── repository/TaskRepository.java
    └── test/
        └── java/cl/amisoft/workshop/
            └── service/TaskServiceTest.java
```

---

## Diseño de los ejercicios

### Floor (todos completan) — `TaskService.findByStatus(String status)`
- Signature + Javadoc dados
- Body es un `// TODO: implementar usando inline-completion`
- Devs escriben el nombre del método y dejan que Gemini Code Assist autocomplete
- Variación esperada: filtrar la lista de tareas por status. Caso borde: status inválido.

### Ceiling (seniors o quien va rápido) — tests en `TaskServiceTest`
- Pedir al chat lateral que escriba tests para `findByStatus`
- Evaluar críticamente la sugerencia:
  - ¿Cubre status válidos e inválidos?
  - ¿Cubre lista vacía?
  - ¿Cubre case-sensitivity?
  - ¿Los assertions son específicos o genéricos?
- Discusión grupal corta al final del bloque sobre qué corrigieron.

### Demo del facilitador (Bloque 3, antes del hands-on)

**Para mostrar lectura/refactoring asistido:**
- `TaskController.createTask()` tiene un cuerpo deliberadamente feo (lógica anidada + validación cruda + magic strings). En vivo, pedirle al chat lateral:
  1. "Explícame qué hace este método"
  2. "Sugiere un refactor que mejore legibilidad"
- Verbalizar las decisiones: aceptar/rechazar la sugerencia, qué cambia, qué no.

**Para mostrar fallos en vivo (límites y alucinaciones):**
Tres provocaciones que casi siempre funcionan:

1. **API inventada:** pedir "usa la librería `com.amisoft.notifications` para enviar un evento cuando se cree la tarea". Esa librería no existe → Gemini probablemente la inventa con métodos creíbles. Punto: validar imports antes de aceptar.

2. **Lógica sutilmente mal:** pedir "agrega un método que devuelva las tareas vencidas (dueDate < hoy)". Frecuentemente Gemini olvida considerar zona horaria o null en `dueDate`. Punto: revisar edge cases.

3. **Sobre-confianza:** pedir "explícame qué hace `@Transactional(propagation = Propagation.NESTED)` en este service". Gemini responderá con seguridad pero a veces inventa comportamiento específico al provider (JPA vs JTA). Punto: la confianza del modelo ≠ verdad.

---

## Para el ejercicio de seguridad (Bloque 4)

No requiere código adicional. Mostrar 1 ejemplo concreto en vivo:
- Abrir un archivo con un `@Repository` y pedirle al chat "agrega un método que busque por título usando una query nativa con LIKE"
- Probable resultado: Gemini concatena el parámetro directamente → SQL injection
- Mostrar la versión correcta con `@Param` y `:title`
- Mini-discusión: ¿la responsabilidad de prevenir esto es del modelo o del dev?

---

## Tarea entre T1 y T2

Email el mismo día del T1:
> "Esta semana usen Gemini Code Assist en al menos 3 tareas reales de su sprint. Anoten:
> - 1 cosa que les sirvió (qué tipo de tarea, qué prompt usaron)
> - 1 cosa que les frustró (qué falló, qué intentaron)
> - 1 caso donde notaron un límite o alucinación (cómo lo detectaron)
>
> Las traemos al T2 — son input directo para el ejercicio sobre su ticket real."

---

## Próximos pasos (no incluidos en este repo)

- Mail de pre-trabajo para los 25 devs
- Mail corto a Nataly, Ignacio, Tomás invitándolos como mini-facilitadores
- Slide deck minimalista (5-6 slides solo)
- Mini-tabla Excalidraw lista para proyectar
