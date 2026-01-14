package com.demo.mcctiendademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MccTiendaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MccTiendaDemoApplication.class, args);
    }
    /*
        -Crear un control de expciones
        -Usar validation en los dts de resquest
        -Ver Poblacion de informacion
        -Ver Queries con jpa
     */

    /*
        -ver Invocacion de Procedure
        -ver Spring Pageable
        -ver Seguiridad con jwt
     */

}
