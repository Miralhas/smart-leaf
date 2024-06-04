package fatec.sp.gov.br.smartleaf.api.dto.input;

import fatec.sp.gov.br.smartleaf.domain.model.FotoSolarPanel;
import fatec.sp.gov.br.smartleaf.domain.model.SolarPanel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class SolarPanelWithImageInput {

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @Positive
    @NotNull
    private Integer maximumPower;

    @Positive
    @NotNull
    private Integer efficiency;

    @NotBlank
    private String panelType;

    @Positive
    @NotNull
    private BigDecimal price;

    @NotNull
    private MultipartFile arquivo;

//    @NotBlank
//    private String descricao;


    public SolarPanel formatToSolarPanel() {
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setBrand(brand);
        solarPanel.setPanelType(panelType);
        solarPanel.setModel(model);
        solarPanel.setPrice(price);
        solarPanel.setEfficiency(efficiency);
        solarPanel.setMaximumPower(maximumPower);
        return solarPanel;
    }

    public FotoSolarPanel formatToImage(SolarPanel solarPanel) {
        var foto = new FotoSolarPanel();
        foto.setSolarPanel(solarPanel);
        foto.setDescricao(arquivo.getOriginalFilename() + " image");
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());
        foto.setContentType(arquivo.getContentType());

        return foto;
    }

    public InputStream arquivoInputStream() throws IOException {
        return arquivo.getInputStream();
    }
}
