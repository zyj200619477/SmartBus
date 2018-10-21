package routeplaningservice;

import static org.junit.Assert.*;

import org.junit.Test;

public class DriverReactionTest {

	@Test
	public void test() {
		DriverReaction dr = new DriverReaction(new AssignAlgorithm() {
			
			int[] my = new int[3];
			@Override
			public void setPassage(int startBus, int endBus) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public int getDriveLocation() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int[] getNextRoute() {
				// TODO Auto-generated method stub
				
				return my;
			}

			@Override
			public void calculateNextRoute(int currentLocation) {
				my[0] = 1;
				my[1] = 2;
				my[2] = 3; 
				// TODO Auto-generated method stub
				
			}
			
		});
		DriverBean db = new DriverBean();
		db.setType("U");
		dr.receive(db);
		assertEquals(2,dr.result().getNBusStopLocation());
		assertEquals(3,dr.result().getnNBusStopLocaton());
		assertEquals("UU",dr.result().getType());
		
	}

}
