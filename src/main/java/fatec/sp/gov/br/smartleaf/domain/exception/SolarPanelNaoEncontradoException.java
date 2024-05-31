package fatec.sp.gov.br.smartleaf.domain.exception;

public class SolarPanelNaoEncontradoException extends EntidadeNaoEncontradaException{

    public SolarPanelNaoEncontradoException(Long id) {
        super(String.format("Painel Solar de código %d não foi encontrado", id));
    }

    public SolarPanelNaoEncontradoException(String name) {
        super(String.format("Painel Solar de nome %s não foi encontrado", name));
    }
}
