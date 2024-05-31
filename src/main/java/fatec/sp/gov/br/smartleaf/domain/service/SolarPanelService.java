package fatec.sp.gov.br.smartleaf.domain.service;

import fatec.sp.gov.br.smartleaf.api.dto.StatsDTO;
import fatec.sp.gov.br.smartleaf.api.dto.input.SolarPanelInput;
import fatec.sp.gov.br.smartleaf.api.dto_mapper.SolarPanelUnmapper;
import fatec.sp.gov.br.smartleaf.domain.exception.SolarPanelNaoEncontradoException;
import fatec.sp.gov.br.smartleaf.domain.model.SolarPanel;
import fatec.sp.gov.br.smartleaf.domain.repository.SolarPanelRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Transactional(readOnly = true)
public class SolarPanelService {

    public static final double K_WH_PRICE = 0.92;
    public static final double SUN_IRRADIATION = 4.93;

    private final SolarPanelRepository solarPanelRepository;
    private final SolarPanelImageService solarPanelImageService;
    private final SolarPanelUnmapper solarPanelUnmapper;

    public SolarPanelService(SolarPanelRepository solarPanelRepository,
                             @Lazy SolarPanelImageService solarPanelImageService, SolarPanelUnmapper solarPanelUnmapper) {
        this.solarPanelRepository = solarPanelRepository;
        this.solarPanelImageService = solarPanelImageService;
        this.solarPanelUnmapper = solarPanelUnmapper;
    }

    @Transactional
    public SolarPanel save(SolarPanel solarPanel) {
        solarPanel = solarPanelRepository.save(solarPanel);
        return solarPanel;
    }


    public StatsDTO getSolarPanelStats(SolarPanel solarPanel, double kwh) {
        StatsDTO solarPanelStats = new StatsDTO();

        var solarPanelPrice = solarPanel.getPrice();
        var maximumPower = solarPanel.getMaximumPower();
        var efficiencyPercentage = solarPanel.getEfficiency();

        var panelsNeeded = getPanelsNeeded(maximumPower, efficiencyPercentage, kwh);
        var estimatedPrice = getEstimatedPrice(panelsNeeded, solarPanelPrice);
        var returnOfInvestment = getReturnOfInvestment(estimatedPrice, getsolarPanelMonthlyEnergy(
                maximumPower, efficiencyPercentage
        ));

        solarPanelStats.setPanelsNedeed(Math.ceil(panelsNeeded.doubleValue()));
        solarPanelStats.setEstimatedPrice(estimatedPrice);
        solarPanelStats.setReturnOfInvestment(returnOfInvestment);

        return solarPanelStats;
    }

    @Transactional
    public SolarPanel update(Long id, SolarPanelInput solarPanelInput) {
        var currentSolarPanel = getSolarPanelOrException(id);
        solarPanelUnmapper.copyToDomainObject(solarPanelInput, currentSolarPanel);
        return solarPanelRepository.save(currentSolarPanel);
    }

    @Transactional
    public void delete(Long id) {
        try {
            solarPanelRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            solarPanelImageService.delete(id);
            solarPanelRepository.flush();
            solarPanelRepository.deleteById(id);
        }
    }

    public SolarPanel getSolarPanelOrException(Long id) {
        return solarPanelRepository.findById(id)
                .orElseThrow(() -> new SolarPanelNaoEncontradoException(id));
    }


    public SolarPanel getSolarPanelByNameOrException(String name) {
        return solarPanelRepository.findSolarPanelByModelNameContaining(name)
                .orElseThrow(() -> new SolarPanelNaoEncontradoException(name));
    }


    private BigDecimal getEstimatedPrice(BigDecimal panelsNeeded, BigDecimal price) {
        return BigDecimal.valueOf(panelsNeeded.doubleValue() * price.doubleValue())
                .setScale(2, RoundingMode.FLOOR);
    }


    private BigDecimal getPanelsNeeded(Integer maximumPower, Integer efficiencyPercentage, double kwh) {
        var solarPanelMonthlyEnergy = getsolarPanelMonthlyEnergy(maximumPower, efficiencyPercentage);

        System.out.println(solarPanelMonthlyEnergy);

        return BigDecimal.valueOf(kwh / solarPanelMonthlyEnergy)
                .setScale(2, RoundingMode.FLOOR);
    }

    private BigDecimal getReturnOfInvestment(BigDecimal investment, double solarPanelMonthlyEnergy) {
        var economy = K_WH_PRICE * solarPanelMonthlyEnergy * 12;
        var returnOfInvestment = investment.doubleValue() / economy;

        return BigDecimal.valueOf(returnOfInvestment).setScale(2, RoundingMode.FLOOR);
    }

    private double getsolarPanelMonthlyEnergy(Integer maximumPower, Integer efficiencyPercentage) {
        var solarPanelDailyEnergy = maximumPower * (efficiencyPercentage / 100.0);
        return ((solarPanelDailyEnergy * SUN_IRRADIATION) * 30) / 100.0;
    }

}
