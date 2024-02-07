package com.crece.crece.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class DemoController {

    @GetMapping
    public String saludo(){
        return "hola desde un endpoint autenticado";
    }
}
