package at.fhv.itb5c.jms.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Converts strings to Message objects. All message strings have the following
 * structure: `kind`:`data`. To use this class, message converter functions have
 * to be registered here using {@link #registerConverter(String, Function)}.
 * Some of these converter functions are defined in {@link MessageConverters}.
 */
public class MessageConverter {
	private static HashMap<String, Function<String, Map<String, Object>>> _converters = new HashMap<>();
	private static final Pattern PATTERN = Pattern.compile("([a-z\\-]+):(.+)");

	/**
	 * Registers a message converter for a specified message kind.
	 *
	 * @param kind
	 *            a string equal to the message kind this converter can deal
	 *            with
	 * @param converter
	 *            a converter function that converts the data part of the
	 *            message string
	 */
	public static void registerConverter(String kind, Function<String, Map<String, Object>> converter) {
		_converters.put(kind, converter);
	}

	/**
	 * General implementation of the conversion from string to {@link Message}.
	 *
	 * @param str
	 *            the message string describing a message (`kind`:`data`).
	 * @return a {@link Message} object
	 */
	public static Message toMessage(String str) {
		if (str == null) {
			return null;
		}

		Matcher matcher = PATTERN.matcher(str);
		if (matcher.find() && matcher.groupCount() == 2) {
			final String kind = matcher.group(1);
			Function<String, Map<String, Object>> converter = _converters.get(kind);
			if (converter != null) {
				final Map<String, Object> data = converter.apply(matcher.group(2));
				if (data != null) {
					return new Message(kind, data);
				}
			}
		}

		return null;
	}
}
