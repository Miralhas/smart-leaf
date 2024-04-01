package fatec.sp.gov.br.smartleaf.domain.exception;

public abstract class EntidadeNaoEncontradaException extends BusinessException{

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
