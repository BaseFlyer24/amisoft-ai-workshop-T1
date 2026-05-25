package cl.amisoft.workshop.service;

import cl.amisoft.workshop.model.Task;
import cl.amisoft.workshop.model.TaskStatus;
import cl.amisoft.workshop.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class TaskServiceTest {

    private TaskRepository repository;
    private TaskService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(TaskRepository.class);
        service = new TaskService(repository);
    }

    @Test
    void findAll_returnsRepositoryResult() {
        Task t = Task.builder().id(1L).title("foo").status(TaskStatus.TODO).build();
        when(repository.findAll()).thenReturn(List.of(t));

        List<Task> result = service.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("foo");
    }

    // ----------------------------------------------------------------------
    // CEILING — los devs escriben aqui los tests de findByStatus en el hands-on.
    // Pedir a Gemini Code Assist (chat lateral) que sugiera tests, evaluar
    // criticamente y agregar los que falten:
    //   - status valido en mayusculas
    //   - case-insensitive (in_progress)
    //   - status invalido -> lista vacia
    //   - null / blank -> lista vacia (sin tocar repository)
    // ----------------------------------------------------------------------
}
