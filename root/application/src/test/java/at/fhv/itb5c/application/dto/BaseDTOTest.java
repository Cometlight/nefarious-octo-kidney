package at.fhv.itb5c.application.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.fhv.itb5c.commons.dto.BaseDTO;

public class BaseDTOTest {

	@Test
	public void testIdDefault() {
		BaseDTO dto = new BaseDTO();
		assertEquals(null, dto.getId());
	}

	@Test
	public void testId() {
		final Long id = 5l;
		BaseDTO dto = new BaseDTO();
		dto.setId(id);

		assertEquals(id, dto.getId());
	}

	@Test
	public void testVersionDefault() {
		BaseDTO dto = new BaseDTO();
		assertEquals(null, dto.getVersion());
	}

	@Test
	public void testVersion() {
		final Long version = 7l;
		BaseDTO dto = new BaseDTO();
		dto.setVersion(version);

		assertEquals(version, dto.getVersion());
	}

}
