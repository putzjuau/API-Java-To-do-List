package br.com.putzjuau.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.putzjuau.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // colocando para o spring gerenciar essa classe
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Vamos verificar se a rota que estamos buscando é a correta e a que queremos,
        // que no caso é a de tasks
        var servletPath = request.getServletPath();
        // ele comeca no tasks
        if (servletPath.startsWith("/tasks/")) { // tivemos que mudar e tirar o equals

            // pegar a autenticação (Usuario e senha)
            var Authorization = request.getHeader("Authorization");

            // extraindo os dados cryptografados
            // Tirando a palavra Basic //trim remove todo o espaco em branco
            var authEncoded = Authorization.substring("Basic".length()).trim();

            // vamos decodificar criando um array de byte
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            var authString = new String(authDecode);

            // criando um array e dividindo usuario e password
            String[] credentials = authString.split(":");
            String userName = credentials[0];
            String password = credentials[1];

            // validar o usuario
            var user = this.userRepository.findByUsername(userName);
            if (user == null) {
                response.sendError(401);
            } else {
                // validar a senha //convertendo para array
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) { // verified retorna um valor booleano
                    // trazendo o id
                    // vai pra frente
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }

            }

            // segue a viagem se tiver ok

        } else {
            filterChain.doFilter(request, response);
        }
    }

}
