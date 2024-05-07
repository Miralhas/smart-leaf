package fatec.sp.gov.br.smartleaf.api.exception_handler;


import lombok.Getter;

import java.net.URI;

@Getter
public enum ProblemType {
    ERRO_DE_NEGOCIO("Erro na regra de negócio", "https://localhost:8080/errors/erro-de-negocio"),
    ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada", "https://localhost:8080/errors/entidade-nao-encontrada"),
    IMAGEM_NAO_ENCONTRADA("Imagem não encontrada", "https://localhost:8080/errors/imagem-nao-encontrada"),
    MENSAGEM_INCOMPREENSIVEL("Mensagem incompreensivel", "https://localhost:8080/errors/"),
    CAMPO_INVALIDO("Campos Inválidos", "https://localhost:8080/errors/campos-invalidos"),
    ERRO_DE_SISTEMA("Erro de Sistema", "https://localhost:8080/errors/erro-de-sistema");

    private final String title;
    private final URI type;

    ProblemType(String title, String type) {
        this.title = title;
        this.type = URI.create(type);
    }
}
