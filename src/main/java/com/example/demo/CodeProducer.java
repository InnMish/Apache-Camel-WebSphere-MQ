package com.example.demo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.cxf.message.MessageContentsList;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class CodeProducer implements Processor {
    ProducerTemplate producer;

    public CodeProducer(ProducerTemplate producer) {
        this.producer = producer;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        int code = exchange.getProperty("Code", Integer.class);
        String date = exchange.getProperty("On_date", String.class);
        MessageContentsList list = (MessageContentsList) exchange.getIn().getBody();
        GetCursOnDateResult result = (GetCursOnDateResult) list.get(0);
        StringBuilder builder = new StringBuilder();
        for (GetCursOnDateResult.ValuteData.ValuteCursOnDate valuteCursOnDate : result.diffgram.getValuteCursOnDate()) {
            if (valuteCursOnDate.vcode == code) {
                builder.append("Date: ").append(date)
                        .append(", Code: ").append(valuteCursOnDate.vcode)
                        .append(", Curs: ").append(valuteCursOnDate.vcurs)
                        .append(", Value: ").append(valuteCursOnDate.vname);

                System.out.println(builder);
            }
        }
        exchange.getIn().setBody(builder);
        //exchange.getIn().setBody(parsePAYLOAD(message, code, date));
    }

    public String parsePAYLOAD(String message, String code, String date)
            throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(message));
        Document doc = builder.parse(is);

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();
        return createMessage(doc, xpath, code, date);
    }

    private static String createMessage(Document doc, XPath xpath, String code, String date)
            throws XPathExpressionException {
        String data = "/*/*/*/ValuteData/ValuteCursOnDate[contains(Vcode,'" + code + "')]";
        StringBuilder builder = new StringBuilder();
        XPathExpression xPathExpr = xpath.compile(data);
        Object result = xPathExpr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        if (nodes.getLength() != 0) {
            for (int i = 0; i < nodes.getLength(); i++) {
                builder.append("Date: ").append(date)
                        .append(", Code: ").append(nodes.item(i).getChildNodes().item(3).getTextContent())
                        .append(", Curs: ").append(nodes.item(i).getChildNodes().item(2).getTextContent())
                        .append(", Value: ").append(nodes.item(i).getChildNodes().item(0).getTextContent());
            }
        } else {
            System.out.println("Here is nothing to print");
        }
        return builder.toString();
    }
}
