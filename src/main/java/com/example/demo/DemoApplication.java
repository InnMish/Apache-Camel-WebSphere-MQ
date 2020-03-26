package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.*;

@SpringBootApplication
@ImportResource("file:SpringRouteContext.xml")
public class DemoApplication {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class, args);

    }
}
