
package com.mycompany.petstoreserviceapp;

import org.apache.camel.CamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        // Iniciar la aplicaci�n Spring Boot y obtener el contexto de aplicaci�n
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        try {
            // Obtener el contexto de Camel desde la aplicaci�n Spring Boot
            CamelContext camelContext = context.getBean(CamelContext.class);

            // Esperar durante 20 segundos (o el tiempo que desees)
            Thread.sleep(20000);
        } finally {
            // La aplicaci�n Spring Boot se detendr� autom�ticamente al finalizar
            context.close();
        }
    }
}


  

