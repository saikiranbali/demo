package com.example.nanitesting;

import java.util.List;

public class BitcoinPriceResponse {
    private List<BitcoinPriceEntry> bitcoinPrices;

    public BitcoinPriceResponse() {
    }

    public BitcoinPriceResponse(List<BitcoinPriceEntry> bitcoinPrices) {
        this.bitcoinPrices = bitcoinPrices;
    }

    public List<BitcoinPriceEntry> getBitcoinPrices() {
        return bitcoinPrices;
    }

    public void setBitcoinPrices(List<BitcoinPriceEntry> bitcoinPrices) {
        this.bitcoinPrices = bitcoinPrices;
    }
}
