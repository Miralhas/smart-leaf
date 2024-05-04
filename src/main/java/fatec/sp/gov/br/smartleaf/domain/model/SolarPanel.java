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

    @NotBlank(message = "Marca Inválida: Marca em branco")
    @Column(name = "brand")
    private String brand;

    @NotBlank(message = "Modelo Inválido: Modelo em branco")
    @Column(name = "model")
    private String model;

    @Positive(message = "Poder Máximo inválido: Menor ou igual a zero")
    @NotNull(message = "Poder Máximo inválido: Valor Nulo")
    @Column(name = "maximum_power")
    private Integer maximumPower;

    @Positive(message = "Eficiência inválida: Menor ou igual a zero")
    @NotNull(message = "Eficiência inválida: Valor Nulo")
    @Column(name = "efficiency")
    private Integer efficiency;

    @NotBlank(message = "Tipo de Painel Inválido: Tipo de painel em branco")
    @Column(name = "panel_type")
    private String panelType;

    @Positive(message = "Eficiência inválida: Menor ou igual a zero")
    @NotNull(message = "Preço Inválido: Valor Nulo")
    @Column(name = "price")
    private BigDecimal price;

}
