package view;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.LocationDao;

public class Locationtest {

	@Test
	public void test() {
		LocationDao dao = new LocationDao();
		assertNotNull(dao.getAllLocations());
	}

}
