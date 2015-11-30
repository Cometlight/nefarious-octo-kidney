package at.fhv.itb5c.application.converter;

import at.fhv.itb5c.application.dto.MessageDTO;
import at.fhv.itb5c.jms.entity.Message;

public class ConverterMessageDTO {
	public static MessageDTO toDTO(Message message) {
		MessageDTO messagedto = null;
		if (message != null) {
			messagedto = new MessageDTO(message.getKind(), message.getData());
		}
		return messagedto;
	}

	public static Message toEntity(MessageDTO messagedto) {
		Message message = null;
		if (messagedto != null) {
			message = new Message(messagedto.getKind(), messagedto.getData());
		}
		return message;
	}
}
