package eventstore;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class EventStoreConsumer {

    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String CLIENT_ID = "event-store-builder";

    public void start() {
        consumeTopic("News", "news-subscription");
        consumeTopic("Stocks", "stocks-subscription");
    }

    private void consumeTopic(String topicName, String subscriptionName) {
        new Thread(() -> {
            try {
                ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);

                Connection connection = factory.createConnection();
                connection.setClientID(CLIENT_ID + "-" + topicName);
                connection.start();

                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Topic topic = session.createTopic(topicName);

                MessageConsumer consumer = session.createDurableSubscriber(topic, subscriptionName);

                System.out.println("Escuchando topic: " + topicName);

                while (true) {
                    Message message = consumer.receive();

                    if (message instanceof TextMessage textMessage) {
                        String json = textMessage.getText();

                        System.out.println("Evento recibido de " + topicName + ":");
                        System.out.println(json);

                        EventStoreWriter writer = new EventStoreWriter();
                        writer.write(topicName, json);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}