package fatec.sp.gov.br.smartleaf.api.openapi;


import fatec.sp.gov.br.smartleaf.api.model.SolarPanelModel;
import fatec.sp.gov.br.smartleaf.api.model.SolarPanelStatsModel;
import fatec.sp.gov.br.smartleaf.domain.model.SolarPanel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Solar Panel")
public interface SolarPanelControllerOpenApi {

    @Operation(summary = "Get a List of all Solar Panels")
    ResponseEntity<Iterable<SolarPanelModel>> findAllSolarPanels();

    @Operation(summary = "Get a specific Solar Panel")
    ResponseEntity<SolarPanel> findSolarPanelById(Long id);

    @Operation(summary = "Get stats of a specific Solar Panel")
    ResponseEntity<SolarPanelStatsModel> getSolarPanelStats(Long id, double kwh);

    @Operation(summary = "Create a Solar Panel Entity")
    ResponseEntity<SolarPanel> createSolarPanel(SolarPanel solarPanel);

    @Operation(summary = "Update a specific Solar Panel")
    ResponseEntity<SolarPanel> updateSolarPanel(Long id, SolarPanel solarPanel);

    @Operation(summary = "Delete a specific Solar Panel")
    ResponseEntity<Object> deleteSolarPanel(Long id);

}
