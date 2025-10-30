package com.delivery_api.Projeto.Delivery.API.controller;

import org.springframework.web.bind.annotation.GetMapping; //importa @GetMapping do Spring para mapear requisições HTTP GET
import org.springframework.web.bind.annotation.RestController; //importa @RestController do Spring para indicar que essa classe é um controlador REST

import java.time.LocalDateTime;
import java.util.Map;

@RestController //indica que essa classe é um controlador REST
public class HealthController {

    @GetMapping("/health") //vai fazer um chamdo para verificar se a API esta funcionando
    public Map<String, String> health() { //metodo que retorna o status da API
        return Map.of( //retorna um mapa com o status da API
                        "status", "UP", //indica que a API esta funcionando
                        "timestamp", LocalDateTime.now().toString(), //retorna a data e hora atual
                        "Service", "Delivery API" //nome doservico
        );

    }
}
//codigo base pra api funfar
//essee codigo eé como um controlador de saude para uma API de entrega. Ele tem um endpoint "/health" que retorna um mapa com o status da API, a data e hora atual, e o nome do serviço. Isso é útil para monitorar se a API está funcionando corretamente.
//colocar em nuvem dew x em x tempo e limitar para restartar dps????

//localhost:8080/health  - p teste