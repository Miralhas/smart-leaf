package fatec.sp.gov.br.smartleaf.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SolarPanelStatsModel {
    private Long id;
    private String model;
    private Integer maximumPower;
    private Integer efficiency;
    private String panelType;
    private BigDecimal price;
    private StatsModel stats;
}
