import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


public class MyListener implements MessageListener {
	public void onMessage(Message arg0) {
		TextMessage tm = (TextMessage)arg0; 
		try {
			System.out.println(tm.getText());
		} catch (Exception e) { e.printStackTrace(); }
	}
}
