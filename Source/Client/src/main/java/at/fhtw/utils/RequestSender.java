package at.fhtw.utils;

import at.fhtw.exceptions.*;
import at.fhtw.models.Tour;
import at.fhtw.models.TourListEntry;
import at.fhtw.models.TourLog;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class RequestSender {
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

            JsonFileHandler.saveJsonStringToFile(filename, httpResponse.body());

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
