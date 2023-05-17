package at.fhtw.services;

import at.fhtw.exceptions.BadRequestException;
import at.fhtw.exceptions.FailedToSendRequestException;
import at.fhtw.exceptions.NoContentException;
import at.fhtw.exceptions.NotFoundException;
import at.fhtw.models.Tour;
import at.fhtw.models.TourListEntry;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TourService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    public Tour createTour(Tour tour) {
        try {
            String jsonString = objectMapper.writeValueAsString(tour);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:10001/tours"))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                    .setHeader("Content-Type", "application/json")
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if(httpResponse.statusCode() == 204) {
                throw new NoContentException("createTour - no content");
            }
            if(httpResponse.statusCode() == 400) {
                throw new BadRequestException("createTour - bad request");
            }
            if(httpResponse.statusCode() == 404) {
                throw new NotFoundException("createTour - not found");
            }

            Tour newtour = objectMapper.readValue(httpResponse.body(), Tour.class);
            return newtour;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            // e.printStackTrace();
            throw new FailedToSendRequestException("createTour - failed to send request");
        }
    }
    public Tour getTour(Integer tourId) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:10001/tours/" + tourId))
                    .GET()
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if(httpResponse.statusCode() == 204) {
                throw new NoContentException("getTour - no content");
            }
            if(httpResponse.statusCode() == 400) {
                throw new BadRequestException("getTour - bad request");
            }
            if(httpResponse.statusCode() == 404) {
                throw new NotFoundException("getTour - not found");
            }

            Tour newtour = objectMapper.readValue(httpResponse.body(), Tour.class);
            return newtour;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            // e.printStackTrace();
            throw new FailedToSendRequestException("getTour - failed to send request");
        }
    }
    public List<TourListEntry> getTourList() {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:10001/tours"))
                    .GET()
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if(httpResponse.statusCode() == 204) {
                throw new NoContentException("getTourList - no content");
            }
            if(httpResponse.statusCode() == 400) {
                throw new BadRequestException("getTourList - bad request");
            }
            if(httpResponse.statusCode() == 404) {
                throw new NotFoundException("getTourList - not found");
            }

            List<TourListEntry> tourList = List.of(objectMapper.readValue(httpResponse.body(), TourListEntry[].class));
            return tourList;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            // e.printStackTrace();
            throw new FailedToSendRequestException("getTourList - failed to send request");
        }
    }
    public List<TourListEntry> searchTour(String keyword) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:10001/tours/search/" + keyword))
                    .GET()
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if(httpResponse.statusCode() == 204) {
                throw new NoContentException("searchTour - no content");
            }
            if(httpResponse.statusCode() == 400) {
                throw new BadRequestException("searchTour - bad request");
            }
            if(httpResponse.statusCode() == 404) {
                throw new NotFoundException("searchTour - not found");
            }

            List<TourListEntry> tourList = List.of(objectMapper.readValue(httpResponse.body(), TourListEntry[].class));
            return tourList;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            // e.printStackTrace();
            throw new FailedToSendRequestException("searchTour - failed to send request");
        }
    }
    public Tour updateTour(Integer tourId, Tour tour) {
        try {
            String jsonString = objectMapper.writeValueAsString(tour);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:10001/tours/" + tourId))
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonString))
                    .setHeader("Content-Type", "application/json")
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if(httpResponse.statusCode() == 204) {
                throw new NoContentException("updateTour - no content");
            }
            if(httpResponse.statusCode() == 400) {
                throw new BadRequestException("updateTour - bad request");
            }
            if(httpResponse.statusCode() == 404) {
                throw new NotFoundException("updateTour - not found");
            }

            Tour newtour = objectMapper.readValue(httpResponse.body(), Tour.class);
            return newtour;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            // e.printStackTrace();
            throw new FailedToSendRequestException("updateTour - failed to send request");
        }
    }
    public void deleteTour(Integer tourId) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:10001/tours/" + tourId))
                    .DELETE()
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if(httpResponse.statusCode() == 204) {
                throw new NoContentException("deleteTour - no content");
            }
            if(httpResponse.statusCode() == 400) {
                throw new BadRequestException("deleteTour - bad request");
            }
            if(httpResponse.statusCode() == 404) {
                throw new NotFoundException("deleteTour - not found");
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            // e.printStackTrace();
            throw new FailedToSendRequestException("deleteTour - failed to send request");
        }
    }
}
