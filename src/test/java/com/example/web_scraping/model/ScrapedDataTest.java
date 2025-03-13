package com.example.web_scraping.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the ScrapedData model class.
 */
public class ScrapedDataTest {

    /**
     * Test to verify the correct creation and retrieval of ScrapedData fields.
     */
    @Test
    void testScrapedData() {
        // Create a sample timestamp
        LocalDateTime timestamp = LocalDateTime.now();

        // Create a ScrapedData object
        ScrapedData data = new ScrapedData(
                "https://timesofindia.indiatimes.com/",
                "This is a sample content about technology.",
                timestamp,
                List.of("technology", "innovation")
        );

        // Assertions to verify the ScrapedData object
        assertEquals("https://timesofindia.indiatimes.com/", data.getUrl(), "URL should match");
        assertEquals("This is a sample content about technology.", data.getContent(), "Content should match");
        assertEquals(timestamp, data.getTimestamp(), "Timestamp should match");
        assertTrue(data.getKeywordsFound().contains("technology"), "Keywords should include 'technology'");
        assertTrue(data.getKeywordsFound().contains("innovation"), "Keywords should include 'innovation'");
        assertEquals(2, data.getKeywordsFound().size(), "Keywords list should have 2 items");
    }
}
