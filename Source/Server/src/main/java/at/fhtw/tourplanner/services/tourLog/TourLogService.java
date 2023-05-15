package at.fhtw.tourplanner.services.tourLog;

import at.fhtw.tourplanner.exceptions.NoContentException;
import at.fhtw.tourplanner.exceptions.NotFoundException;
import at.fhtw.tourplanner.models.Tour;
import at.fhtw.tourplanner.models.TourLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TourLogService {
    private final TourLogController tourLogController;
    public TourLogService(TourLogController tourLogController) {
        this.tourLogController = tourLogController;
    }
    @PostMapping("/tours/{tourId}/tourlogs")
    public ResponseEntity<TourLog> createTourLog(@PathVariable("tourId") Integer tourId, @RequestBody TourLog tourLog) {
        try {
            tourLog = tourLogController.createTourLog(tourId, tourLog);
            return new ResponseEntity<>(tourLog, HttpStatus.CREATED);
        }
        catch(NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/tours/{tourId}/tourlogs")
    public ResponseEntity<List<TourLog>> getTourLogs(@PathVariable("tourId") Integer tourId) {
        try {
            List<TourLog> tourLogs = tourLogController.getTourLogs(tourId);
            return new ResponseEntity<>(tourLogs, HttpStatus.OK);
        }
        catch(NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/tourlogs")
    public ResponseEntity<List<TourLog>> getTourLogs() {
        try {
            List<TourLog> tourLogs = tourLogController.getTourLogs();
            return new ResponseEntity<>(tourLogs, HttpStatus.OK);
        }
        catch(NoContentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/tourlogs/{tourLogId}")
    public ResponseEntity<TourLog> getTourLog(@PathVariable("tourLogId") Integer tourLogId) {
        try {
            TourLog tourLog = tourLogController.getTourLog(tourLogId);
            return new ResponseEntity<>(tourLog, HttpStatus.OK);
        }
        catch(NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping("/tourlogs/{tourLogId}")
    public ResponseEntity<TourLog> updateTourLog(@PathVariable("tourLogId") Integer tourLogId, @RequestBody TourLog tourLog) {
        try {
            tourLog = tourLogController.updateTourLog(tourLogId, tourLog);
            return new ResponseEntity<>(tourLog, HttpStatus.OK);
        }
        catch(NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("/tourlogs/{tourLogId}")
    public ResponseEntity<Tour> deleteTourLog(@PathVariable("tourLogId") Integer tourLogId) {
        try {
            tourLogController.deleteTourLog(tourLogId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
