package cl.amisoft.workshop.service;

import cl.amisoft.workshop.model.Task;
import cl.amisoft.workshop.model.TaskStatus;
import cl.amisoft.workshop.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public Task save(Task task) {
        if (task.getCreatedAt() == null) {
            task.setCreatedAt(LocalDateTime.now());
        }
        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.TODO);
        }
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    /**
     * Devuelve todas las tareas con el estado indicado.
     * Si el estado provisto no es valido (no calza con ningun TaskStatus),
     * devuelve una lista vacia en vez de lanzar excepcion.
     *
     * Ejemplo:
     *   findByStatus("TODO")        -> tareas en estado TODO
     *   findByStatus("in_progress") -> tareas en estado IN_PROGRESS (case-insensitive)
     *   findByStatus("FOO")         -> lista vacia
     *
     * @param status nombre del estado (case-insensitive)
     * @return lista de tareas que coinciden con ese estado
     */
    public List<Task> findByStatus(String status) {
        // FLOOR — implementar en el hands-on del T1.
        //
        // Objetivo: devolver las tareas cuyo TaskStatus corresponda al string
        // recibido. Reglas (ver Javadoc de arriba):
        //   - status null o blank -> lista vacia
        //   - status que no calza con ningun TaskStatus -> lista vacia
        //   - case-insensitive (ej. "in_progress" debe matchear IN_PROGRESS)
        //   - cuando el status es valido, delegar en taskRepository.findByStatus(...)
        //
        // Sugerencia: tipear el nombre del metodo y dejar que Gemini Code Assist
        // proponga la implementacion via inline-completion. Despues evaluar
        // criticamente la sugerencia antes de aceptarla.
        throw new UnsupportedOperationException("findByStatus: implementar en el hands-on");
    }

    public List<Task> findByAssignee(String assignee) {
        return taskRepository.findByAssignee(assignee);
    }
}
