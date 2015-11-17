package at.fhv.itb5c.commons.util.time;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TimeUtilityTest {

	@Test
	public void testNull() {
		assertNull(TimeUtility.timeStringToLocalTime(null));
	}

	@Test
	public void testWrongFormat() {
		assertNull(TimeUtility.timeStringToLocalTime("12:ab"));
	}

	@Test
	public void testCorrectFormatButNoTime() {
		assertNull(TimeUtility.timeStringToLocalTime("25:34"));
	}

	@Test
	public void testCorrectFormat() {
		assertNotNull(TimeUtility.timeStringToLocalTime("13:45"));
	}

	@Test
	public void testLowerBound() {
		assertNotNull(TimeUtility.timeStringToLocalTime("00:00"));
	}

	@Test
	public void testUpperBound() {
		assertNull(TimeUtility.timeStringToLocalTime("24:00"));
	}

}
