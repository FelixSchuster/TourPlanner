package at.fhtw;

import at.fhtw.businessLogic.BusinessLogic;
import at.fhtw.models.Tour;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TourPlannerClient {
    private static final BusinessLogic businessLogic = new BusinessLogic();
    public static void main(String[] args) {
        businessLogic.createSummarizeReport("C:\\Users\\Felix\\Desktop\\summarizeReport.pdf");
        businessLogic.createTourReport(1, "C:\\Users\\Felix\\Desktop\\tourReport.pdf");

        // Tour createTour = new Tour("Vienna-Bratislava tour", "Vienna-Bratislava (car)", "Vienna", "Bratislava", "car");
        // businessLogic.createTour(createTour);

        // businessLogic.searchTour("aaaaaaaaaa");

        // businessLogic.getTour(1);


        /*
        businessLogic.getTourList();

        businessLogic.getTour(1);

        Tour updateTour = new Tour(); updateTour.setTourDescription("New description"); updateTour.setDestination("Wien Mitte");
        businessLogic.updateTour(1, updateTour);



        businessLogic.deleteTour(1);

        businessLogic.getTour(1);

        businessLogic.createTour(createTour);

        businessLogic.getTourLogs(2);
        businessLogic.getTourLogs(3);

        TourLog createTourLog = new TourLog("My comment", 3, 8500, 2);
        businessLogic.createTourLog(2, createTourLog);
        businessLogic.createTourLog(2, createTourLog);

        businessLogic.getTourLogs(2);

        businessLogic.getTourLog(1);
        businessLogic.getTourLog(2);
        businessLogic.getTourLog(10);

        TourLog updateTourLog = new TourLog(); updateTourLog.setComment("New comment");
        businessLogic.updateTourLog(1, updateTourLog);

        businessLogic.getTourLogs(2);

        businessLogic.deleteTourLog(1);

        businessLogic.getTourLog(1);

        businessLogic.getTourLogs(2);


        businessLogic.exportTours("C:\\Users\\Felix\\Desktop\\export.json");

        businessLogic.importTours("C:\\Users\\Felix\\Desktop\\import.json");
        */
    }
}
