package com.example.web_scraping.service;

import com.example.web_scraping.model.ScrapedData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service class for web scraping functionality.
 */
@Service
public class WebScraperService {

    /**
     * Scrapes the given URLs and extracts content while checking for specified keywords.
     * @param urls List of URLs to scrape.
     * @param keywords List of keywords to search for in the scraped content.
     * @return List of ScrapedData objects containing extracted content and matched keywords.
     */
    public List<ScrapedData> scrapeData(List<String> urls, List<String> keywords) {
        List<ScrapedData> results = new ArrayList<>();

        for (String url : urls) {
            try {
                Document doc = Jsoup.connect(url).get(); // Fetch and parse the webpage
                String text = doc.text().toLowerCase(); // Convert to lowercase for case-insensitive search
                Set<String> matchedKeywords = new HashSet<>();

                // Check if any of the given keywords are present in the scraped content
                for (String keyword : keywords) {
                    if (text.contains(keyword.toLowerCase())) {
                        matchedKeywords.add(keyword);
                    }
                }

                // If at least one keyword is found, store the scraped data
                if (!matchedKeywords.isEmpty()) {
                    results.add(new ScrapedData(url, text, LocalDateTime.now(), new ArrayList<>(matchedKeywords)));
                }
            } catch (IOException e) {
                System.err.println("Error scraping URL: " + url + " - " + e.getMessage());
            }
        }
        return results;
    }
}
