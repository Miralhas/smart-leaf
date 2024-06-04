package fatec.sp.gov.br.smartleaf.api.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoSolarPanelDTO {
    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}
