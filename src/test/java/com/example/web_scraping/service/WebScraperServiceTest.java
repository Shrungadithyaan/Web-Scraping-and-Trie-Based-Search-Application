package com.example.web_scraping.service;

import com.example.web_scraping.model.ScrapedData;
import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Unit tests for WebScraperService.
 */
@ExtendWith(MockitoExtension.class)
public class WebScraperServiceTest {

    @InjectMocks
    private WebScraperService webScraperService;

    /**
     * Tests the scrapeData method to ensure correct data retrieval and keyword detection.
     */
    @Test
    void testScrapeData() throws IOException {
        // Create a mock Document
        Document mockDocument = new Document("https://timesofindia.indiatimes.com/");
        mockDocument.text("This is a sample content about technology.");

        // Mock the Connection object
        Connection mockConnection = mock(Connection.class);
        when(mockConnection.get()).thenReturn(mockDocument);

        // Mock the static Jsoup.connect method
        try (MockedStatic<Jsoup> mockedJsoup = mockStatic(Jsoup.class)) {
            mockedJsoup.when(() -> Jsoup.connect(anyString())).thenReturn(mockConnection);

            // Test the scrapeData method
            List<String> urls = List.of("https://timesofindia.indiatimes.com/");
            List<String> keywords = List.of("technology");

            List<ScrapedData> results = webScraperService.scrapeData(urls, keywords);

            // Assertions
            assertNotNull(results, "Scraped results should not be null");
            assertFalse(results.isEmpty(), "Scraped results should not be empty");
            assertEquals("https://timesofindia.indiatimes.com/", results.get(0).getUrl(), "URL should match");
            assertTrue(results.get(0).getKeywordsFound().contains("technology"), "Keywords should include 'technology'");
        }
    }

    /**
     * Tests the scrapeData method when an invalid URL is provided, ensuring proper handling of exceptions.
     */
    @Test
    void testScrapeDataWithInvalidUrl() throws IOException {
        // Mock the Connection object to throw an IOException
        Connection mockConnection = mock(Connection.class);
        when(mockConnection.get()).thenThrow(new IOException("Failed to connect"));

        // Mock the static Jsoup.connect method
        try (MockedStatic<Jsoup> mockedJsoup = mockStatic(Jsoup.class)) {
            mockedJsoup.when(() -> Jsoup.connect(anyString())).thenReturn(mockConnection);

            // Test the scrapeData method with an invalid URL
            List<String> urls = List.of("https://invalid-url.com");
            List<String> keywords = List.of("technology");

            List<ScrapedData> results = webScraperService.scrapeData(urls, keywords);

            // Assertions
            assertNotNull(results, "Scraped results should not be null");
            assertTrue(results.isEmpty(), "Scraped results should be empty for invalid URLs");
        }
    }
}
