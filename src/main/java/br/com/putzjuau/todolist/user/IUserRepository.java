package br.com.putzjuau.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

//Interface é um modelo/contrato dentro da API        //Qual classe estamos referenciado e que tipo de ID ela tem
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username); // fazendo um select buscando username
}
