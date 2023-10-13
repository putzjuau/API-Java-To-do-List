package br.com.putzjuau.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    /*
     * Id
     * Usuario (ID_USUARIO)
     * Descriçao
     * Titulo
     * Data de Inicio
     * Data termino
     * Prioridade
     * 
     */
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    @Column(length = 50) // deixando um tamanho maximo de caracteres pro title
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @Column(length = 50)
    private String priority;

    // atrelar o id do usuario
    private UUID idUser;

    @CreationTimestamp // define quando foi criado
    private LocalDateTime createdAt;

    // criando uma propria customização

    public void setTitle(String title) throws Exception {
        if (title.length() > 50) {
            throw new Exception("O campo title deve conter no máximo 50 caracteres");
        }
        this.title = title;
    }
}
