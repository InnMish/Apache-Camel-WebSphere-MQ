package com.example.demo;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GetCursOnDateResult {
    @XmlAnyElement
    protected Object schema;
    @XmlElement(name="diffgram",namespace="urn:schemas-microsoft-com:xml-diffgram-v1")
    protected ValuteData diffgram;

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ValuteData {
        @XmlElementWrapper(name = "ValuteData")
        @XmlElement(name = "ValuteCursOnDate")
        protected List<ValuteCursOnDate> valuteCursOnDate;

        public List<ValuteCursOnDate> getValuteCursOnDate() {
            if (valuteCursOnDate == null) {
                valuteCursOnDate = new ArrayList<ValuteCursOnDate>();
            }
            return this.valuteCursOnDate;
        }

        public static class ValuteCursOnDate {
            @XmlElement(name = "Vname")
            protected String vname;
            @XmlElement(name = "Vnom")
            protected BigDecimal vnom;
            @XmlElement(name = "Vcurs")
            protected BigDecimal vcurs;
            @XmlElement(name = "Vcode")
            protected Integer vcode;
            @XmlElement(name = "VchCode")
            protected String vchCode;
        }
    }
}
