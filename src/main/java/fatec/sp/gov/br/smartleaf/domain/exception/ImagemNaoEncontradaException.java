package fatec.sp.gov.br.smartleaf.domain.exception;

public class ImagemNaoEncontradaException extends BusinessException {

    public static final String MESSAGE = "Não foi possivel recuperar a imagem do painel solar de id %d";

    public ImagemNaoEncontradaException(Long id) {
        super(String.format(MESSAGE, id));
    }

    public ImagemNaoEncontradaException(Long id, Throwable cause) {
        super(String.format(MESSAGE, id), cause);
    }
}
