package project.social;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SocialMediaApplication {

    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        return () -> 0;
    }

    public static void main(String[] args) {
        SpringApplication.run(SocialMediaApplication.class, args);
    }
}
