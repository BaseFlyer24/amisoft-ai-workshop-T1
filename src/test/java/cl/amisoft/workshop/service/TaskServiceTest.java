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
    // Tests CEILING — los devs piden a Gemini que los escriba en el hands-on.
    // En la branch `start` estos tests no existen; se agregan durante el taller.
    // Lo que sigue son tests de referencia para la branch `final`.
    // ----------------------------------------------------------------------

    @Test
    void findByStatus_withValidUppercaseStatus_returnsMatchingTasks() {
        Task t = Task.builder().id(1L).title("a").status(TaskStatus.TODO).build();
        when(repository.findByStatus(TaskStatus.TODO)).thenReturn(List.of(t));

        List<Task> result = service.findByStatus("TODO");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getStatus()).isEqualTo(TaskStatus.TODO);
    }

    @Test
    void findByStatus_isCaseInsensitive() {
        Task t = Task.builder().id(1L).title("a").status(TaskStatus.IN_PROGRESS).build();
        when(repository.findByStatus(TaskStatus.IN_PROGRESS)).thenReturn(List.of(t));

        List<Task> result = service.findByStatus("in_progress");

        assertThat(result).hasSize(1);
    }

    @Test
    void findByStatus_withInvalidStatus_returnsEmptyList() {
        List<Task> result = service.findByStatus("FOO");

        assertThat(result).isEmpty();
        Mockito.verifyNoInteractions(repository);
    }

    @Test
    void findByStatus_withNull_returnsEmptyList() {
        List<Task> result = service.findByStatus(null);

        assertThat(result).isEmpty();
        Mockito.verifyNoInteractions(repository);
    }

    @Test
    void findByStatus_withBlank_returnsEmptyList() {
        List<Task> result = service.findByStatus("   ");

        assertThat(result).isEmpty();
        Mockito.verifyNoInteractions(repository);
    }
}
