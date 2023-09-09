package com.example.nanitesting;



import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitcoinPriceApiResponse {
    private Map<String, Double> bpi;

    public BitcoinPriceApiResponse() {
    }

    public Map<String, Double> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, Double> bpi) {
        this.bpi = bpi;
    }

    @JsonAnySetter
    public void setDynamicProperty(String key, Double value) {
        if (bpi == null) {
            bpi = new HashMap<>();
        }
        bpi.put(key, value);
    }
}
