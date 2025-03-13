package com.example.web_scraping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Model class representing scraped data from a web page.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScrapedData {
    private String url; // URL of the scraped webpage
    private String content; // Extracted content from the webpage
    private LocalDateTime timestamp; // Timestamp of when the data was scraped
    private List<String> keywordsFound; // List of keywords found in the content
}
