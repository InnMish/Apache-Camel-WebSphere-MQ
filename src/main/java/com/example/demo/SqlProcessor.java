package com.example.demo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class SqlProcessor implements Processor {
    ProducerTemplate producer;

    public SqlProcessor(ProducerTemplate producer) {
        this.producer = producer;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        List<Map<String, Object>> dataList = exchange.getIn().getBody(List.class);
        CustomerList customers = new CustomerList();
        customers.setCustomers(new ArrayList<>());

        for (Map<String, Object> data : dataList) {
            Customer customer = new Customer();
            customer.setCustomerId((Integer) data.get("customer_id"));
            customer.setCustomerName((String) data.get("customer_name"));
            customer.setCustomerPhone((Integer) data.get("customer_phone"));
            customers.getCustomers().add(customer);
        }
        exchange.getIn().setBody(customers);
    }
}
