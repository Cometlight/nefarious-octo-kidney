package at.fhv.itb5c.jms;

import java.util.Hashtable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import at.fhv.itb5c.logging.ILogger;

public class QueueManager implements ILogger {
	ConnectionFactory _cf;
	String MYCF_LOOKUP_NAME = "cn=myQCF";
	String MYQUEUE_LOOKUP_NAME = "cn=myQ";
	Queue _queue;
	Connection _connection;
	Session _session;
	
	public void produce(String textMessage) {
		Hashtable<String, String> env;
		Context ctx = null;

		env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
		// env.put(Context.PROVIDER_URL, url);
		
		try {
			// Create the initial context.
			ctx = new InitialContext(env);
			_cf = (javax.jms.ConnectionFactory) ctx.lookup(MYCF_LOOKUP_NAME);
			_queue = (javax.jms.Queue) ctx.lookup(MYQUEUE_LOOKUP_NAME);
			_connection = _cf.createConnection();
		} catch (NamingException | JMSException e) {
			log.error(e.getMessage());
		}
		
		log.info("Connection established!");
		
		try {
			_session = _connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			log.info("Session created!");

			// Create the MessageProducer and MessageConsumer
			MessageProducer msgProducer = _session.createProducer(_queue);

			log.info("Producer created!");
			
			// Tell the provider to start sending messages.
			_connection.start();

			TextMessage msg = _session.createTextMessage(textMessage);
			
			log.info("Message created!");

			// rcvMsg = (TextMessage) msgConsumer.receive();
			// System.out.println("Received nothing (should be null): " +
			// rcvMsg);

			// Publish the message
			log.info("Publishing a message to Queue: " + _queue.getQueueName());
			msgProducer.send(msg, DeliveryMode.NON_PERSISTENT, 4, 0);

			_connection.close();

		} catch (JMSException e) {
			log.error(e.getMessage());
		}
	}

	public TextMessage consume(String consumer) {
		return null;
	}
}
