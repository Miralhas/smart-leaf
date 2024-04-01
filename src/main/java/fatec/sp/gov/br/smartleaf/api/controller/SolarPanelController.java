package fatec.sp.gov.br.smartleaf.api.controller;


import fatec.sp.gov.br.smartleaf.api.openapi.SolarPanelControllerOpenApi;
import fatec.sp.gov.br.smartleaf.domain.model.SolarPanel;
import fatec.sp.gov.br.smartleaf.domain.repository.SolarPanelRepository;
import fatec.sp.gov.br.smartleaf.domain.service.SolarPanelService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/panels")
public class SolarPanelController implements SolarPanelControllerOpenApi {

    private final SolarPanelRepository solarPanelRepository;
    private final SolarPanelService solarPanelService;

    public SolarPanelController(SolarPanelRepository solarPanelRepository, SolarPanelService solarPanelService) {
        this.solarPanelRepository = solarPanelRepository;
        this.solarPanelService = solarPanelService;
    }

//    @ApiResponses(value = @ApiResponse(responseCode = "200"))
    @GetMapping
    public ResponseEntity<Iterable<SolarPanel>> findAllSolarPanels() {
        var panels = solarPanelRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(panels);
    }

//    @ApiResponses(value = @ApiResponse(responseCode = "200"))
    @GetMapping("/{id}")
    public ResponseEntity<SolarPanel> findSolarPanelById(@PathVariable Long id) {
        var panel = solarPanelService.getSolarPanelOrException(id);
        return ResponseEntity.status(HttpStatus.OK).body(panel);
    }

//    @ApiResponses(value = @ApiResponse(responseCode = "200"))
    @GetMapping("/{id}/stats")
    public ResponseEntity<Map<String, Object>> getSolarPanelStats(@PathVariable Long id, @RequestParam("kwh") double kwh) {
        var panel =  solarPanelService.getSolarPanelOrException(id);
        var solarPanelStatsHashMap = solarPanelService.getStats(panel, kwh);
        return ResponseEntity.status(HttpStatus.OK).body(solarPanelStatsHashMap);
    }


    @ApiResponses(value = @ApiResponse(responseCode = "201"))
    @PostMapping
    public ResponseEntity<SolarPanel> createSolarPanel(@Valid @RequestBody SolarPanel solarPanel) {
        var savedPanel = solarPanelService.save(solarPanel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPanel);
    }

//    @ApiResponses(value = @ApiResponse(responseCode = "200"))
    @PutMapping("/{id}")
    public ResponseEntity<SolarPanel> updateSolarPanel(@Valid @PathVariable Long id, @RequestBody SolarPanel solarPanel) {
        var updatedPanel = solarPanelService.update(id, solarPanel);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPanel);
    }

    @ApiResponses(value = @ApiResponse(responseCode = "204"))
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSolarPanel(@Valid @PathVariable Long id) {
        solarPanelService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
