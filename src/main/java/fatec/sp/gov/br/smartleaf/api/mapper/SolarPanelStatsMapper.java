package fatec.sp.gov.br.smartleaf.api.mapper;

import fatec.sp.gov.br.smartleaf.api.model.SolarPanelStatsModel;
import fatec.sp.gov.br.smartleaf.api.model.StatsModel;
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

    public SolarPanelStatsModel toModel(SolarPanel solarPanel, double kwh) {
        var mappedSolarPanel = modelMapper.map(solarPanel, SolarPanelStatsModel.class);
        var statsModel = new StatsModel();
        var stats = solarPanelService.getStats(solarPanel, kwh);
        statsModel.setPanelsNedeed(stats.get("panelsNeeded"));
        statsModel.setEstimatedPrice(stats.get("estimatedPrice"));
        statsModel.setReturnOfInvestment(stats.get("returnOfInvestment"));
        mappedSolarPanel.setStats(statsModel);
        return mappedSolarPanel;
    }
}
