﻿# Web Scraping and Trie-Based Search Application - Spring Boot

This project is a **Spring Boot application** designed to scrape real-time data from specified websites and efficiently store and retrieve keywords using a **Trie data structure**. The application provides fast and relevant search functionality, enabling users to access real-time information and support data-driven decision-making processes.

---

## **Technologies Used**
- **Spring Boot** 3.1.0
- **Java** 17
- **Jsoup** 1.15.3 (for web scraping)
- **Maven** 3.x (for dependency management)
- **Mockito** 5.x (for unit testing)
- **Postman** (for API testing)

---

## **Features**
1. **Web Scraping Module**
    - Scrapes content from user-defined URLs based on specified keywords.
    - Supports scheduled scraping at regular intervals or on-demand.
    - Stores scraped data along with metadata (e.g., source URL, timestamp).

2. **Trie Data Structure**
    - Implements a Trie data structure for efficient keyword indexing.
    - Provides prefix-based search and autocompletion features.

3. **RESTful API**
    - Allows users to initiate scraping, perform searches, and manage scraped data.

---

## **Implementation Details**

The application uses **Jsoup** for web scraping and a **Trie data structure** for efficient keyword storage and retrieval. The Trie enables fast prefix-based searches and autocompletion, enhancing the user experience.

---

## **How to Run the Project**

### **Prerequisites**
- **Java 17** installed
- **Maven** installed

### **Steps to Run**
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Shrungadithyaan/Web-Scraping-and-Trie-Based-Search-Application.git
   cd Web-Scraping-and-Trie-Based-Search-Application
   ```

2. **Build the Project**:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the Application**:
    - Application runs at: `http://localhost:8080`

5. **Run Tests**:
   To execute unit tests, run the following command:
   ```bash
   mvn test
   ```

---

## **API Endpoints**

### **1. Scraping Endpoint**
- **Endpoint**: `POST /api/v1/scrape`
- **Request Body**:
  ```json
  {
    "urls": [
      "https://www.deccanherald.com",
      "https://timesofindia.indiatimes.com"
    ],
    "keywords": ["technology", "cricket"],
    "schedule": "2025-03-09T10:00:00Z"
  }
  ```
- **Response**:
  ```json
  {
    "status": "success",
    "scheduledAt": "2025-03-09T10:00:00Z",
    "jobId": 1001,
    "message": "Scraping initiated successfully."
  }
  ```

### **2. Search Endpoint**
- **Endpoint**: `POST /api/v1/search`
- **Request Body**:
  ```json
  {
    "prefix": "cricket",
    "limit": 2
  }
  ```
- **Response**:
  ```json
  {
    "status": "success",
    "results": [
        {   
            "matchedContent": "karnataka bengaluru sports cricket football tennis f1 racing entertainment movie...",
            "url": "https://www.deccanherald.com",
            "timestamp": "2025-03-13T05:48:28.9816271"
        },
        {
            "matchedContent": "arrest world business tech cricket sports entertainment astro tv education life &.....",
            "url": "https://timesofindia.indiatimes.com",
            "timestamp": "2025-03-13T05:48:29.169376"
        }
    ]
  }
  ```

### **3. Status Check Endpoint**
- **Endpoint**: `GET /api/v1/status/{jobId}`
- **Response**:
  ```json
  {
    "finishedAt": "2025-03-13T05:48:38.217124",
    "keywordsFound": [
        "cricket",
        "technology"
    ],
    "jobId": 1001,
    "dataSize": "38 KB",
    "status": "completed",
    "urlsScraped": [
        "https://www.deccanherald.com",
        "https://timesofindia.indiatimes.com"
    ]
  }
  ```

---

## **File Structure**
```
web-scraping-trie-search/
├── src/
│   ├── main/
│   │   ├── java/com/example/web_scraping/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── service/
│   │   │   ├── trie/
│   │   │   └── WebScrapingApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/java/com/example/web_scraping/
│       ├── controller/
│       ├── service/
│       ├── trie/
│       └── WebScrapingApplicationTests.java
├── pom.xml
└── README.md
```

---

## **Output**

[![Image 1](https://github.com/Shrungadithyaan/Web-Scraping-and-Trie-Based-Search-Application/blob/main/Output%20IMG/1.png?raw=true)](https://github.com/Shrungadithyaan/Web-Scraping-and-Trie-Based-Search-Application/blob/main/Output%20IMG/1.png?raw=true)

[![Image 2](https://github.com/Shrungadithyaan/Web-Scraping-and-Trie-Based-Search-Application/blob/main/Output%20IMG/5.png?raw=true)](https://github.com/Shrungadithyaan/Web-Scraping-and-Trie-Based-Search-Application/blob/main/Output%20IMG/5.png?raw=true)

[![Image 3](https://github.com/Shrungadithyaan/Web-Scraping-and-Trie-Based-Search-Application/blob/main/Output%20IMG/3.png?raw=true)](https://github.com/Shrungadithyaan/Web-Scraping-and-Trie-Based-Search-Application/blob/main/Output%20IMG/3.png?raw=true)

[![Image 4](https://github.com/Shrungadithyaan/Web-Scraping-and-Trie-Based-Search-Application/blob/main/Output%20IMG/4.png?raw=true)](https://github.com/Shrungadithyaan/Web-Scraping-and-Trie-Based-Search-Application/blob/main/Output%20IMG/4.png?raw=true)


---

## **Reference Links**
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/index.html)
- [Java Documentation](https://www.tutorialspoint.com/java/java_documentation.htm)
- [Maven POM Guide](https://maven.apache.org/pom.html)

---

## **Contact**
For any questions or issues, please contact:

- **Name**: Shrungadithya A N
- **Email**: shrungadithyaan@gmail.com
- **GitHub**: [Shrungadithyaan](https://github.com/Shrungadithyaan)
- **Portfolio**: [shrungadithya.netlify.app](https://shrungadithya.netlify.app/)

