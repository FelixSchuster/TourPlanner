package at.fhtw.utils;

import at.fhtw.exceptions.FailedToParseImageFileException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageHandler {
    public static void saveBase64EncodedImageToFile(String encodedImage, String filename) {
        if(!filename.endsWith(".jpg")) {
            filename += ".jpg";
        }
        byte[] decodedBytes = Base64.getDecoder().decode(encodedImage);
        Path path = Paths.get(filename);
        try (FileOutputStream fileOutputStream = new FileOutputStream(path.toFile())) {
            fileOutputStream.write(decodedBytes);
            // System.out.println("Image saved successfully to: " + filename);
        } catch (IOException e) {
            // e.printStackTrace();
            throw new FailedToParseImageFileException("saveBase64EncodedImageToFile - failed to save to file: " + filename);
        }
    }
}
