package com.example.demo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XmlProcessor implements Processor {
    ProducerTemplate producer;

    public XmlProcessor(ProducerTemplate producer) {
        this.producer = producer;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        String message = exchange.getIn().getBody(String.class);
        exchange.getIn().setBody(message);
    }
}
