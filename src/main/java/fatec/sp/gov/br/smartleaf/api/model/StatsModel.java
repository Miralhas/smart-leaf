package fatec.sp.gov.br.smartleaf.api.model;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StatsModel {
    private BigDecimal panelsNedeed;
    private BigDecimal estimatedPrice;
    private BigDecimal returnOfInvestment;
}
