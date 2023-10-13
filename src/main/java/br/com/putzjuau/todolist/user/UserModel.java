package br.com.putzjuau.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

//lib de java para n precisar usar metodos getters e setters visiveis - Pois ela usa getters e setters

/*
 * Caso eu queira apenas os Getters:
 * 
 * @Getter
 * 
 * Caso eu queira apenas os Setters:
 * 
 * @Setters
 */

@Data
// deixando claro pro database
@Entity(name = "tb_users")
public class UserModel {

    @Id // deixando claro que esse sera o ID da tb
    @GeneratedValue(generator = "UUID") // Deixando claro pro jakarta que ele pode gerenciar e que o tipo do gerador é
                                        // UUID
    private UUID id; // UUID é um identificador unico/ Mais seguro do que colocar um ID SEQUENCIAL;

    /* @Getter Definindo acima do atributo, define apenas para ele */
    @Column(unique = true) // validacao de username, deixando claro que username é unico
    private String username;
    private String name;
    @Column()
    private String password;

    @CreationTimestamp // Pega a informação de quando o dado foi gerado
    private LocalDateTime createdAt;

}
