package fatec.sp.gov.br.smartleaf.api.controller;


import fatec.sp.gov.br.smartleaf.api.dto.SolarPanelDTO;
import fatec.sp.gov.br.smartleaf.api.dto_mapper.SolarPanelCollectionMapper;
import fatec.sp.gov.br.smartleaf.api.dto_mapper.SolarPanelStatsMapper;
import fatec.sp.gov.br.smartleaf.api.dto.SolarPanelStatsDTO;
import fatec.sp.gov.br.smartleaf.api.openapi.SolarPanelControllerOpenApi;
import fatec.sp.gov.br.smartleaf.domain.model.SolarPanel;
import fatec.sp.gov.br.smartleaf.domain.repository.SolarPanelRepository;
import fatec.sp.gov.br.smartleaf.domain.service.SolarPanelService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/panels")
public class SolarPanelController implements SolarPanelControllerOpenApi {

    private final SolarPanelRepository solarPanelRepository;
    private final SolarPanelService solarPanelService;
    private final SolarPanelStatsMapper solarPanelStatsMapper;
    private final SolarPanelCollectionMapper solarPanelCollectionMapper;

    
    @GetMapping
    public ResponseEntity<Iterable<SolarPanelDTO>> findAllSolarPanels() {
        var panels = solarPanelCollectionMapper.toCollectionModel(solarPanelRepository.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(panels);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SolarPanel> findSolarPanelById(@PathVariable Long id) {
        var panel = solarPanelService.getSolarPanelOrException(id);
        return ResponseEntity.status(HttpStatus.OK).body(panel);
    }


    @GetMapping("/{id}/stats")
    public ResponseEntity<SolarPanelStatsDTO> getSolarPanelStats(@PathVariable Long id, @RequestParam("kwh") double kwh) {
        var panel =  solarPanelService.getSolarPanelOrException(id);
        var solarPanelStats = solarPanelStatsMapper.toModel(panel, kwh);
        return ResponseEntity.status(HttpStatus.OK).body(solarPanelStats);
    }


    @ApiResponses(value = @ApiResponse(responseCode = "201"))
    @PostMapping
    public ResponseEntity<SolarPanel> createSolarPanel(@Valid @RequestBody SolarPanel solarPanel) {
        var savedPanel = solarPanelService.save(solarPanel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPanel);
    }


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
