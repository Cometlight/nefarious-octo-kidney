package at.fhv.itb5c.commons.util.time;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class for time operations.
 */
public class TimeUtility {

	/**
	 * Converts a time string (hh:mm) to a local time instance.
	 *
	 * @param timeString
	 *            the string describing a time
	 * @return a LocalTime; or null, if the string couldn't be parsed
	 */
	public static LocalTime timeStringToLocalTime(String timeString) {
		final String regex = "(\\d\\d):(\\d\\d)";
		if (timeString != null && timeString.matches(regex)) {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(timeString);
			if (matcher.find()) {
				int hour = Integer.parseInt(matcher.group(1));
				int minute = Integer.parseInt(matcher.group(2));
				if (hour < 24 && minute < 60) {
					return LocalTime.of(hour, minute);
				}
			}
		}
		return null;
	}
}
