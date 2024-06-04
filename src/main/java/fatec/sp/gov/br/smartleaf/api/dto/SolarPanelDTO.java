package fatec.sp.gov.br.smartleaf.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SolarPanelDTO {
    private Long id;
    private String model;
    private String brand;
    private BigDecimal price;
}
