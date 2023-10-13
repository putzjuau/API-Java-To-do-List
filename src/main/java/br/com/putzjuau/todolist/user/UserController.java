package br.com.putzjuau.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

//Vamos definir que esse controller sera nossa camada que cuiudara do usuario
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired // falaando pro spring gerenciar todo o ciclo de vida disso
    private IUserRepository userRepository;

    // As informação deve vir da body da requisicao

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        // vamos verificar se o usuario existe
        var user = this.userRepository.findByUsername(userModel.getUsername());
        if (user != null) {
            System.out.println("Usuário já existe");
            // mensagem de Erro
            // status code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }
        var passwordHashred = BCrypt.withDefaults() // cryptografando a senha
                .hashToString(12, userModel.getPassword().toCharArray());// Deve transformar a senha em um array de char

        userModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }

}
