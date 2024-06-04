package fatec.sp.gov.br.smartleaf.api.dto_mapper;

import fatec.sp.gov.br.smartleaf.api.dto.SolarPanelStatsDTO;
import fatec.sp.gov.br.smartleaf.domain.model.SolarPanel;
import fatec.sp.gov.br.smartleaf.domain.service.SolarPanelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SolarPanelStatsMapper {

    private final ModelMapper modelMapper;
    private final SolarPanelService solarPanelService;

    public SolarPanelStatsMapper(ModelMapper modelMapper, SolarPanelService solarPanelService) {
        this.modelMapper = modelMapper;
        this.solarPanelService = solarPanelService;
    }

    public SolarPanelStatsDTO toModel(SolarPanel solarPanel, double kwh) {
        var mappedSolarPanel = modelMapper.map(solarPanel, SolarPanelStatsDTO.class);
        var stats = solarPanelService.getSolarPanelStats(solarPanel, kwh);
        mappedSolarPanel.setStats(stats);
        return mappedSolarPanel;
    }
}
