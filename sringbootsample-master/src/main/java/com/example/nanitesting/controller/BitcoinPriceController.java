package com.example.nanitesting.controller;

import com.example.nanitesting.BitcoinPriceResponse;
import com.example.nanitesting.service.BitcoinPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class BitcoinPriceController {
    private final BitcoinPriceService bitcoinPriceService;

    @Autowired
    public BitcoinPriceController(BitcoinPriceService bitcoinPriceService) {
        this.bitcoinPriceService = bitcoinPriceService;
    }

    @GetMapping("/bitcoin-prices")
    public Mono<BitcoinPriceResponse> getBitcoinPrices(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("outputCurrency") String outputCurrency) {
        // Call the BitcoinPriceService to fetch historical Bitcoin prices
        return bitcoinPriceService.fetchBitcoinPrices(startDate, endDate, outputCurrency);
    }


}
