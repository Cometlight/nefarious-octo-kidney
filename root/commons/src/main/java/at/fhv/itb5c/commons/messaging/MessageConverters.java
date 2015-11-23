package at.fhv.itb5c.commons.messaging;

import java.util.HashMap;
import java.util.Map;

public class MessageConverters {

	/**
	 * Call this method once at startup to register the converter functions
	 * defined here.
	 */
	static void init() {
		MessageConverter.registerConverter("rsvp", MessageConverters::rsvpMessageConverter);
	}

	/**
	 * Parses the data part of a message string and builds a map containing
	 * these values.
	 *
	 * @param data
	 *            the data part of a message string
	 * @return a map containing
	 */
	public static Map<String, Object> rsvpMessageConverter(String data) {
		try {
			int tournamentId = Integer.parseInt(data);
			HashMap<String, Object> map = new HashMap<>();
			map.put("tournamentId", tournamentId);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
