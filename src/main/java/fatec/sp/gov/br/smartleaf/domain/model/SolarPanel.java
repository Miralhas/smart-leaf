package fatec.sp.gov.br.smartleaf.domain.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Marca em branco")
    @Column(name = "brand")
    private String brand;

    @NotBlank(message = "Modelo em branco")
    @Column(name = "model")
    private String model;

    @Positive(message = "Menor ou igual a zero")
    @NotNull(message = "Valor Nulo")
    @Column(name = "maximum_power")
    private Integer maximumPower;

    @Positive(message = "Menor ou igual a zero")
    @NotNull(message = "Valor Nulo")
    @Column(name = "efficiency")
    private Integer efficiency;

    @NotBlank(message = "Tipo de painel em branco")
    @Column(name = "panel_type")
    private String panelType;

    @Positive(message = "Menor ou igual a zero")
    @NotNull(message = "Valor Nulo")
    @Column(name = "price")
    private BigDecimal price;

}
