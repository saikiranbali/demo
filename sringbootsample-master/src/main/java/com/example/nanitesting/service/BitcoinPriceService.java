package com.example.nanitesting.service;

import com.example.nanitesting.BitcoinPriceApiResponse;
import com.example.nanitesting.BitcoinPriceEntry;
import com.example.nanitesting.BitcoinPriceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BitcoinPriceService {
    @Value("${coindesk.api.url}")
    private String coindeskApiUrl;

    private final WebClient webClient;

    public BitcoinPriceService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(coindeskApiUrl).build();
    }

    public Mono<BitcoinPriceResponse> fetchBitcoinPrices(String startDate, String endDate, String outputCurrency) {
        // Build the Coindesk API URL with the provided parameters
        String apiUrl = buildApiUrl(startDate, endDate, outputCurrency);

        // Make an API request to Coindesk to fetch historical Bitcoin prices
        return webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(BitcoinPriceApiResponse.class)
                .map(response -> {
                    if (response != null && response.getBpi() != null) {
                        return createBitcoinPriceResponse(response.getBpi(), startDate, endDate, outputCurrency);
                    }
                    // Handle error cases here
                    return null;
                });
    }

//    private String buildApiUrl(String startDate, String endDate, String outputCurrency) {
//        // Build the Coindesk API URL with the provided parameters
//        return "?start=" + startDate + "&end=" + endDate + "&currency=" + outputCurrency;
//    }

    private String buildApiUrl(String startDate, String endDate, String outputCurrency) {
        // Build the Coindesk API URL with the provided parameters
        return coindeskApiUrl + "?start=" + startDate + "&end=" + endDate + "&currency=" + outputCurrency;
    }


    private BitcoinPriceResponse createBitcoinPriceResponse(
            Map<String, Double> bpiData, String startDate, String endDate, String outputCurrency) {
        List<BitcoinPriceEntry> priceEntries = new ArrayList<>();
        Double highestPrice = Double.MIN_VALUE;
        Double lowestPrice = Double.MAX_VALUE;

        for (Map.Entry<String, Double> entry : bpiData.entrySet()) {
            String date = entry.getKey();
            Double price = entry.getValue();

            // Check if the price is the highest or lowest
            boolean isHighest = price >= highestPrice;
            boolean isLowest = price <= lowestPrice;

            if (isHighest) {
                highestPrice = price;
            }

            if (isLowest) {
                lowestPrice = price;
            }

            // Create a BitcoinPriceEntry for the date
            BitcoinPriceEntry priceEntry = new BitcoinPriceEntry(date, price, outputCurrency, isHighest, isLowest);
            priceEntries.add(priceEntry);
        }

        // Create a BitcoinPriceResponse with the list of price entries
        return new BitcoinPriceResponse(priceEntries);
    }
}
