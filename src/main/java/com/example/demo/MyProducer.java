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
        exchange.setProperty("On_date", currency.getDate());
        exchange.getIn().setBody(currency.getDate().toString());
    }

    private String getSoap(String date) {
        return
                "      <web:GetCursOnDate xmlns:web=\"http://web.cbr.ru/\">\n" +
                        "         <web:On_date>" + date + "</web:On_date>\n" +
                        "      </web:GetCursOnDate>";
    }
}
