package fatec.sp.gov.br.smartleaf.api.dto_mapper;

import fatec.sp.gov.br.smartleaf.api.dto.input.SolarPanelInput;
import fatec.sp.gov.br.smartleaf.domain.model.SolarPanel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SolarPanelUnmapper {

    private final ModelMapper modelMapper;

    public SolarPanel toDomainObject(SolarPanelInput solarPanelInput) {
        return modelMapper.map(solarPanelInput, SolarPanel.class);
    }

    public void copyToDomainObject(SolarPanelInput solarPanelInput, SolarPanel solarPanel) {
        modelMapper.map(solarPanelInput, solarPanel);
    }

}
