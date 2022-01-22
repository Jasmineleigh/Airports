package prob1;

public class Airport {
	private String city;
	private String state;
	private String code;
	private double latitude;
	private double longitude;
	
	public Airport(String code, double latitude, double longitude, String city, String state) {
		this.code = code;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.state = state;
	}
	
	protected Airport(String code) {
		this.code = code;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Airport) {
			Airport a = (Airport)o;
			if(this.code.equals(a.code)) {
				return true;
			}
		}
		return false;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getCode() {
		return code;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public String getState() {
		return state;
	}
	
	public String toString() {
		String messageOfAirport = String.format("%s-%s, %s: %.2f, %.2f", code, city, state, latitude, longitude);
		return messageOfAirport;
	}
}
