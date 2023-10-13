package br.com.putzjuau.todolist.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ITaskRespository extends JpaRepository<TaskModel, UUID> {
    List<TaskModel> findByIdUser(UUID idUser); // traz uma lista de taskmodel baseado no usuario
}
