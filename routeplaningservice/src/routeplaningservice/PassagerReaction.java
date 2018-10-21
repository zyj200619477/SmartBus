package routeplaningservice;

//1. 两个type, 1. type U, 要求更新司机所在,返回司机当前位置, 2 type R, 要求乘坐汽车

public class PassagerReaction implements ActionsByPacket<PassagerBean>  {
	private AssignAlgorithm ag;
	private PassagerBean newBean = new PassagerBean();
	
	public PassagerReaction(AssignAlgorithm ag) {
		this.ag = ag;
		
	}

	@Override
	public  void receive(PassagerBean receiveBean) {
		newBean = (PassagerBean) receiveBean;
		newBean.setType("UU");
		int[]  aRoute= ag.getNextRoute();
		newBean.setcDriverlocation(aRoute[0]);
		newBean.setnDriverLocation(aRoute[1]);
		newBean.setNnDriverLocation(aRoute[2]);
		
		if(newBean.getType().equals("U")) {

		// TODO Auto-generated method stub

			
		}else if(newBean.getType().equals("R")) {
			
			ag.setPassage(newBean.getCBusStopLocation(), newBean.getNBusStopLocation());						
		}

	
		
	}

	@Override
	public PassagerBean result() {
		

		
		if(newBean.getType().equals("UU")) {

			return  newBean;
			
		}
		// TODO Auto-generated method stub
		return null;
	}


	

}
