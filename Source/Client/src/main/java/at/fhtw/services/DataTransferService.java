package at.fhtw.services;

import at.fhtw.exceptions.BadRequestException;
import at.fhtw.exceptions.FailedToSendRequestException;
import at.fhtw.exceptions.NoContentException;
import at.fhtw.exceptions.NotFoundException;
import at.fhtw.models.Tour;
import at.fhtw.utils.JsonFileHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class DataTransferService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    public List<Tour> exportTours(String filename) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:10001/export"))
                    .GET()
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if(httpResponse.statusCode() == 204) {
                throw new NoContentException("exportTours - no content");
            }
            if(httpResponse.statusCode() == 400) {
                throw new BadRequestException("exportTours - bad request");
            }
            if(httpResponse.statusCode() == 404) {
                throw new NotFoundException("exportTours - not found");
            }

            if(filename != null) {
                JsonFileHandler.saveJsonStringToFile(filename, httpResponse.body());
            }

            List<Tour> tours = List.of(objectMapper.readValue(httpResponse.body(), Tour[].class));
            return tours;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            // e.printStackTrace();
            throw new FailedToSendRequestException("exportTours - failed to send request");
        }
    }
    public List<Tour> importTours(String filename) {
        try {
            String jsonString = JsonFileHandler.readJsonStringFromFile(filename);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:10001/import"))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                    .setHeader("Content-Type", "application/json")
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if(httpResponse.statusCode() == 204) {
                throw new NoContentException("importTours - no content");
            }
            if(httpResponse.statusCode() == 400) {
                throw new BadRequestException("importTours - bad request");
            }
            if(httpResponse.statusCode() == 404) {
                throw new NotFoundException("importTours - not found");
            }

            List<Tour> importTours = List.of(objectMapper.readValue(httpResponse.body(), Tour[].class));
            return importTours;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            // e.printStackTrace();
            throw new FailedToSendRequestException("importTours - failed to send request");
        }
    }
}
