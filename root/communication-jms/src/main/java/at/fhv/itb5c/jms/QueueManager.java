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

import at.fhv.itb5c.commons.property.PropertyManager;
import at.fhv.itb5c.logging.ILogger;

public class QueueManager implements ILogger {
	private String _winPath;
	private String _unixPath;
	private String _cfLookupName;
	private String _qLookupName;

	private Queue _queue;
	private Connection _connection;

	public QueueManager(String name) {
		_cfLookupName = PropertyManager.getInstance().getProperty("at.fhv.itb5c.jms.QF");
		_qLookupName = PropertyManager.getInstance().getProperty("at.fhv.itb5c.jms.Qprefix") + name;
		_winPath = PropertyManager.getInstance().getProperty("at.fhv.itb5c.jms.winPath");
		_unixPath = PropertyManager.getInstance().getProperty("at.fhv.itb5c.jms.unixPath");
		log.debug("Queue Parameters: _cfLookupName = " + _cfLookupName + ", _qLookupName = " + _qLookupName
				+ ", _winPath = " + _winPath + ", _unixPath = " + _unixPath);
	}

	public void produce(String textMessage) {
		// create a new JMS session
		Session session = createSession();
		TextMessage msg;
		try {
			msg = session.createTextMessage(textMessage);
			log.info("Message created!");

			// Publish the message
			log.info("Publishing a message to Queue: " + _queue.getQueueName());

			// Create the MessageProducer
			MessageProducer msgProducer = session.createProducer(_queue);

			log.info("Producer created!");

			// send message to queue
			msgProducer.send(msg, DeliveryMode.NON_PERSISTENT, 4, 0);

			_connection.close();
		} catch (JMSException e) {
			log.error(e.getMessage());
		}
	}

	public String consume() {
		Session session = createSession();
		try {
			// Create the MessageConsumer
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

		// set environment variables
		env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
		if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
			env.put(Context.PROVIDER_URL, _winPath);
		} else {
			env.put(Context.PROVIDER_URL, _unixPath);
		}

		try {
			// Create the initial context.
			ctx = new InitialContext(env);
			ConnectionFactory cf = (javax.jms.ConnectionFactory) ctx.lookup(_cfLookupName);
			_queue = (javax.jms.Queue) ctx.lookup(_qLookupName);
			_connection = cf.createConnection();
		} catch (NamingException | JMSException e) {
			log.error(e.getMessage());
			return null;
		}

		log.info("Connection established!");

		Session session = null;

		try {
			session = _connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			log.info("Session created!");

			// Tell the provider to start sending messages.
			_connection.start();
		} catch (JMSException e) {
			log.error(e.getMessage());
			return null;
		}

		return session;
	}
}
