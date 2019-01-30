package com.example.interceptor.kasakaidinterceptor.controller;

import com.example.interceptor.kasakaidinterceptor.service.KasakaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/first")
public class KasakaidRestController {

    @Autowired
    private KasakaidService service;

    @GetMapping("/class")
    public Map<String, String> fireClass() {
        service.fireClass();
        return new HashMap<String, String>() {
            {
                put("type", "class");
            }
        };
    }

    @GetMapping("/method")
    public Map<String, String> fireMethod() {
        service.fireMethod();
        return new HashMap<String, String>() {
            {
                put("type", "method");
            }
        };
    }

    @GetMapping("/within")
    public Map<String, String> fireWithin() {
        service.fireWithin();
        return new HashMap<String, String>() {
            {
                put("type", "within");
            }
        };
    }
}
