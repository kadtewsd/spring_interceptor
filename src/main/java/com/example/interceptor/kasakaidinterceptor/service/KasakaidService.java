package com.example.interceptor.kasakaidinterceptor.service;

import com.example.interceptor.kasakaidinterceptor.infrastructure.KasakaidClassTrace;
import com.example.interceptor.kasakaidinterceptor.infrastructure.KasakaidMethodTrace;
import org.springframework.stereotype.Service;

@Service
@KasakaidClassTrace
public class KasakaidService {
    public boolean fireClass() {
        return true;
    }

    @KasakaidMethodTrace
    public boolean fireMethod() {
        return true;
    }

    public boolean fireWithin() {
        return true;
    }
}
