package cl.amisoft.workshop.controller;

import cl.amisoft.workshop.model.Task;
import cl.amisoft.workshop.model.TaskStatus;
import cl.amisoft.workshop.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAll(@RequestParam(required = false) String status,
                             @RequestParam(required = false) String assignee) {
        if (status != null) {
            return taskService.findByStatus(status);
        }
        if (assignee != null) {
            return taskService.findByAssignee(assignee);
        }
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        Optional<Task> task = taskService.findById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crea una tarea nueva.
     *
     * Este metodo esta deliberadamente feo — logica anidada, validacion cruda,
     * magic strings. Sirve para la demo del facilitador en el Bloque 3 del T1:
     * pedirle al chat lateral que lo explique y proponga un refactor.
     */
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        if (task != null) {
            if (task.getTitle() != null) {
                if (!task.getTitle().isBlank()) {
                    if (task.getTitle().length() <= 200) {
                        if (task.getStatus() == null) {
                            task.setStatus(TaskStatus.TODO);
                        } else {
                            if (task.getStatus() != TaskStatus.TODO
                                    && task.getStatus() != TaskStatus.IN_PROGRESS
                                    && task.getStatus() != TaskStatus.BLOCKED
                                    && task.getStatus() != TaskStatus.DONE) {
                                return ResponseEntity.badRequest().body("estado invalido");
                            }
                        }
                        Task saved = taskService.save(task);
                        return ResponseEntity.ok(saved);
                    } else {
                        return ResponseEntity.badRequest().body("titulo muy largo");
                    }
                } else {
                    return ResponseEntity.badRequest().body("titulo en blanco");
                }
            } else {
                return ResponseEntity.badRequest().body("titulo es obligatorio");
            }
        } else {
            return ResponseEntity.badRequest().body("body vacio");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint de busqueda libre — actualmente solo un stub.
     *
     * Este metodo esta intencionalmente sin implementar para el ejercicio "ceiling"
     * extendido (si sobra tiempo en el hands-on). La idea es pedirle al chat
     * lateral de Gemini que sugiera una implementacion que filtre tareas por
     * varios criterios opcionales (status + assignee + texto en title).
     */
    @GetMapping("/search")
    public List<Task> searchTasks(@RequestParam(required = false) String q,
                                  @RequestParam(required = false) String status,
                                  @RequestParam(required = false) String assignee) {
        // TODO: ejercicio opcional — implementar busqueda combinada
        return List.of();
    }
}
