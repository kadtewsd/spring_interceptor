package com.example.interceptor.kasakaidinterceptor.infrastructure;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface KasakaidMethodTrace {
}
