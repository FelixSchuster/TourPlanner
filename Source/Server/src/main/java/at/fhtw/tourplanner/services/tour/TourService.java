package at.fhtw.tourplanner.services.tour;

import at.fhtw.tourplanner.exceptions.BadRequestException;
import at.fhtw.tourplanner.exceptions.NoContentException;
import at.fhtw.tourplanner.exceptions.NotFoundException;
import at.fhtw.tourplanner.models.Tour;
import at.fhtw.tourplanner.models.TourListEntry;
import at.fhtw.tourplanner.services.tourLog.TourLogController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tours")
public class TourService {
    private final TourController tourController;
    private final TourLogController tourLogController;
    public TourService(TourController tourController, TourLogController tourLogController) {
        this.tourController = tourController;
        this.tourLogController = tourLogController;
    }
    @PostMapping
    public ResponseEntity<Tour> createTour(@RequestBody Tour tour) {
        try {
            tour = tourController.createTour(tour);
            return new ResponseEntity<>(tour, HttpStatus.CREATED);
        }
        catch(BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping
    public ResponseEntity<List<TourListEntry>> getTourList() {
        try {
            List<TourListEntry> tourListEntries = tourController.getTourList();
            return new ResponseEntity<>(tourListEntries, HttpStatus.OK);
        }
        catch(NoContentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/{tourId}")
    public ResponseEntity<Tour> getTour(@PathVariable("tourId") Integer tourId) {
        try {
            Tour tour = tourController.getTour(tourId);
            return new ResponseEntity<>(tour, HttpStatus.OK);
        }
        catch(NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping("/{tourId}")
    public ResponseEntity<Tour> updateTour(@PathVariable("tourId") Integer tourId, @RequestBody Tour tour) {
        try {
            tour = tourController.updateTour(tourId, tour);
            return new ResponseEntity<>(tour, HttpStatus.OK);
        }
        catch(NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("/{tourId}")
    public ResponseEntity<Tour> deleteTour(@PathVariable("tourId") Integer tourId) {
        try {
            tourController.deleteTour(tourId);
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
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<TourListEntry>> searchTour(@PathVariable("keyword") String keyword) {
        try {
            List<TourListEntry> tours = tourController.searchTour(keyword);
            return new ResponseEntity<>(tours, HttpStatus.OK);
        }
        catch(NoContentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
