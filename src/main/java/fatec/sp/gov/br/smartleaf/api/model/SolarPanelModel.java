package fatec.sp.gov.br.smartleaf.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SolarPanelModel {
    private Long id;
    private String model;
    private BigDecimal price;
//    private Integer maximumPower;
//    private Integer efficiency;
//    private String panelType;
}
