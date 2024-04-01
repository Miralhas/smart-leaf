package fatec.sp.gov.br.smartleaf.domain.exception;

public class SolarPanelNaoEncontradoException extends EntidadeNaoEncontradaException{

    public SolarPanelNaoEncontradoException(Long id) {
        super(String.format("Painel Solar de código %d não foi encontrado", id));
    }
}
