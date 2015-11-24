package at.fhv.itb5c.jms;

import java.util.HashMap;

import at.fhv.itb5c.jms.entity.Message;

public class TestJMS {
	public static void main(String[] args) {
		QueueManager qm = new QueueManager("testQ");
		HashMap<String, Object> data = new HashMap<>();
		data.put("data1", "This is the data content");
		Message source = new Message("Testmessage",data);
		qm.produce(source);
		
		QueueManager qr = new QueueManager("testQ");
		Message dest = qr.consume();
		System.out.println("Message: " + dest.getKind() + ": " + dest.get("data1").toString());
	}
}
