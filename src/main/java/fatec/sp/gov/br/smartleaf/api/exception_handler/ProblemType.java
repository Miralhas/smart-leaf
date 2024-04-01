package fatec.sp.gov.br.smartleaf.api.exception_handler;


import lombok.Getter;

@Getter
public enum ProblemType {
    ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada"),
    ERRO_DE_NEGOCIO("Erro na regra de negócio"),
    MENSAGEM_INCOMPREENSIVEL( "Mensagem incompreensivel");

    private final String title;

    ProblemType(String title) {
        this.title = title;
    }
}
