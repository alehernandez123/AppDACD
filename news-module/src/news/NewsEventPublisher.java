package news;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

public class NewsEventPublisher {

    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String TOPIC_NAME = "News";

    public void publish(String jsonEvent) {

        try {
            ActiveMQConnectionFactory factory =
                    new ActiveMQConnectionFactory(BROKER_URL);

            Connection connection = factory.createConnection();
            connection.start();

            Session session =
                    connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Topic topic = session.createTopic(TOPIC_NAME);

            MessageProducer producer = session.createProducer(topic);

            TextMessage message =
                    session.createTextMessage(jsonEvent);

            producer.send(message);

            System.out.println("Evento enviado a ActiveMQ");

            producer.close();
            session.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}