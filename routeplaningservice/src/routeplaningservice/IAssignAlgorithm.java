package routeplaningservice;

public class IAssignAlgorithm implements AssignAlgorithm {

	int[] r = new int[3];
	int[] passagerNumber= new int[8];
	@Override
	public void setPassage(int startBus, int endBus) {
		passagerNumber[startBus-1]++;
		passagerNumber[endBus-1]++;
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDriveLocation() {
		// TODO Auto-generated method stub
		return r[0];
	}

	@Override
	public int[] getNextRoute() {
		// TODO Auto-generated method stub
		return r;
	}

	@Override
	public void calculateNextRoute(int currentLocation) {
		// TODO Auto-generated method stub
		int input=currentLocation;
		
			switch(input){
			case 1:
				r[0]=input;
				r[1]=2;
				r[2]=5;
				break;
			case 2:
				r[0]=input;
				r[1]=3;
				r[2]=5;
				break;
			case 3:
				r[0]=input;
				r[1]=5;
				r[2]=4;
				break;
			case 4:
				r[0]=input;
				r[1]=6;
				r[2]=7;
				break;
			case 5:
				r[0]=input;
				r[1]=4;
				r[2]=6;
				break;
			case 6:
				r[0]=input;
				r[1]=7;
				r[2]=-1;
				break;
			case 7:
				r[0]=input;
				r[1]=-1;
				r[2]=-1;
				break;
			case 8:
				r[0]=input;
				r[1]=-1;
				r[2]=-1;
			}

		}
	}

