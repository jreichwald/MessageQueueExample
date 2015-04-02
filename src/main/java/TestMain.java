import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


public class TestMain {

	public static void main(String[] args) {
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
		
		try {
			Connection con = connectionFactory.createConnection();
			con.start();
			
			Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE); 
			Destination destination = session.createQueue("TestQueue");
			
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			TextMessage tm = session.createTextMessage("Das hier ist ein test!"); 
		
			MessageConsumer consumer = session.createConsumer(destination); 
			consumer.setMessageListener(new MyListener());
			con.start();
			
			producer.send(tm); 
			
			System.out.println("Message sent!");
			
			Thread.sleep(2000);
			
			con.stop();
			
			
		//	TextMessage tm2 = (TextMessage) consumer.receive();
			
		//	System.out.print("Received Message: ");
		//	System.out.println(tm2.getText());
			
		
			
		//	consumer.receive();
			
			
			Thread.sleep(2000);
			
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
