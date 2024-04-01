package fatec.sp.gov.br.smartleaf.local_run;

import fatec.sp.gov.br.smartleaf.SmartLeafApplication;
import fatec.sp.gov.br.smartleaf.domain.service.SolarPanelService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class Teste {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(SmartLeafApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        SolarPanelService solarPanelService = applicationContext.getBean(SolarPanelService.class);

        var solarPanel = solarPanelService.getSolarPanelOrException(1L);
        var kwh = 600.0;

        var maximumPower = solarPanel.getMaximumPower();
        var efficiency = solarPanel.getEfficiency();
        var sunIrradiation = 4.93;

        var solarPanelDailyEnergy = maximumPower * (efficiency / 100.0);
        var solarPanelMonthlyEnergy = ((solarPanelDailyEnergy * sunIrradiation) * 30) / 100.0;
        System.out.println(solarPanelMonthlyEnergy);

//        var needed = BigDecimal.valueOf(kwh / solarPanelMonthlyEnergy)
//                .setScale(2, RoundingMode.FLOOR);
//
//        System.out.println(needed);


    }
}
