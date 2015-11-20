package at.fhv.itb5c.jms;

import java.util.Hashtable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import at.fhv.itb5c.logging.ILogger;

public class QueueManager implements ILogger {
	String def_windows_url = "file:///C:/tmp";
	ConnectionFactory _cf;
	String MYCF_LOOKUP_NAME = "cn=myQCF";
	String MYQUEUE_LOOKUP_NAME = "cn=myQ";
	Queue _queue;
	Connection _connection;

	public void produce(String textMessage) {

		Session session = createSession();
		TextMessage msg;
		try {
			msg = session.createTextMessage(textMessage);
			log.info("Message created!");

			// rcvMsg = (TextMessage) msgConsumer.receive();
			// System.out.println("Received nothing (should be null): " +
			// rcvMsg);

			// Publish the message
			log.info("Publishing a message to Queue: " + _queue.getQueueName());

			// Create the MessageProducer and MessageConsumer
			MessageProducer msgProducer = session.createProducer(_queue);

			log.info("Producer created!");

			msgProducer.send(msg, DeliveryMode.NON_PERSISTENT, 4, 0);

			_connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String consume() {
		Session session = createSession();
		try {
			MessageConsumer msgConsumer = session.createConsumer(_queue);
			TextMessage msg = (TextMessage) msgConsumer.receive();

			_connection.close();
			
			return msg.getText();
		} catch (JMSException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	private Session createSession() {
		Hashtable<String, String> env;
		Context ctx = null;

		env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
		env.put(Context.PROVIDER_URL, def_windows_url);

		try {
			// Create the initial context.
			ctx = new InitialContext(env);
			ConnectionFactory cf = (javax.jms.ConnectionFactory) ctx.lookup(MYCF_LOOKUP_NAME);
			_queue = (javax.jms.Queue) ctx.lookup(MYQUEUE_LOOKUP_NAME);
			_connection  = cf.createConnection();
		} catch (NamingException | JMSException e) {
			log.error(e.getMessage());
		}

		log.info("Connection established!");

		Session session = null;
		
		try {
			session = _connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			log.info("Session created!");

			// Tell the provider to start sending messages.
			_connection.start();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return session;
	}
}
