/**
 * QueueManager for producing and consuming messages on/from the queue broker.
 * The imqbroker (glassfish) needs to be running.
 * New queues are automatically generated.
 * (https://glassfish.java.net/docs/4.0/mq-dev-guide-java.pdf) 
 */
package at.fhv.itb5c.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import com.sun.messaging.AdministeredObject;
import com.sun.messaging.ConnectionConfiguration;

import at.fhv.itb5c.commons.property.PropertyManager;
import at.fhv.itb5c.jms.entity.Message;
import at.fhv.itb5c.logging.ILogger;

public class QueueManager implements ILogger {
	private String _qName;
	private String _brokerList;
	private int _timeoutConsumer;

	private Queue _queue;
	private Connection _connection;

	public QueueManager(String name) {
		// initializing imq paramters
		_qName = PropertyManager.getInstance().getProperty("at.fhv.itb5c.jms.Qprefix") + name;
		_timeoutConsumer = Integer
				.parseInt(PropertyManager.getInstance().getProperty("at.fhv.itb5c.jms.consumer.timeout"));
		_brokerList = PropertyManager.getInstance().getProperty("at.fhv.itb5c.jms.brokerlist");
		log.debug("Queue Parameters: _cfLookupName = " + _qName + ", _timeoutConsumer = " + _timeoutConsumer
				+ ", _brokerList = " + _brokerList);
	}

	public void produce(Message message) {
		// create a new JMS session
		Session session = createSession();
		ObjectMessage msg;
		try {
			// change to .createObjectMessage(Serializable)
			msg = session.createObjectMessage(message);

			// Create the MessageProducer
			MessageProducer msgProducer = session.createProducer(_queue);

			// send message to queue
			msgProducer.send(msg, DeliveryMode.NON_PERSISTENT, 4, 0);

			log.info("Message published to queue: " + _queue.getQueueName());
			_connection.close();
		} catch (JMSException e) {
			log.error(e.getMessage());
		}
	}

	public Message consume() {
		Session session = createSession();
		try {
			// Create the MessageConsumer
			MessageConsumer msgConsumer = session.createConsumer(_queue);
			ObjectMessage msg = (ObjectMessage) msgConsumer.receive(_timeoutConsumer);
			log.info("Message consumed from queue: " + _queue.getQueueName());

			_connection.close();

			if (msg == null) {
				return null;
			}

			return (Message) msg.getObject();
		} catch (JMSException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	private Session createSession() {
		try {
			// create new connection factory
			ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
			((AdministeredObject) cf).setProperty(ConnectionConfiguration.imqAddressList, _brokerList);
			((AdministeredObject) cf).setProperty(ConnectionConfiguration.imqReconnectEnabled, "true");
			// create new queue
			_queue = new com.sun.messaging.Queue(_qName);
			// create connection
			_connection = cf.createConnection();
		} catch (JMSException e) {
			log.error(e.getMessage());
			return null;
		}

		log.info("Connection established!");

		Session session = null;

		// create session and start connection
		try {
			session = _connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			log.info("Session created!");

			_connection.start();
		} catch (JMSException e) {
			log.error(e.getMessage());
			return null;
		}

		return session;
	}
}
