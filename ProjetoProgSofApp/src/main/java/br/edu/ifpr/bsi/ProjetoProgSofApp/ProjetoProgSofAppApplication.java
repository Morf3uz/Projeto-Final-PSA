package br.edu.ifpr.bsi.ProjetoProgSofApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class ProjetoProgSofAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjetoProgSofAppApplication.class, args);
    }
}