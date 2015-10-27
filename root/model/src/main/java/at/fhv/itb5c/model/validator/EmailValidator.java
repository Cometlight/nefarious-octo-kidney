package at.fhv.itb5c.model.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
	private static Pattern pattern;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	static {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	/**
	 * Validates email address with regular expression.
	 * 
	 * @param emailAddress
	 *            email address for validation
	 * @return true valid email address, false otherwise
	 */
	public static boolean validate(final String emailAddress) {
		Matcher matcher = pattern.matcher(emailAddress);
		return matcher.matches();
	}
}
