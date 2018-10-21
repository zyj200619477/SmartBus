package routeplaningservice;

public interface ActionsByPacket<T extends Bean> {
	
	public void receive(T receiveBean);
	
	public  T result( );

	

}
