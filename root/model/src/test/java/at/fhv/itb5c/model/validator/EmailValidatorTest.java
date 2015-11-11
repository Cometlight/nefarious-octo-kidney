package at.fhv.itb5c.model.validator;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmailValidatorTest {

	@Test
	public final void testEmail() {
		String[] validEmailAddresses = new String[] { 
				"robert.codd@hotmail.com", 
				"rd@students.fhv.at", 
				"r-d.421@1-2.de",
				"äöüßÄÖÜ@äöüßÄÜß.at" 
		};

		String[] invalidEmailAddresses = new String[] { 
				"robert", 
				"robert@.com", 
				"robert@gmail.a", // top level domain has to be at least 2 characters
				".robert@gmail.com", "robert()*@gamil.com", // no ()*
				"robert..codd@aol.com", // no double dots
				"robert.@aol.com", // first part may not end with dot
				"robert@codd@gmail.com", "robert@aol.1a" // no numbers in top-level-domain
		};

		for (int i = 0; i < validEmailAddresses.length; i++) {
			assertTrue(EmailValidator.validate(validEmailAddresses[i]));
		}

		for (int j = 0; j < invalidEmailAddresses.length; j++) {
			assertFalse(EmailValidator.validate(invalidEmailAddresses[j]));
		}
	}
}
