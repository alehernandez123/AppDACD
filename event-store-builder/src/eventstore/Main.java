package eventstore;

public class Main {
    public static void main(String[] args) {
        EventStoreConsumer consumer = new EventStoreConsumer();
        consumer.start();
    }
}