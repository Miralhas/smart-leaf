package fatec.sp.gov.br.smartleaf.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SolarPanelInput {

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @Positive
    @NotNull
    private Integer maximumPower;

    @Positive
    @NotNull
    private Integer efficiency;

    @NotBlank
    private String panelType;

    @Positive
    @NotNull
    private BigDecimal price;
}
