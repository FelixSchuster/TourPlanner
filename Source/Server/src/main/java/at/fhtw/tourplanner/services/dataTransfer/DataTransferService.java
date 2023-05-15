package at.fhtw.tourplanner.services.dataTransfer;

import at.fhtw.tourplanner.exceptions.NoContentException;
import at.fhtw.tourplanner.models.Tour;
import at.fhtw.tourplanner.services.tour.TourController;
import at.fhtw.tourplanner.services.tourLog.TourLogController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataTransferService {

    private final DataTransferController dataTransferController;
    public DataTransferService(DataTransferController dataTransferController) {
        this.dataTransferController = dataTransferController;
    }
    @RequestMapping("/export")
    @GetMapping
    public ResponseEntity<List<Tour>> exportTours() {
        try {
            List<Tour> tours = dataTransferController.exportTours();
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
    @RequestMapping("/import")
    @PostMapping
    public ResponseEntity<List<Tour>> importTours(@RequestBody List<Tour> tours) {
        try {
            List<Tour> importedTours = dataTransferController.importTours(tours);
            return new ResponseEntity<>(importedTours, HttpStatus.CREATED);
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
