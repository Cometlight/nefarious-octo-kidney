package at.fhv.itb5c.jms;

public class TestJMS {
	public static void main(String[] args) {
		QueueManager qm = new QueueManager("");
		qm.produce("Wow a message!");
		
		QueueManager qr = new QueueManager("");
		System.out.println("Message: " + qr.consume());
	}
}
