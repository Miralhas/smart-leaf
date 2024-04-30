package fatec.sp.gov.br.smartleaf.domain.model;

import fatec.sp.gov.br.smartleaf.domain.exception.SolarPanelNaoEncontradoException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class FotoSolarPanel {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "solar_panel_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private SolarPanel solarPanel;

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;

    public Long getSolarPanelId() {
        if (solarPanel != null) {
            return solarPanel.getId();
        }
        return null;
    }
}
