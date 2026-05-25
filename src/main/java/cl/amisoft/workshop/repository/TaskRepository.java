package cl.amisoft.workshop.repository;

import cl.amisoft.workshop.model.Task;
import cl.amisoft.workshop.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByAssignee(String assignee);
}
