package com.example.web_scraping.controller;

import com.example.web_scraping.service.WebScraperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ScraperController.
 */
@SpringBootTest
public class ScraperControllerTest {

    @Autowired
    private ScraperController scraperController; // Injecting ScraperController for testing

    /**
     * Test for the scrape endpoint to verify if the scraping process is initiated successfully.
     */
    @Test
    void testScrape() {
        Map<String, Object> response = scraperController.scrape(Map.of(
                "urls", List.of("https://timesofindia.indiatimes.com/"),
                "keywords", List.of("technology")
        ));
        assertEquals("success", response.get("status")); // Assert that scraping is successful
    }

    /**
     * Test for the search endpoint to verify if keyword search returns results.
     */
    @Test
    void testSearch() {
        // Perform initial scraping to populate data
        scraperController.scrape(Map.of(
                "urls", List.of("https://timesofindia.indiatimes.com/"),
                "keywords", List.of("technology")
        ));

        // Perform search for a prefix
        Map<String, Object> response = scraperController.search(Map.of(
                "prefix", "tech",
                "limit", 5
        ));

        assertEquals("success", response.get("status")); // Assert that search is successful
        assertFalse(((List<?>) response.get("results")).isEmpty()); // Assert that results are not empty
    }
}
