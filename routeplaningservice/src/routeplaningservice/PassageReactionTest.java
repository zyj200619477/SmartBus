package routeplaningservice;

import static org.junit.Assert.*;

import org.junit.Test;

public class PassageReactionTest {

	@Test
	public void test() {
	PassagerReaction dr = new PassagerReaction(new AssignAlgorithm() {
			
			int[] my = new int[3];
			@Override
			public void setPassage(int startBus, int endBus) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public int getDriveLocation() {
				// TODO Auto-generated method stub
				return 1;
			}

			@Override
			public int[] getNextRoute() {
				// TODO Auto-generated method stub
				calculateNextRoute(1);
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
		PassagerBean db = new PassagerBean();
		db.setType("U");
		dr.receive(db);
		assertEquals(1,dr.result().getcDriverlocation());
		assertEquals(3,dr.result().getNnDriverLocation());
		assertEquals("UU",dr.result().getType());
	}

}
