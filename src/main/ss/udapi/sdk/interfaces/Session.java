package ss.udapi.sdk.interfaces;

import java.util.List;

public interface Session {
	public Service getService(String name);
	public List<Service> getServices();
}
