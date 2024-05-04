package fatec.sp.gov.br.smartleaf.domain.model.input;

import fatec.sp.gov.br.smartleaf.domain.model.FotoSolarPanel;
import fatec.sp.gov.br.smartleaf.domain.model.SolarPanel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


@Getter
@Setter
public class FotoProdutoInput {

    @NotNull
    private MultipartFile arquivo;

    @NotBlank
    private String descricao;

    public FotoSolarPanel formatTo(SolarPanel solarPanel) {
        var foto = new FotoSolarPanel();
        foto.setSolarPanel(solarPanel);
        foto.setDescricao(descricao);
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());
        foto.setContentType(arquivo.getContentType());

        return foto;
    }

    public InputStream arquivoInputStream() throws IOException {
        return arquivo.getInputStream();
    }
}
