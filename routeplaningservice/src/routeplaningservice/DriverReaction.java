package routeplaningservice;

//会接收到1种类型的包 U, 1. 司机已经到达一个站点, 然后发送包, 要求返回下一站的位置,
public class DriverReaction implements ActionsByPacket<DriverBean> {
	private DriverBean newBean;
	private AssignAlgorithm ag;
	public DriverReaction(AssignAlgorithm ag) {
		this.ag = ag;
	}

	@Override
	public  void receive(DriverBean receiveBean) {
		newBean = (DriverBean) receiveBean;
		if(newBean.getType().equals("U")) {
			ag.calculateNextRoute(newBean.getCBusStopLocation());
			int[] newRoute = ag.getNextRoute();
			System.out.printf("%d ",newRoute[1]);
			newBean.setNBusStopLocation(newRoute[1]);
			newBean.setnNBusStopLocaton(newRoute[2]);
			newBean.setType("UU");
			
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public DriverBean result() {
		// TODO Auto-generated method stub
		if(newBean.getType().equals("UU")) {
			return newBean;
			
		}
		// 
		return null;
	}
	

}
