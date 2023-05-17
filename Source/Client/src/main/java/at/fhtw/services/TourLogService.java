package at.fhtw.services;

import at.fhtw.exceptions.BadRequestException;
import at.fhtw.exceptions.FailedToSendRequestException;
import at.fhtw.exceptions.NoContentException;
import at.fhtw.exceptions.NotFoundException;
import at.fhtw.models.TourLog;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TourLogService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    public TourLog createTourLog(Integer tourId, TourLog tourLog) {
        try {
            String jsonString = objectMapper.writeValueAsString(tourLog);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:10001/tours/" + tourId + "/tourlogs"))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                    .setHeader("Content-Type", "application/json")
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if(httpResponse.statusCode() == 204) {
                throw new NoContentException("createTourLog - no content");
            }
            if(httpResponse.statusCode() == 400) {
                throw new BadRequestException("createTourLog - bad request");
            }
            if(httpResponse.statusCode() == 404) {
                throw new NotFoundException("createTourLog - not found");
            }

            TourLog newTourLog = objectMapper.readValue(httpResponse.body(), TourLog.class);
            return newTourLog;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            // e.printStackTrace();
            throw new FailedToSendRequestException("createTourLog - failed to send request");
        }
    }
    public List<TourLog> getTourLogs(Integer tourId) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:10001/tours/" + tourId + "/tourlogs"))
                    .GET()
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if(httpResponse.statusCode() == 204) {
                throw new NoContentException("getTourLogs - no content");
            }
            if(httpResponse.statusCode() == 400) {
                throw new BadRequestException("getTourLogs - bad request");
            }
            if(httpResponse.statusCode() == 404) {
                throw new NotFoundException("getTourLogs - not found");
            }

            List <TourLog> tourLogs = List.of(objectMapper.readValue(httpResponse.body(), TourLog[].class));
            return tourLogs;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            // e.printStackTrace();
            throw new FailedToSendRequestException("getTourLogs - failed to send request");
        }
    }
    public TourLog getTourLog(Integer tourLogId) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:10001/tourlogs/" + tourLogId))
                    .GET()
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if(httpResponse.statusCode() == 204) {
                throw new NoContentException("getTourLog - no content");
            }
            if(httpResponse.statusCode() == 400) {
                throw new BadRequestException("getTourLog - bad request");
            }
            if(httpResponse.statusCode() == 404) {
                throw new NotFoundException("getTourLog - not found");
            }

            TourLog tourLog = objectMapper.readValue(httpResponse.body(), TourLog.class);
            return tourLog;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            // e.printStackTrace();
            throw new FailedToSendRequestException("getTourLog - failed to send request");
        }
    }
    public TourLog updateTourLog(Integer tourLogId, TourLog tourlog) {
        try {
            String jsonString = objectMapper.writeValueAsString(tourlog);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:10001/tourlogs/" + tourLogId))
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonString))
                    .setHeader("Content-Type", "application/json")
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if(httpResponse.statusCode() == 204) {
                throw new NoContentException("updateTourLog - no content");
            }
            if(httpResponse.statusCode() == 400) {
                throw new BadRequestException("updateTourLog - bad request");
            }
            if(httpResponse.statusCode() == 404) {
                throw new NotFoundException("updateTourLog - not found");
            }

            TourLog newTourLog = objectMapper.readValue(httpResponse.body(), TourLog.class);
            return newTourLog;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            // e.printStackTrace();
            throw new FailedToSendRequestException("updateTourLog - failed to send request");
        }
    }
    public void deleteTourLog(Integer tourLogId) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:10001/tourlogs/" + tourLogId))
                    .DELETE()
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if(httpResponse.statusCode() == 204) {
                throw new NoContentException("deleteTourLog - no content");
            }
            if(httpResponse.statusCode() == 400) {
                throw new BadRequestException("deleteTourLog - bad request");
            }
            if(httpResponse.statusCode() == 404) {
                throw new NotFoundException("deleteTourLog - not found");
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            // e.printStackTrace();
            throw new FailedToSendRequestException("deleteTourLog - failed to send request");
        }
    }
}
