package fatec.sp.gov.br.smartleaf.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class SolarPanel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "maximum_power", nullable = false)
    private Integer maximumPower;

    @Column(name = "efficiency", nullable = false)
    private Integer efficiency;

    @Column(name = "panel_type", nullable = false)
    private String panelType;

    @Column(name = "price", nullable = false)
    private BigDecimal price;
}
