package at.fhtw.tourplanner.services.tour;

import at.fhtw.tourplanner.exceptions.NoContentException;
import at.fhtw.tourplanner.exceptions.NotFoundException;
import at.fhtw.tourplanner.models.MapQuestData;
import at.fhtw.tourplanner.models.Tour;
import at.fhtw.tourplanner.models.TourListEntry;
import at.fhtw.tourplanner.models.TourLog;
import at.fhtw.tourplanner.repositories.MapQuestRepository;
import at.fhtw.tourplanner.repositories.TourRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class TourController {
    private final TourRepository tourRepository;
    private final MapQuestRepository mapQuestRepository = new MapQuestRepository();
    public TourController(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }
    public Tour createTour(Tour tour) {
        MapQuestData mapQuestData = mapQuestRepository.findMapQuestData(tour.getStart(), tour.getDestination(), tour.getTransportType());
        String tourInformation = mapQuestRepository.findMapQuestMap(tour.getStart(), tour.getDestination());
        tour.setTourDistance(mapQuestData.getTourDistance());
        tour.setEstimatedTime(mapQuestData.getEstimatedTime());
        tour.setTourInformation(tourInformation);

        tour = tourRepository.save(tour);
        return tour;
    }
    public List<TourListEntry> getTourList() {
        List<Tour> tours = tourRepository.findAll();
        if(tours.isEmpty()) {
            throw new NoContentException("No tours were created yet");
        }
        List<TourListEntry> tourListEntries = new ArrayList<>();
        for(Tour tour : tours) {
            tourListEntries.add(new TourListEntry(tour.getTourId(), tour.getName()));
        }
        return tourListEntries;
    }
    public Tour getTour(Integer tourId) {
        try {
            Tour tour = tourRepository.findById(tourId).get();
            return tour;
        }
        catch(NoSuchElementException e) {
            throw new NotFoundException("Tour with given id not found");
        }
    }
    public Tour updateTour(Integer tourId, Tour tour) {
        try {
            Tour updatedTour = tourRepository.findById(tourId).get();

            if(tour.getName() != null) {
                updatedTour.setName(tour.getName());
            }
            if(tour.getTourDescription() != null) {
                updatedTour.setTourDescription(tour.getTourDescription());
            }
            if(tour.getStart() != null) {
                updatedTour.setStart(tour.getStart());
            }
            if(tour.getDestination() != null) {
                updatedTour.setDestination(tour.getDestination());
            }
            if(tour.getTransportType() != null) {
                updatedTour.setTransportType(tour.getTransportType());
            }

            MapQuestData mapQuestData = mapQuestRepository.findMapQuestData(updatedTour.getStart(), updatedTour.getDestination(), updatedTour.getTransportType());
            String tourInformation = mapQuestRepository.findMapQuestMap(updatedTour.getStart(), updatedTour.getDestination());
            updatedTour.setTourDistance(mapQuestData.getTourDistance());
            updatedTour.setEstimatedTime(mapQuestData.getEstimatedTime());
            updatedTour.setTourInformation(tourInformation);

            updatedTour = tourRepository.save(updatedTour);

            return updatedTour;
        }
        catch(NoSuchElementException e) {
            throw new NotFoundException("Tour with given id not found");
        }
    }
    public void deleteTour(Integer tourId) {
        Integer deletedRows = tourRepository.deleteByTourId(tourId);
        if(deletedRows < 1) {
            throw new NotFoundException("deleteTour - not found");
        }
    }
    public List<TourListEntry> searchTour(String keyword) {
        keyword = keyword.toLowerCase();
        List<Tour> tours = tourRepository.findAll();
        if(tours.isEmpty()) {
            throw new NoContentException("searchTour - no content");
        }
        List<TourListEntry> result = new ArrayList<>();
        for(Tour tour : tours) {
            if (tour.getName().toLowerCase().contains(keyword)
                    || tour.getTourDescription().toLowerCase().contains(keyword)
                    || tour.getStart().toLowerCase().contains(keyword)
                    || tour.getDestination().toLowerCase().contains(keyword)
                    || tour.getTransportType().toLowerCase().contains(keyword)) {
                result.add(new TourListEntry(tour.getTourId(), tour.getName()));
            }
            else {
                for (TourLog tourLog : tour.getTourLogs()) {
                    if(tourLog.getDate().toLowerCase().contains(keyword)
                            || tourLog.getComment().toLowerCase().contains(keyword)) {
                        result.add(new TourListEntry(tour.getTourId(), tour.getName()));
                    }
                }
            }
        }
        return result;
    }
}
