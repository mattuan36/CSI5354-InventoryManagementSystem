package com.baylor.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class InventoryApp
{
    public static void main(String[] args) {
        SpringApplication.run(InventoryApp.class, args);
    }
}
