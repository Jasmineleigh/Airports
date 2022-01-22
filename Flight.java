package prob2;

import java.time.LocalDate;
import prob1.Airport;
import prob1.DistanceCalculator;

public class Flight {
	private String number;
	private LocalDate date;
	private Airport origin;
	private Airport destination;
	private double distance;
	private double cost;
	private Exception InvalidArgumentException;

	public Flight (String number, LocalDate date, Airport origin, Airport destination, double cost) throws Exception {
		
		if(isFlightNumValid(number)) {
			this.number = number;
		}
		else {
			throw InvalidArgumentException;
		}
		
		this.date = date;
		this.origin = origin;
		this.destination = destination;
		this.cost = cost;
		this.distance = getDistanceBetween();
	}
	
	public double getCost() {
		return cost;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public Airport getDestination() {
		return destination;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public String getNumber() {
		return number;
	}
	
	public Airport getOrigin() {
		return origin;
	}
	
	private double getDistanceBetween() {
		new DistanceCalculator();
		return DistanceCalculator.getDistance(origin.getLatitude(), origin.getLongitude(),
									destination.getLatitude(), destination.getLongitude());
	}
	
	private boolean isFlightNumValid(String number) {
		if(number.length() == 4) {
			return true;
		}
		return false;
	}
	
//	String location(Airport a) {
//		
//	}
	
	public String toString() {
		String str = "";
		String flight = String.format("%-2sFlight: %s, Date:" + date + "\n", str, number, date);
		String from = String.format("%-4sFrom: %s-%s, %s\n", str, origin.getCode(), origin.getCity(), origin.getState());
		String to = String.format("%-6sTo: %s-%s, %s\n", str, destination.getCode(), destination.getCity(), destination.getState());
		String distanceString = String.format("Distance: %.0f miles, Cost: $%.2f\n", distance, cost);
		return flight + from + to + distanceString;
	}
}
