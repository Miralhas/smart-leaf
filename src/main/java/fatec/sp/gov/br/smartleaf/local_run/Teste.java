package fatec.sp.gov.br.smartleaf.local_run;

import fatec.sp.gov.br.smartleaf.SmartLeafApplication;
import fatec.sp.gov.br.smartleaf.domain.repository.SolarPanelRepository;
import fatec.sp.gov.br.smartleaf.domain.service.SolarPanelService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Teste {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(SmartLeafApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
