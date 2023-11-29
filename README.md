# URL Shortener Service

## Overview

The URL Shortener Service is a Java Spring Boot application that provides APIs for shortening URLs and expanding shortened URLs back to their original form. This service is designed to be simple and efficient, keeping the shortened URLs in memory.

## How to Run

To run this application:

1. Ensure you have Java JDK 11 and Maven installed.
2. Navigate to the root directory of the project.
3. Run the application using Maven:

## Endpoints

The service provides two main endpoints:

- `POST /api/url/encode`: Accepts a URL in the request body and returns a shortened URL.
- `GET /api/url/decode/{shortUrlKey}`: Accepts a short URL key as a path variable and returns the original URL.

Both endpoints communicate via JSON format.

## Usage

### Encoding a URL

Send a POST request to `/api/url/encode` with the original URL in the request body:

###json
{
"longUrl": "https://www.yahoo.com"
}
The response will be a JSON object containing the shortened URL key:
{
  "shortUrlKey": "abc123"
}

### Decoding a URL
Send a GET request to /api/url/decode/{shortUrlKey} replacing {shortUrlKey} with the key you received from the encode endpoint.

The response will be a JSON object containing the original long URL:
{
  "longUrl": "https://www.example.com"
}
