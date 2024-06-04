package fatec.sp.gov.br.smartleaf.api.controller;

import fatec.sp.gov.br.smartleaf.api.openapi.LoginControllerOpenApi;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginController implements LoginControllerOpenApi {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> authentication() {
        return Map.of("title", "Autorizado", "status", "200", "detail", "Usuario Autenticado com sucesso!");
    }

}