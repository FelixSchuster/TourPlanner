package at.fhtw.tourplanner.repositories;

import at.fhtw.tourplanner.models.TourLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourLogRepository extends JpaRepository<TourLog, Integer> { }
