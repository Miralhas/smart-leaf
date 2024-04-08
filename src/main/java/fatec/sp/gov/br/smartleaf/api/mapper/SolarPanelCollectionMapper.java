package fatec.sp.gov.br.smartleaf.api.mapper;

import fatec.sp.gov.br.smartleaf.api.model.SolarPanelModel;
import fatec.sp.gov.br.smartleaf.domain.model.SolarPanel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SolarPanelCollectionMapper {

    private final ModelMapper modelMapper;

    public SolarPanelCollectionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SolarPanelModel toModel(SolarPanel solarPanel) {
        return modelMapper.map(solarPanel, SolarPanelModel.class);
    }

    public Iterable<SolarPanelModel> toCollectionModel(List<SolarPanel> panels) {
        return panels.stream().map(this::toModel).collect(Collectors.toList());
    }
}
