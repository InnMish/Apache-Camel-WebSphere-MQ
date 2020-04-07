package com.example.demo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;

public class MyProducer implements Processor {
    ProducerTemplate producer;

    public MyProducer(ProducerTemplate producer) {
        this.producer = producer;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Currency currency = exchange.getIn().getBody(Currency.class);
        exchange.setProperty("Code", currency.getCode());
        exchange.setProperty("Date", currency.getDate());
        exchange.getIn().setBody(getSoap(currency.getDate().toString()));
    }

    private String getSoap(String date) {
        return
                "         <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://web.cbr.ru/\">\n" +
                        "   <soapenv:Header/>\n" +
                        "   <soapenv:Body>\n" +
                        "      <web:GetCursOnDate>\n" +
                        "         <web:On_date>" + date + "</web:On_date>\n" +
                        "      </web:GetCursOnDate> </soapenv:Body>\n" +
                        "</soapenv:Envelope>";
    }
}
