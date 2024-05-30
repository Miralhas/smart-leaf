package fatec.sp.gov.br.smartleaf.domain.service;

import fatec.sp.gov.br.smartleaf.api.dto.input.SolarPanelWithImageInput;
import fatec.sp.gov.br.smartleaf.domain.exception.ImagemDefaultException;
import fatec.sp.gov.br.smartleaf.domain.exception.ImagemNaoEncontradaException;
import fatec.sp.gov.br.smartleaf.domain.model.FotoSolarPanel;
import fatec.sp.gov.br.smartleaf.domain.model.SolarPanel;
import fatec.sp.gov.br.smartleaf.domain.repository.SolarPanelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SolarPanelImageService {

    public static final String DEFAULT_IMAGE_FILENAME = "default.jpg";
    public static final String DEFAULT_IMAGE_MEDIA_TYPE = "image/jpg";

    private final SolarPanelRepository solarPanelRepository;
    private final SolarPanelService solarPanelService;
    private final FotoStorageService fotoStorageService;

    public ResponseEntity<InputStreamResource> getImage(Long id, String acceptHeader)
            throws HttpMediaTypeNotAcceptableException {
        try {
            FotoSolarPanel foto = getImageJSONOrException(id);
            List<MediaType> providedMediaTypes = MediaType.parseMediaTypes(acceptHeader);

            MediaType mediaTypeFoto = MediaType.parseMediaType(foto.getContentType());
            verificarCompatibilidadeMediaType(mediaTypeFoto, providedMediaTypes);

            InputStream fotoInputStream = fotoStorageService.recuperar(foto.getNomeArquivo());

            return ResponseEntity.ok()
                    .contentType(mediaTypeFoto)
                    .body(new InputStreamResource(fotoInputStream));
        } catch (ImagemNaoEncontradaException e) {
            // Caso a entidade não tenha uma imagem, retornamos uma default.
            InputStream foto = fotoStorageService.recuperar(DEFAULT_IMAGE_FILENAME);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(DEFAULT_IMAGE_MEDIA_TYPE))
                    .body(new InputStreamResource(foto));
        }
    }


    @Transactional
    public SolarPanel saveSolarPanelWithImage(SolarPanelWithImageInput solarPanelWithImageInput) throws IOException {
        var solarPanel = solarPanelWithImageInput.formatToSolarPanel();
        var fotoSolarPanel = solarPanelWithImageInput.formatToImage(solarPanel);
        solarPanel = solarPanelService.save(solarPanel);
        save(fotoSolarPanel, solarPanelWithImageInput.arquivoInputStream());
        return solarPanel;
    }


    public FotoSolarPanel getImageJSONOrException(Long id) {
        solarPanelService.getSolarPanelOrException(id);
        return solarPanelRepository.findFotoById(id)
                .orElseThrow(() -> new ImagemNaoEncontradaException(id));
    }

    @Transactional
    public FotoSolarPanel save(FotoSolarPanel foto, InputStream dadosArquivo) {
        String novoNomeFoto = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());
        String nomeArquivoExistente = null;
        foto.setNomeArquivo(novoNomeFoto);

        // Caso já tenha uma imagem presente nessa entidade Painel Solar, removemos ela.
        Optional<FotoSolarPanel> fotoExistente = solarPanelRepository.findFotoById(foto.getSolarPanelId());
        if (fotoExistente.isPresent()) {
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            // Removendo do Banco de Dados
            solarPanelRepository.deleteImage(fotoExistente.get());
            solarPanelRepository.flush();
        }

        foto = solarPanelRepository.saveImage(foto);
        solarPanelRepository.flush();

        var novaFoto = FotoStorageService.NovaFoto.builder()
                .nomeArquivo(foto.getNomeArquivo())
                .inputStream(dadosArquivo)
                .build();

        // Adicionando a nova no armazenamento local e caso tenha uma antiga removemos ela.
        fotoStorageService.substituir(novaFoto, nomeArquivoExistente);

        return foto;
    }

    @Transactional
    public void delete(Long id) {
        FotoSolarPanel fotoSolarPanel = getImageJSONOrException(id);
        // Previnir que uma imagem default seja apagada.
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
