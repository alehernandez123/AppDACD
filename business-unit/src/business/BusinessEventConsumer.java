package business;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class BusinessEventConsumer {

    private static final String BROKER_URL = "tcp://localhost:61616";

    private final BusinessDatamart datamart;

    public BusinessEventConsumer(BusinessDatamart datamart) {
        this.datamart = datamart;
    }

    public void start() {
        consumeTopic("News");
        consumeTopic("Stocks");
    }

    private void consumeTopic(String topicName) {

        new Thread(() -> {

            try {

                ActiveMQConnectionFactory factory =
                        new ActiveMQConnectionFactory(BROKER_URL);

                Connection connection = factory.createConnection();

                connection.setClientID("business-unit-" + topicName);

                connection.start();

                Session session =
                        connection.createSession(false,
                                Session.AUTO_ACKNOWLEDGE);

                Topic topic = session.createTopic(topicName);

                MessageConsumer consumer =
                        session.createDurableSubscriber(
                                topic,
                                "business-sub-" + topicName
                        );

                System.out.println("Escuchando " + topicName);

                while (true) {

                    Message message = consumer.receive();

                    if (message instanceof TextMessage textMessage) {

                        String json =
                                textMessage.getText();

                        processEvent(topicName, json);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }

    private void processEvent(String topic, String jsonEvent) {

        JsonObject event =
                JsonParser.parseString(jsonEvent)
                        .getAsJsonObject();

        JsonObject data =
                event.getAsJsonObject("data");

        if (topic.equals("News")) {

            datamart.addNewsEvent(jsonEvent);

            String source =
                    data.get("source").getAsString();

            datamart.getNewsCountBySource()
                    .merge(source, 1, Integer::sum);

            System.out.println("News añadida al datamart");

        } else if (topic.equals("Stocks")) {

            datamart.addStockEvent(jsonEvent);

            String symbol =
                    data.get("symbol").getAsString();

            datamart.getLatestStockBySymbol()
                    .put(symbol, jsonEvent);

            System.out.println("Stock añadido al datamart");
        }
    }

    public void processHistoricalEvent(String topic, String jsonEvent) {
        processEvent(topic, jsonEvent);
    }
}