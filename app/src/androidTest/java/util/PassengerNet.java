package util;

public interface PassengerNet<PassengerResponse> {
    public void connect(int id);
    public void disconnect(int id);
    public PassengerResponse getPassengerResponse(int location);
    public PassengerResponse registerForBus(int location);
}
