package com.example.web_scraping.controller;

import com.example.web_scraping.model.ScrapedData;
import com.example.web_scraping.service.WebScraperService;
import com.example.web_scraping.trie.Trie;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/v1")
public class ScraperController {

    private final WebScraperService webScraperService;
    private final Trie trie = new Trie(); // Trie data structure for keyword search
    private final List<ScrapedData> scrapedDataList = new ArrayList<>(); // Stores scraped data
    private static final AtomicInteger jobCounter = new AtomicInteger(1000); // Job ID counter, starting from 1000

    public ScraperController(WebScraperService webScraperService) {
        this.webScraperService = webScraperService;
    }

    /**
     * Endpoint to initiate web scraping.
     * @param request A JSON request containing URLs, keywords, and optional schedule time.
     * @return A response map with job details.
     */
    @PostMapping("/scrape")
    public Map<String, Object> scrape(@RequestBody Map<String, Object> request) {
        List<String> urls = (List<String>) request.getOrDefault("urls", new ArrayList<>());
        List<String> keywords = (List<String>) request.getOrDefault("keywords", new ArrayList<>());
        String scheduledAt = (String) request.getOrDefault("schedule", "Not Scheduled");

        int jobId = jobCounter.incrementAndGet(); // Generate a new job ID
        List<ScrapedData> scrapedData = webScraperService.scrapeData(urls, keywords);
        scrapedDataList.addAll(scrapedData);

        // Insert found keywords into the Trie for efficient search
        for (ScrapedData data : scrapedData) {
            for (String keyword : data.getKeywordsFound()) {
                trie.insert(keyword);
            }
        }

        return Map.of(
                "status", "success",
                "message", "Scraping initiated successfully.",
                "jobId", jobId,
                "scheduledAt", scheduledAt
        );
    }

    /**
     * Endpoint to search for scraped content based on a keyword prefix.
     * @param request A JSON request containing the prefix and optional limit.
     * @return A response map with matched content and URLs.
     */
    @PostMapping("/search")
    public Map<String, Object> search(@RequestBody Map<String, Object> request) {
        String prefix = ((String) request.get("prefix")).toLowerCase();
        int limit = (int) request.getOrDefault("limit", 5);

        List<Map<String, Object>> results = new ArrayList<>();

        // If no keyword starts with the given prefix, return failure
        if (!trie.startsWith(prefix)) {
            return Map.of("status", "failure", "results", results);
        }

        // Search through scraped data for matching content
        for (ScrapedData data : scrapedDataList) {
            String content = data.getContent().toLowerCase();
            if (content.contains(prefix)) {
                Map<String, Object> result = new HashMap<>();
                result.put("url", data.getUrl());
                result.put("matchedContent", content.substring(0, Math.min(100, content.length())) + "...");
                result.put("timestamp", data.getTimestamp());
                results.add(result);

                if (results.size() >= limit) {
                    break; // Stop searching if limit is reached
                }
            }
        }

        return Map.of(
                "status", results.isEmpty() ? "failure" : "success",
                "results", results
        );
    }

    /**
     * Endpoint to retrieve the status of a scraping job.
     * @param jobId The job ID to check.
     * @return A response map with job details and scraped information.
     */
    @GetMapping("/status/{jobId}")
    public Map<String, Object> getStatus(@PathVariable int jobId) {
        return Map.of(
                "status", "completed",
                "jobId", jobId,
                "urlsScraped", scrapedDataList.stream().map(ScrapedData::getUrl).toList(),
                "keywordsFound", scrapedDataList.stream().flatMap(data -> data.getKeywordsFound().stream()).distinct().toList(),
                "dataSize", (scrapedDataList.stream().mapToInt(data -> data.getContent().length()).sum() / 1024) + " KB",
                "finishedAt", LocalDateTime.now().toString()
        );
    }
}
