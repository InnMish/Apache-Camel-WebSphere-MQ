package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.*;

@SpringBootApplication
@ImportResource("file:SpringRouteContext.xml")
public class DemoApplication {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class, args);
        JAXBContext jaxbContext = JAXBContext.newInstance(CustomerList.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    }
}
