package at.fhtw.tourplanner.services.tourLog;

import at.fhtw.tourplanner.exceptions.NoContentException;
import at.fhtw.tourplanner.exceptions.NotFoundException;
import at.fhtw.tourplanner.models.Tour;
import at.fhtw.tourplanner.models.TourLog;
import at.fhtw.tourplanner.repositories.TourLogRepository;
import at.fhtw.tourplanner.repositories.TourRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class TourLogController {
    private final TourLogRepository tourLogRepository;
    private final TourRepository tourRepository;
    private void updateTourStats(Integer tourId) {
        try {
            Tour tour = tourRepository.findById(tourId).get();
            if (tour.getTourLogs().size() > 2) {
                tour.setPopularity(2);
            }
            if (tour.getTourLogs().size() > 5) {
                tour.setPopularity(3);
            }
            if (tour.getTourLogs().size() > 10) {
                tour.setPopularity(4);
            }
            if (tour.getTourLogs().size() > 15) {
                tour.setPopularity(5);
            }
            Integer averageDifficulty = 0;
            for(TourLog tourLog : tour.getTourLogs()) {
                averageDifficulty = averageDifficulty + tourLog.getDifficulty();
            }
            averageDifficulty = averageDifficulty / tour.getTourLogs().size();
            if(averageDifficulty == 1) {
                tour.setChildFriendliness(5);
            }
            if(averageDifficulty == 2) {
                tour.setChildFriendliness(4);
            }
            if(averageDifficulty == 4) {
                tour.setChildFriendliness(2);
            }
            if(averageDifficulty == 5) {
                tour.setChildFriendliness(1);
            }
            tourRepository.save(tour);
        }
        catch(NoSuchElementException e) {
            throw new NotFoundException("Tour with given id not found");
        }
    }
    public TourLogController(TourLogRepository tourLogRepository, TourRepository tourRepository) {
        this.tourLogRepository = tourLogRepository;
        this.tourRepository = tourRepository;
    }
    public TourLog createTourLog(Integer tourId, TourLog tourLog) {
        try {
            Tour tour = tourRepository.findById(tourId).get();
            tourLog.setTour(tour);
            tourLog = tourLogRepository.save(tourLog);
            updateTourStats(tourId);
            return tourLog;
        }
        catch(NoSuchElementException e) {
            throw new NotFoundException("Tour with given id not found");
        }
    }
    public List<TourLog> getTourLogs(Integer tourId) {
        try {
            Tour tour = tourRepository.findById(tourId).get();
            List<TourLog> tourLogs = tour.getTourLogs();
            return tourLogs;
        }
        catch(NoSuchElementException e) {
            throw new NotFoundException("Tour with given id not found");
        }
    }
    public List<TourLog> getTourLogs() {
        List<TourLog> tourLogs = tourLogRepository.findAll();
        if(tourLogs.isEmpty()) {
            throw new NoContentException("No tourLogs were created yet");
        }
        return tourLogs;
    }
    public TourLog getTourLog(Integer tourLogId) {
        try {
            TourLog tourLog = tourLogRepository.findById(tourLogId).get();
            return tourLog;
        }
        catch(NoSuchElementException e) {
            throw new NotFoundException("TourLog with given id not found");
        }
    }
    public TourLog updateTourLog(Integer tourLogId, TourLog tourLog) {
        try {
            TourLog updatedTourLog = tourLogRepository.findById(tourLogId).get();

            if(tourLog.getDate() != null) {
                updatedTourLog.setDate(tourLog.getDate());
            }
            if(tourLog.getComment() != null) {
                updatedTourLog.setComment(tourLog.getComment());
            }
            if(tourLog.getDifficulty() != null) {
                updatedTourLog.setDifficulty(tourLog.getDifficulty());
            }
            if(tourLog.getTotalTime() != null) {
                updatedTourLog.setTotalTime(tourLog.getTotalTime());
            }
            if(tourLog.getRating() != null) {
                updatedTourLog.setRating(tourLog.getRating());
            }
            updateTourStats(tourLogRepository.findById(tourLogId).get().getTour().getTourId());
            updatedTourLog = tourLogRepository.save(updatedTourLog);

            return updatedTourLog;
        }
        catch(NoSuchElementException e) {
            throw new NotFoundException("TourLog with given id not found");
        }
    }
    public void deleteTourLog(Integer tourLogId) {
        Integer deletedRows = tourLogRepository.deleteByTourLogId(tourLogId);
        if(deletedRows < 1) {
            throw new NotFoundException("deleteTourLog - not found");
        }
        updateTourStats(tourLogRepository.findById(tourLogId).get().getTour().getTourId());
    }
}
