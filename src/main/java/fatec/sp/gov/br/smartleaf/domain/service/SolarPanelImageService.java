package fatec.sp.gov.br.smartleaf.domain.service;

import fatec.sp.gov.br.smartleaf.domain.exception.ImagemDefaultException;
import fatec.sp.gov.br.smartleaf.domain.exception.ImagemNaoEncontradaException;
import fatec.sp.gov.br.smartleaf.domain.model.FotoSolarPanel;
import fatec.sp.gov.br.smartleaf.domain.repository.SolarPanelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SolarPanelImageService {

    public static final String DEFAULT_IMAGE_FILENAME = "default.jpg";
    private final SolarPanelRepository solarPanelRepository;
    private final SolarPanelService solarPanelService;
    private final FotoStorageService fotoStorageService;

    public ResponseEntity<InputStreamResource> getImageOrException(Long id, String acceptHeader) throws HttpMediaTypeNotAcceptableException {
        FotoSolarPanel foto = getImageJSONOrException(id);

        List<MediaType> providedMediaTypes = MediaType.parseMediaTypes(acceptHeader);

        MediaType mediaTypeFoto = MediaType.parseMediaType(foto.getContentType());
        verificarCompatibilidadeMediaType(mediaTypeFoto, providedMediaTypes);

        InputStream fotoInputStream = fotoStorageService.recuperar(foto.getNomeArquivo());

        return ResponseEntity.ok()
                .contentType(mediaTypeFoto)
                .body(new InputStreamResource(fotoInputStream));

    }

    public FotoSolarPanel getImageJSONOrException(Long id) {
        solarPanelService.getSolarPanelOrException(id);
        return solarPanelRepository.findFotoById(id)
                .orElseThrow(() -> new ImagemNaoEncontradaException(id));
    }

    public FotoSolarPanel save(FotoSolarPanel foto, InputStream dadosArquivo) {
        String novoNomeFoto = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());
        String nomeArquivoExistente = null;
        foto.setNomeArquivo(novoNomeFoto);

        // Caso já tenha uma imagem presente nessa entidade Painel Solar, removemos ela.
        Optional<FotoSolarPanel> fotoExistente = solarPanelRepository.findFotoById(foto.getSolarPanelId());
        if (fotoExistente.isPresent()) {
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            solarPanelRepository.deleteImage(fotoExistente.get());
        }

        foto = solarPanelRepository.saveImage(foto);
        solarPanelRepository.flush();

        var novaFoto = FotoStorageService.NovaFoto.builder()
                .nomeArquivo(foto.getNomeArquivo())
                .inputStream(dadosArquivo)
                .build();

        fotoStorageService.substituir(novaFoto, nomeArquivoExistente);

        return foto;
    }

    public void delete(Long id) {
        FotoSolarPanel fotoSolarPanel = getImageJSONOrException(id);

        // Previnir que uma imagem default seja apagada.
        if (fotoSolarPanel.getNomeArquivo().equals(DEFAULT_IMAGE_FILENAME)) {
            throw new ImagemDefaultException();
        }
        solarPanelRepository.deleteImage(fotoSolarPanel);
        fotoStorageService.remover(fotoSolarPanel.getNomeArquivo());
    }

    private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, List<MediaType> providedMediaTypes)
            throws HttpMediaTypeNotAcceptableException {
        boolean compativel = providedMediaTypes.stream()
                .anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));
        if (!compativel) {
            throw new HttpMediaTypeNotAcceptableException(List.of(mediaTypeFoto));
        }
    }

}
