package com.bank.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.bank.account.infrastructure.config.AppProperties;
import com.bank.account.domain.port.out.AccountRepository;
import com.bank.account.application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bank.account.infrastructure.adapter.persistence")
@ComponentScan(basePackages = {"com.bank.account"})
@EnableConfigurationProperties(AppProperties.class)
@EnableAsync
public class AccountApplication {

    private static final Logger logger = LoggerFactory.getLogger(AccountApplication.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @PostConstruct
    public void init() {
        logger.info("Inicializando aplicación de gestión de cuentas bancarias");
        try {
            // Verificación de conexión con la base de datos
            accountRepository.count();
            logger.info("Conexión con la base de datos verificada exitosamente");

            // Inicialización de datos de prueba si es necesario
            initializeSampleData();
        } catch (Exception e) {
            logger.error("Error durante la inicialización de la aplicación", e);
            throw new RuntimeException("Fallo en la inicialización de la aplicación", e);
        }
    }

    private void initializeSampleData() {
        try {
            // Creación de datos de prueba para demostración
            if (accountRepository.count() == 0) {
                logger.info("Creando datos de prueba para cuentas bancarias");
                // En un entorno real, esto se manejaría con migraciones o scripts
                // Aquí se omite la implementación detallada para mantener el foco en la arquitectura
            }
        } catch (Exception e) {
            logger.warn("Error al crear datos de prueba", e);
        }
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .maxAge(3600);
            }
        };
    }
}