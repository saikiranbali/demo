package com.example.nanitesting;

import com.example.nanitesting.service.BitcoinPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype" )
public class Alien {
    @Autowired
    private BitcoinPriceService bitcoinPriceService;

    public void fetchBitcoinPrices(String startDate, String endDate, String outputCurrency) {
        // Call the BitcoinPriceService to fetch historical Bitcoin prices
        BitcoinPriceResponse bitcoinPriceResponse = bitcoinPriceService.fetchBitcoinPrices(startDate, endDate, outputCurrency).block();

        // Process the response and display results
        if (bitcoinPriceResponse != null) {
            System.out.println("Bitcoin Prices:");
            for (BitcoinPriceEntry entry : bitcoinPriceResponse.getBitcoinPrices()) {
                System.out.println("Date: " + entry.getDate());
                System.out.println("Price: " + entry.getPrice());
                if (entry.isHighest()) {
                    System.out.println("(high)");
                } else if (entry.isLowest()) {
                    System.out.println("(low)");
                }
                System.out.println("Currency: " + entry.getCurrency());
                System.out.println();
            }
        } else {
            System.out.println("Failed to fetch Bitcoin prices.");
        }
    }
}
