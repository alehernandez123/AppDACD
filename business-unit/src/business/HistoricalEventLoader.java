package business;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class HistoricalEventLoader {

    private final BusinessEventConsumer eventProcessor;

    public HistoricalEventLoader(BusinessDatamart datamart) {
        this.eventProcessor = new BusinessEventConsumer(datamart);
    }

    public void load() {
        Path eventStorePath = Path.of("eventstore");

        if (!Files.exists(eventStorePath)) {
            System.out.println("No existe eventstore todavía.");
            return;
        }

        try (Stream<Path> paths = Files.walk(eventStorePath)) {
            paths
                    .filter(path -> path.toString().endsWith(".events"))
                    .forEach(this::loadFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFile(Path file) {
        String topic = file.getParent().getParent().getFileName().toString();

        try {
            Files.lines(file).forEach(line -> {
                eventProcessor.processHistoricalEvent(topic, line);
            });

            System.out.println("Histórico cargado: " + file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}