package at.fhv.itb5c.rmi.server.converter;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.MessageDTO;
import at.fhv.itb5c.commons.dto.rmi.IMessageRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.server.rmiclasses.MessageRMI;

public class ConverterMessageRMI implements ILogger {
	public static IMessageRMI toRMI(MessageDTO messagedto){
		IMessageRMI messagermi = null;
		if(messagedto != null) {
			try {
				messagermi = new MessageRMI(messagedto.getKind(), messagedto.getData());
			} catch (RemoteException e) {
				log.error(e.getMessage());
				return null;
			}
		}
		return messagermi;
	}
	
	public static MessageDTO toDTO(IMessageRMI messagermi){
		MessageDTO messagedto;
		try {
			messagedto = new MessageDTO(messagermi.getKind(), messagermi.getData());
		} catch (RemoteException e) {
			log.error(e.getMessage());
			return null;
		}
		return messagedto;
	}
}
