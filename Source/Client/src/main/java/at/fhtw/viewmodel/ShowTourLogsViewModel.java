package at.fhtw.viewmodel;

import at.fhtw.exceptions.FailedToSendRequestException;
import at.fhtw.exceptions.InternalServerErrorException;
import at.fhtw.exceptions.NoContentException;
import at.fhtw.exceptions.NotFoundException;
import at.fhtw.models.Tour;
import at.fhtw.models.TourListEntry;
import at.fhtw.models.TourLog;
import at.fhtw.services.TourLogService;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShowTourLogsViewModel {
    private static final Logger logger = LogManager.getLogger(ShowTourLogsViewModel.class);
    TourLogService tourLogService;
    BooleanProperty hideTourInformation = new SimpleBooleanProperty();
    BooleanProperty showTourInformation  = new SimpleBooleanProperty();
    BooleanProperty isNoTourLogContent = new SimpleBooleanProperty();
    TourListEntry tourListEntry;
    List<TourLog> tourLogs;

    public ShowTourLogsViewModel()
    {
        hideTourInformation.setValue(false);
        showTourInformation.setValue(false);
        tourLogService = new TourLogService();
        tourLogs = new ArrayList<>();
    }

    public void hideTourLogs()
    {
        hideTourInformation.setValue(true);
    }
    public void clearItems(){ tourLogs.clear(); }

    public void showTourLogs(Integer tourId)
    {
        try {
            hideTourInformation.setValue(true);
            tourLogs = tourLogService.getTourLogs(tourId);

            showTourInformation.setValue(true);
            logger.info("ShowTourLogsViewModel.getTourLogs() - tourLogs retrieved successfully: " + tourLogs);
        } catch (NoContentException e) {
            isNoTourLogContent.setValue(true);
            throw new NoContentException(e);
        } catch (NotFoundException e) {
            throw new NotFoundException(e);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(e);
        } catch (FailedToSendRequestException e) {
            throw new FailedToSendRequestException(e);
        }
    }

    public void createTourLog(Integer tourId, TourLog createdTourLog) {
        try {
            TourLog tourLog = tourLogService.createTourLog(tourId, createdTourLog);
            logger.info("ShowTourLogsViewModel.createTourLog() - tourLog created successfully: " + tourLog);
            showTourLogs(tourId);

        } catch (NotFoundException e) {
            throw new NotFoundException(e);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(e);
        } catch (FailedToSendRequestException e) {
            throw new FailedToSendRequestException(e);
        }
    }

    public void getTourLog(Integer tourLogId)
    {
        try {
            TourLog tourLog= tourLogService.getTourLog(tourLogId);
            logger.info("ShowTourLogsViewModel.getTourLog() - tourLog retrieved successfully: " + tourLog);
        } catch (NotFoundException e) {
            throw new NotFoundException(e);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(e);
        } catch (FailedToSendRequestException e) {
            throw new FailedToSendRequestException(e);
        }
    }

    public void updateTourLogs(TourLog updatedTourLog, Integer tourLogId)
    {
        try {
            TourLog tourLog = tourLogService.updateTourLog(tourLogId, updatedTourLog);
            logger.info("ShowTourLogsViewModel.updateTourLog() - tourLog updated successfully: " + tourLog);
        } catch (NotFoundException e) {
            throw new NotFoundException(e);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(e);
        } catch (FailedToSendRequestException e) {
            throw new FailedToSendRequestException(e);
        }
    }

    public void deleteTourLogs(Integer tourLogId)
    {
        try {
            tourLogService.deleteTourLog(tourLogId);
            logger.info("ShowTourLogsViewModel.deleteTourLog() - tourLog deleted successfully: " + tourLogId);
        } catch (NotFoundException e) {
            throw new NotFoundException(e);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(e);
        } catch (FailedToSendRequestException e) {
            throw new FailedToSendRequestException(e);
        }
    }

    public boolean isHideTourInformation() {
        return hideTourInformation.get();
    }

    public BooleanProperty hideTourInformationProperty() {
        return hideTourInformation;
    }

    public void setHideTourInformation(boolean hideTourInformation) {
        this.hideTourInformation.set(hideTourInformation);
    }

    public boolean isShowTourInformation() {
        return showTourInformation.get();
    }

    public BooleanProperty showTourInformationProperty() {
        return showTourInformation;
    }

    public void setShowTourInformation(boolean showTourInformation) {
        this.showTourInformation.set(showTourInformation);
    }

    public boolean getIsNoTourLogContent() {
        return isNoTourLogContent.get();
    }

    public BooleanProperty isNoTourLogContentProperty() {
        return isNoTourLogContent;
    }

    public void setIsNoTourLogContent(boolean isNoTourLogContent) {
        this.isNoTourLogContent.set(isNoTourLogContent);
    }

    public TourListEntry getTourListEntry() {
        return tourListEntry;
    }

    public List<TourLog> getTourLogs() {
        return tourLogs;
    }

    public void setTourListEntry(TourListEntry tourListEntry) {
        this.tourListEntry = tourListEntry;
    }
}
