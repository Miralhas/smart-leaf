package fatec.sp.gov.br.smartleaf.api.openapi;


import fatec.sp.gov.br.smartleaf.api.dto.SolarPanelDTO;
import fatec.sp.gov.br.smartleaf.api.dto.SolarPanelStatsDTO;
import fatec.sp.gov.br.smartleaf.api.dto.input.SolarPanelInput;
import fatec.sp.gov.br.smartleaf.domain.model.SolarPanel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Solar Panel")
public interface SolarPanelControllerOpenApi {

    @Operation(summary = "Get a List of all Solar Panels")
    List<SolarPanelDTO> findAllSolarPanels();

    @Operation(summary = "Get a specific Solar Panel")
    SolarPanel findSolarPanelById(Long id);

    @Operation(summary = "Get stats of a specific Solar Panel")
    SolarPanelStatsDTO getSolarPanelStats(Long id, double kwh);

    @Operation(summary = "Create a Solar Panel Entity")
    SolarPanel createSolarPanel(SolarPanelInput solarPanelInput);

    @Operation(summary = "Update a specific Solar Panel")
    SolarPanel updateSolarPanel(Long id, SolarPanelInput solarPanelInput);

    @Operation(summary = "Delete a specific Solar Panel")
    void deleteSolarPanel(Long id);

}
