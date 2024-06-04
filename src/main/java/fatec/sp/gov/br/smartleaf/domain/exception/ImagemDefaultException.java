package fatec.sp.gov.br.smartleaf.domain.exception;

public class ImagemDefaultException extends BusinessException {

    public static final String MESSAGE = "Não é possível apagar uma imagem default";

    public ImagemDefaultException() {
        super(MESSAGE);
    }

    public ImagemDefaultException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
