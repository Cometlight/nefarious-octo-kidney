package at.fhv.itb5c.application.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import at.fhv.itb5c.commons.dto.LeagueDTO;
import at.fhv.itb5c.commons.enums.TypeOfSport;

public class LeagueDTOTest {

	@Test
	public void testDefaultName() {
		LeagueDTO league = new LeagueDTO();
		assertNull(league.getName());
	}

	@Test
	public void testDefaultTypeOfSport() {
		LeagueDTO league = new LeagueDTO();
		assertNull(league.getTypeOfSport());
	}

	@Test
	public void testName() {
		final String name = "League Name";
		LeagueDTO league = new LeagueDTO();
		league.setName(name);
		assertEquals(name, league.getName());
	}

	@Test
	public void testTypeOfSport() {
		final TypeOfSport typeOfSport = TypeOfSport.Tennis;
		LeagueDTO league = new LeagueDTO();
		league.setTypeOfSport(typeOfSport);
		assertEquals(typeOfSport, league.getTypeOfSport());
	}

}
