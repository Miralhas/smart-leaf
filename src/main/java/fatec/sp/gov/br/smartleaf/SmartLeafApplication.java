package fatec.sp.gov.br.smartleaf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;

@SpringBootApplication
public class SmartLeafApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartLeafApplication.class, args);
    }

}
