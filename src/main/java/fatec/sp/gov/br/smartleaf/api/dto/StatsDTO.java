package fatec.sp.gov.br.smartleaf.api.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StatsDTO {
    private BigDecimal panelsNedeed;
    private BigDecimal estimatedPrice;
    private BigDecimal returnOfInvestment;
}
