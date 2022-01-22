package prob2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import prob1.AirportManager;

public class FlightManager {
	private AirportManager airportManager = null;
	private Map<String, Flight> flights = new HashMap<>();
	
	public FlightManager(AirportManager airportManager) {
		this.airportManager = airportManager;
	}
	
	public boolean addFlight(Flight flight) {
		
		if(flights.containsKey(flight.getNumber())){
			return false;
		}
		
		flights.put(flight.getNumber(), flight);
		return true;
	}
	
	public boolean addFlight(String number, String date, String originCode, String destinationCode, double cost) throws Exception {
		
		if(flights.containsKey(number)) {
			return false;
		}
		
		Flight flight = new Flight(number, LocalDate.parse(date), airportManager.getAirport(originCode), 
									airportManager.getAirport(destinationCode), cost);
		flights.put(number, flight);
		return true;
	}
	
	public List<Flight> getFlights(){
		List<Flight> flightsOnly = new ArrayList<>(flights.values());
		return flightsOnly;
	}
	
	public Flight getFlight(String number){
		
		if(flights.containsKey(number)) {
			return flights.get(number);
		}
		
		return null;
	}
	
	public List<Flight> getFlightsByOrigin(String originCode){
		List<Flight> flightsWithOrigin = new ArrayList<>();
		
		for(Flight flight: flights.values()) {
			if (doesMatchOrigin(originCode, flight)) {
				flightsWithOrigin.add(flight);
			}
		}
		
		return flightsWithOrigin;
	}
	
	public List<Flight> getFlightsByOrigin(String originCode, String date){
		List<Flight> flightsWithOrigin = new ArrayList<>();
		
		for(Flight flight: flights.values()) {
			if (doesMatchOrigin(originCode, flight) && doesMatchDate(date, flight)) {
				flightsWithOrigin.add(flight);
			}
		}
		
		return flightsWithOrigin;
	}
	
	public List<Flight> getFlightsByOriginAndDestination(String originCode, String destinationCode, String date){
		List<Flight> flightsWithOrigin = new ArrayList<>();
		
		for(Flight flight: flights.values()) {
			if (doesMatchOrigin(originCode, flight) && doesMatchDate(date, flight) && doesMatchDestination(destinationCode, flight)) {
				flightsWithOrigin.add(flight);
			}
		}
		
		return flightsWithOrigin;
	}
	
	public int getNumFlights() {
		return flights.size();
	}
	
	public Flight removeFlight(String number) {
		return flights.remove(number);
	}
	
	/*
	 * Helpers
	 */
	private boolean doesMatchDate(String date, Flight flight) {
		
		if(flight.getDate().equals(LocalDate.parse(date))) {
			return true;
		}
		
		return false;
	}
	
	private boolean doesMatchDestination(String destinationCode, Flight flight) {
		
		if(destinationCode.equals(flight.getDestination().getCode())) {
			return true;
		}
		
		return false;
	}
	
	private boolean doesMatchOrigin(String originCode, Flight flight) {
		
		if(flight.getOrigin().getCode().equals(originCode)) {
			return true;
		}
		
		return false;
	}
	

}
