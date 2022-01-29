package nl.hva.c25.team1.digivault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DigivaultApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigivaultApplication.class, args);
    }
}
