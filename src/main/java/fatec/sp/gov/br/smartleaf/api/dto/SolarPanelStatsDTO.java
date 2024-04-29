package fatec.sp.gov.br.smartleaf.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SolarPanelStatsDTO {
    private Long id;
    private String brand;
    private String model;
    private Integer maximumPower;
    private Integer efficiency;
    private String panelType;
    private BigDecimal price;
    private StatsDTO stats;
}
