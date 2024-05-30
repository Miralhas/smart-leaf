package fatec.sp.gov.br.smartleaf.api.controller;


import fatec.sp.gov.br.smartleaf.api.dto.SolarPanelDTO;
import fatec.sp.gov.br.smartleaf.api.dto.SolarPanelStatsDTO;
import fatec.sp.gov.br.smartleaf.api.dto.input.SolarPanelInput;
import fatec.sp.gov.br.smartleaf.api.dto.input.SolarPanelWithImageInput;
import fatec.sp.gov.br.smartleaf.api.dto_mapper.SolarPanelCollectionMapper;
import fatec.sp.gov.br.smartleaf.api.dto_mapper.SolarPanelStatsMapper;
import fatec.sp.gov.br.smartleaf.api.dto_mapper.SolarPanelUnmapper;
import fatec.sp.gov.br.smartleaf.api.openapi.SolarPanelControllerOpenApi;
import fatec.sp.gov.br.smartleaf.domain.model.SolarPanel;
import fatec.sp.gov.br.smartleaf.domain.repository.SolarPanelRepository;
import fatec.sp.gov.br.smartleaf.domain.service.SolarPanelImageService;
import fatec.sp.gov.br.smartleaf.domain.service.SolarPanelService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/panels")
public class SolarPanelController implements SolarPanelControllerOpenApi {


    private final SolarPanelRepository solarPanelRepository;
    private final SolarPanelService solarPanelService;
    private final SolarPanelStatsMapper solarPanelStatsMapper;
    private final SolarPanelCollectionMapper solarPanelCollectionMapper;
    private final SolarPanelUnmapper solarPanelUnmapper;
    private final SolarPanelImageService solarPanelImageService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SolarPanelDTO> findAllSolarPanels() {
        return solarPanelCollectionMapper.toCollectionModel(solarPanelRepository.findAll());
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SolarPanel findSolarPanelById(@PathVariable Long id) {
        return solarPanelService.getSolarPanelOrException(id);
    }


    @GetMapping("/{id}/stats")
    @ResponseStatus(HttpStatus.OK)
    public SolarPanelStatsDTO getSolarPanelStats(@PathVariable Long id, @RequestParam("kwh") double kwh) {
        SolarPanel panel = solarPanelService.getSolarPanelOrException(id);
        return solarPanelStatsMapper.toModel(panel, kwh);
    }


    @ApiResponses(value = @ApiResponse(responseCode = "201"))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolarPanel createSolarPanel(@RequestBody @Valid SolarPanelInput solarPanelInput) {
        var solarPanel = solarPanelUnmapper.toDomainObject(solarPanelInput);
        return solarPanelService.save(solarPanel);
    }

    @ApiResponses(value = @ApiResponse(responseCode = "201"))
    @PostMapping(value = "/with-image")
    @ResponseStatus(HttpStatus.CREATED)
    public SolarPanel createSolarPanelWithImage(@ModelAttribute @Valid SolarPanelWithImageInput solarPanelWithImageInput) throws IOException {
        return solarPanelImageService.saveSolarPanelWithImage(solarPanelWithImageInput);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SolarPanel updateSolarPanel(@PathVariable Long id, @RequestBody @Valid SolarPanelInput solarPanelInput) {
        return solarPanelService.update(id, solarPanelInput);
    }


    @ApiResponses(value = @ApiResponse(responseCode = "204"))
    @DeleteMapping("/{id}")
    public void deleteSolarPanel(@PathVariable Long id) {
        solarPanelService.delete(id);
    }
}
