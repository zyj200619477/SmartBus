package huang.bling.smartbus.util;

public interface DriverNet<DriverResponse> {
    public void connect(int id);
    public void disconnect(int id);
    public DriverResponse getDriverResponse(int location);

}
