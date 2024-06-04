package fatec.sp.gov.br.smartleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class SmartLeafApplication {

    public static Path getPath(String folderNameInsideResources) {
        String resourcesDir = System.getProperty("user.dir") + "/src/main/resources";
        return Path.of(resourcesDir + String.format("/%s", folderNameInsideResources));
    }

    public static void main(String[] args) {
        SpringApplication.run(SmartLeafApplication.class, args);
    }
}
