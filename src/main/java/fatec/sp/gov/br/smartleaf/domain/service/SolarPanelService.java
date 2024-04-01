package fatec.sp.gov.br.smartleaf.domain.service;

import fatec.sp.gov.br.smartleaf.domain.exception.SolarPanelNaoEncontradoException;
import fatec.sp.gov.br.smartleaf.domain.model.SolarPanel;
import fatec.sp.gov.br.smartleaf.domain.repository.SolarPanelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class SolarPanelService {

    public static final double K_WH_PRICE = 0.92;
    private final SolarPanelRepository solarPanelRepository;

    public SolarPanelService(SolarPanelRepository solarPanelRepository) {
        this.solarPanelRepository = solarPanelRepository;
    }

    public SolarPanel save(SolarPanel solarPanel) {
        return solarPanelRepository.save(solarPanel);
    }

    public Map<String, Object> getStats(SolarPanel solarPanel, double kwh) {
        Map<String, Object> stats = new HashMap<>();

        var solarPanelPrice = solarPanel.getPrice();
        var maximumPower = solarPanel.getMaximumPower();
        var efficiency = solarPanel.getEfficiency();
        var sunIrradiation = 4.93;

        var panelsNeeded = getPanelsNeeded(maximumPower, efficiency, sunIrradiation, kwh);
        var estimatedPrice = getEstimatedPrice(panelsNeeded, solarPanelPrice);
        var returnOfInvestment = getReturnOfInvestment(estimatedPrice, getsolarPanelMonthlyEnergy(
                maximumPower, efficiency, sunIrradiation
        ));

        stats.put("solarPanel", solarPanel);
        stats.put("panelsNeeded", panelsNeeded);
        stats.put("estimatedPrice", estimatedPrice);
        stats.put("returnOfInvestment", returnOfInvestment);

        return stats;
    }

    public SolarPanel update(Long id, SolarPanel solarPanel) {
        var currentSolarPanel = getSolarPanelOrException(id);
        BeanUtils.copyProperties(solarPanel, currentSolarPanel, "id");
        return solarPanelRepository.save(currentSolarPanel);
    }

    public void delete(Long id) {
        solarPanelRepository.deleteById(id);
    }

    public SolarPanel getSolarPanelOrException(Long id) {
        return solarPanelRepository.findById(id)
                .orElseThrow(() -> new SolarPanelNaoEncontradoException(id));
    }


    private BigDecimal getEstimatedPrice(BigDecimal panelsNeeded, BigDecimal price) {
        return BigDecimal.valueOf(panelsNeeded.doubleValue() * price.doubleValue())
                .setScale(2, RoundingMode.FLOOR);
    }


    private BigDecimal getPanelsNeeded(Integer maximumPower, Integer efficiency, double sunIrradiation, double kwh) {
        var solarPanelMonthlyEnergy = getsolarPanelMonthlyEnergy(maximumPower, efficiency, sunIrradiation);

        return BigDecimal.valueOf(kwh / solarPanelMonthlyEnergy)
                .setScale(2, RoundingMode.FLOOR);
    }

    private BigDecimal getReturnOfInvestment(BigDecimal investment, double solarPanelMonthlyEnergy) {
        var economy = K_WH_PRICE * solarPanelMonthlyEnergy * 12;
        var returnOfInvestment = investment.doubleValue() / economy;

        return BigDecimal.valueOf(returnOfInvestment).setScale(2, RoundingMode.FLOOR);
    }

    private double getsolarPanelMonthlyEnergy(Integer maximumPower, Integer efficiency, double sunIrradiation) {
        var solarPanelDailyEnergy = maximumPower * (efficiency / 100.0);
        return ((solarPanelDailyEnergy * sunIrradiation) * 30) / 100.0;
    }
}
