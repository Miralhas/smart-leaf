package fatec.sp.gov.br.smartleaf.api.openapi;

import fatec.sp.gov.br.smartleaf.api.dto.FotoSolarPanelDTO;
import fatec.sp.gov.br.smartleaf.api.dto.input.FotoProdutoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.IOException;

@Tag(name = "Solar Panel Image")
public interface SolarPanelImageControllerOpenApi {

    @Operation(summary = "Get a specific Solar Panel Image JSON value")
    FotoSolarPanelDTO getSolarPanelImageJSON(@PathVariable Long id);


    @Operation(summary = "Get a specific Solar Panel Image (image/*)")
    ResponseEntity<InputStreamResource> getSolarPanelImage(
            @PathVariable Long id, @RequestHeader(name = "accept") String acceptHeader
    ) throws HttpMediaTypeNotAcceptableException;


    @Operation(summary = "Update a specific Solar Panel Image")
    FotoSolarPanelDTO updateSolarPanelImage(
            @PathVariable Long id, @Valid FotoProdutoInput fotoProdutoInput
    ) throws IOException;


    @Operation(summary = "Delete a specific Solar Panel Image")
    void deleteSolarPanelImage(@PathVariable Long id);
}
