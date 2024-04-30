package fatec.sp.gov.br.smartleaf.api.dto_mapper;

import fatec.sp.gov.br.smartleaf.api.dto.FotoSolarPanelDTO;
import fatec.sp.gov.br.smartleaf.domain.model.FotoSolarPanel;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FotoSolarPanelMapper {

    private final ModelMapper modelMapper;

    public FotoSolarPanelDTO toModel(FotoSolarPanel fotoSolarPanel) {
        return modelMapper.map(fotoSolarPanel, FotoSolarPanelDTO.class);
    }
}
