package com.dzone.example.security.oauth2.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/public")
    public String publica() {
        return "Pagina Publica";
    }

    @RequestMapping("/private")
    public String privada() {
        return "Pagina Privada";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "Pagina Administrator";
    }

}
