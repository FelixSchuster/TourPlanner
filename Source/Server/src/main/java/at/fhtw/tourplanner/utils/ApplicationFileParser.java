package at.fhtw.tourplanner.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;

public class ApplicationFileParser {
    public static String parseApiKey() {
        try {
            YAMLFactory yamlFactory = new YAMLFactory();
            ObjectMapper objectMapper = new ObjectMapper(yamlFactory);
            File yamlFile = new File("src/main/resources/application.yml");
            JsonNode rootNode = objectMapper.readTree(yamlFile);
            return rootNode.path("api-key").asText();
        } catch (Exception e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println("Failed to read API-Key");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
        }
        return null;
    }
}
