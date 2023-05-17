package at.fhtw.utils;

import at.fhtw.models.Tour;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PdfFileHandler {
    private static Font fontHeadline = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
    private static Font fontSubHeadline = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static Font textFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
    public void createSummarizeReport(String filename, java.util.List<Tour> tours) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();

            document.addTitle("Summarize-Report");
            document.addSubject("<TourName>");
            document.addCreator("TourPlanner-Client");

            addTitlePage(document);

            addContent(document, tours);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void addTitlePage(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();

        addEmptyLine(preface, 1);

        preface.add(new Paragraph("Summarize-Report", fontHeadline));

        addEmptyLine(preface, 1);

        preface.add(new Paragraph("Date of report: " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()), textFont));

        document.add(preface);

        // document.newPage();
    }
    private static void addContent(Document document, java.util.List<Tour> tours) throws DocumentException {
        int i = 1;
        for(Tour tour : tours) {
            Anchor anchor = new Anchor(tour.getName(), fontHeadline);
            anchor.setName(tour.getName());

            Chapter catPart = new Chapter(new Paragraph(anchor), i);

            Paragraph subPara = new Paragraph("Tour Information", fontSubHeadline);
            Section subCatPart = catPart.addSection(subPara);

            addEmptyLine(subPara, 1);
            Paragraph mapParagraph = new Paragraph();

            mapParagraph.add("MAP GOES HERE"); // tourInformation

            subCatPart.add(mapParagraph);

            List list = new List(false, false, 10);
            list.add(new ListItem("Tour Name: " + tour.getName()));
            list.add(new ListItem("Start: " + tour.getStart()));
            list.add(new ListItem("Destination: " + tour.getDestination()));
            list.add(new ListItem("Transport Type: " + tour.getTransportType()));
            list.add(new ListItem("Tour Distance: " + tour.getTourDistance() + "km"));
            list.add(new ListItem("Estimated Time: " + LocalTime.ofSecondOfDay(tour.getEstimatedTime()).toString()));
            list.add(new ListItem("Popularity: " + tour.getPopularity()));
            list.add(new ListItem("Child Friendliness: " + tour.getChildFriendliness()));

            subCatPart.add(list);

            if(!tour.getTourLogs().isEmpty()) {
                subPara = new Paragraph("Tour Logs", fontSubHeadline);
                subCatPart = catPart.addSection(subPara);

                addEmptyLine(subPara, 1);
                Paragraph tourlogParagraph = new Paragraph();

            }

            subCatPart.add(new Paragraph("Paragraph 1"));
            subCatPart.add(new Paragraph("Paragraph 2"));
            subCatPart.add(new Paragraph("Paragraph 3"));

            // add a list
            createList(subCatPart);
            Paragraph paragraph = new Paragraph();
            addEmptyLine(paragraph, 5);
            subCatPart.add(paragraph);

            // add a table
            createTable(subCatPart);

            // now add all this to the document
            document.add(catPart);

            // Next section
            anchor = new Anchor("Second Chapter", fontHeadline);
            anchor.setName("Second Chapter");

            // Second parameter is the number of the chapter
            catPart = new Chapter(new Paragraph(anchor), 1);

            subPara = new Paragraph("Subcategory", fontSubHeadline);
            subCatPart = catPart.addSection(subPara);
            subCatPart.add(new Paragraph("This is a very important message"));

            // now add all this to the document
            document.add(catPart);

            i = i + 1;
        }


    }
    private static void createTable(Section subCatPart) throws BadElementException {
        PdfPTable table = new PdfPTable(3);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 2"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 3"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell("1.0");
        table.addCell("1.1");
        table.addCell("1.2");
        table.addCell("2.1");
        table.addCell("2.2");
        table.addCell("2.3");

        subCatPart.add(table);

    }

    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
