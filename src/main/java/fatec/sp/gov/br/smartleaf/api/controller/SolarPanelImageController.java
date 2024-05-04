package fatec.sp.gov.br.smartleaf.api.controller;

import fatec.sp.gov.br.smartleaf.api.dto.FotoSolarPanelDTO;
import fatec.sp.gov.br.smartleaf.api.dto_mapper.FotoSolarPanelMapper;
import fatec.sp.gov.br.smartleaf.domain.model.FotoSolarPanel;
import fatec.sp.gov.br.smartleaf.domain.model.input.FotoProdutoInput;
import fatec.sp.gov.br.smartleaf.domain.service.SolarPanelImageService;
import fatec.sp.gov.br.smartleaf.domain.service.SolarPanelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/panels/{id}/foto")
@RequiredArgsConstructor
public class SolarPanelImageController {

    private final SolarPanelImageService solarPanelImageService;
    private final FotoSolarPanelMapper fotoSolarPanelMapper;
    private final SolarPanelService solarPanelService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoSolarPanelDTO getSolarPanelImageJSON(@PathVariable Long id) {
        FotoSolarPanel foto = solarPanelImageService.getImageJSONOrException(id);
        return fotoSolarPanelMapper.toModel(foto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<InputStreamResource> getSolarPanelImage(
            @PathVariable Long id, @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
        return solarPanelImageService.getImageOrException(id, acceptHeader);
    }

    // Esse endpoint apenas aceita o content-type do tipo multipart form data
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoSolarPanelDTO updateSolarPanelImage(@PathVariable Long id, @Valid FotoProdutoInput fotoProdutoInput) throws IOException {
        FotoSolarPanel foto = fotoProdutoInput.formatTo(solarPanelService.getSolarPanelOrException(id));
        FotoSolarPanel savedFoto = solarPanelImageService.save(foto, fotoProdutoInput.arquivoInputStream());
        return fotoSolarPanelMapper.toModel(savedFoto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteSolarPanelImage(@PathVariable Long id) {
        solarPanelImageService.delete(id);
    }
}
