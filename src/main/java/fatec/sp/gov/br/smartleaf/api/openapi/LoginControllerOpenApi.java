package fatec.sp.gov.br.smartleaf.api.openapi;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;

@Tag(name = "Login")
public interface LoginControllerOpenApi {
    Map<String, String> authentication();
}
