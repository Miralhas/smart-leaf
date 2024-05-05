package fatec.sp.gov.br.smartleaf.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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

    @Positive
    @NotNull
    @Column(name = "maximum_power", nullable = false)
    private Integer maximumPower;

    @Positive
    @NotNull
    @Column(name = "efficiency", nullable = false)
    private Integer efficiency;

    @NotBlank
    @Column(name = "panel_type", nullable = false)
    private String panelType;

    @Positive
    @NotNull
    @Column(name = "price", nullable = false)
    private BigDecimal price;
}
