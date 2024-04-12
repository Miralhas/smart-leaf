package fatec.sp.gov.br.smartleaf.domain.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class SolarPanel {

    @Schema(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Column(name = "brand", nullable = false)
    private String brand;

    @NotBlank
    @Column(name = "model", nullable = false)
    private String model;

    @NotNull
    @Column(name = "maximum_power", nullable = false)
    private Integer maximumPower;

    @NotNull
    @Column(name = "efficiency", nullable = false)
    private Integer efficiency;

    @NotBlank
    @Column(name = "panel_type", nullable = false)
    private String panelType;

    @NotNull
    @Column(name = "price", nullable = false)
    private BigDecimal price;

}
