package eventstore;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventStoreWriter {

    public void write(String topic, String jsonEvent) {
        try {
            JsonObject event = JsonParser.parseString(jsonEvent).getAsJsonObject();

            String ss = event.get("ss").getAsString();
            String ts = event.get("ts").getAsString();

            String date = extractDate(ts);

            Path directory = Path.of("eventstore", topic, ss);
            Files.createDirectories(directory);

            Path file = directory.resolve(date + ".events");

            try (FileWriter writer = new FileWriter(file.toFile(), true)) {
                writer.write(jsonEvent);
                writer.write(System.lineSeparator());
            }

            System.out.println("Evento guardado en: " + file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String extractDate(String ts) {
        LocalDateTime dateTime = LocalDateTime.parse(ts);
        return dateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}