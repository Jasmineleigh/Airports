package prob1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AirportManager {
	private Map<String, Airport> airports = new HashMap<String, Airport>();
	
	public AirportManager(Map<String, Airport> airports) {
		this.airports = airports;
	}
	
	// Returns Airport in the manager with the following code
	public Airport getAirport(String code) {
		
		if(airports.containsKey(code)) {
			return airports.get(code);
		}
		
		return null;
	}
	
	// The intended version - returns airport based on distance
	public Airport getAirportClosestTo_Distance(String code) {
		Airport airport = getAirport(code);
		double leastDistance = Integer.MAX_VALUE;
		Airport closest = null;
		
		for(Airport a: airports.values()) {
			double distance = DistanceCalculator.getDistance(airport.getLatitude(), airport.getLongitude(), a.getLatitude(), a.getLongitude());
			if(distance < leastDistance) {
				leastDistance = distance;
				closest = a;
			}
		}
		return closest;
	}
	
	/* 
	 * My version - compares each character of the code 
	 * returns airports whose code is spelled the closest to parameter
	 */
	public Airport getAirportClosestTo_Code(String code) {
		Airport aKey = new Airport(code);
		int minimumDifference = Integer.MAX_VALUE;
		Airport closest = null;
		int maxLettersEqual = 0;
		
		for(Entry<String, Airport> a: airports.entrySet()) {
			if(a.getValue().getCode().equals(aKey.getCode())) {
				return a.getValue();
			}
			else {
				int numEqualLetters = getNumEquals(code, a.getValue().getCode());
				
				if(numEqualLetters == 0 && maxLettersEqual == 0) {
					int val = a.getKey().compareTo(code);
					if(val < minimumDifference) {
						minimumDifference = val;
						closest = a.getValue();
					}
				}
				else {
					if(numEqualLetters > maxLettersEqual) {
						maxLettersEqual = numEqualLetters;
						closest = a.getValue();
					}
					else if(numEqualLetters == 2 && maxLettersEqual == 2) {
						closest = compareDiffWithLastChars(a.getValue(), closest, a.getKey().charAt(2),
								code.charAt(2), closest.getCode().charAt(2));
					}
					else if(numEqualLetters == 1 && maxLettersEqual == 1) {
						return compareDiffWithLast2Chars(a.getValue(), closest, a.getKey().charAt(1),
								code.charAt(1), closest.getCode().charAt(1), a.getKey().charAt(2),
								code.charAt(2), closest.getCode().charAt(2));
					}
				}
			}
		}
		return closest;
	}
	
	// Returns a List of all the Airports in the manager
	public List<Airport> getAirports(){
		List<Airport> airports = new ArrayList<>();
		
		for(Airport a: this.airports.values()) {
			airports.add(a);
		}
		
		return airports;
	}
	
	// Returns List of Airports in the city, regardless of state
	public List<Airport> getAirportsByCity(String city){
		List<Airport> listOfAirports = getAirports();
		List<Airport> airportsInCity = new ArrayList<>();
		
		for(Airport a: listOfAirports) {
			if(a.getCity().equals(city)) {
				airportsInCity.add(a);
			}
		}
		
		return airportsInCity;
	}
	
	// Returns List of Airports in the city and state given 
	public List<Airport> getAirportsByCityState(String city, String state){
		List<Airport> listOfAirports = getAirports();
		List<Airport> airportsInCity = new ArrayList<>();
		
		for(Airport a: listOfAirports) {
			if(a.getCity().contains(city) && a.getState().equals(state)) {
				airportsInCity.add(a);
			}
		}
		
		return airportsInCity;
	}

	// returns List of Airports who are National Weather Stations
	public List<Airport> getNWSNamedAirports(){
		List<Airport> allAirports = getAirports();
		List<Airport> nwsAirports = new ArrayList<>();
		
		for(Airport a: allAirports) {
			if(a.getCode().charAt(2) == 'X') {
				nwsAirports.add(a);
			}
		}
		
		return nwsAirports;
	}
	
	// My version of closestTo method
	public List<Airport> getAirportsClosestTo_Code(String code, int num){
		List<Airport> allAirports = getAirports();
		List<Airport> topNum = new ArrayList<>();

		while(topNum.size() < num) {
			Airport airport = getAirportClosestTo(code, allAirports);
			if(!airport.equals(null)) {
				topNum.add(airport);
				allAirports.remove(airport);
			}
		}
		return topNum;
	}
	
	// The intended closestTo method
	public List<Airport> getAirportsClosestTo_Distance(String code, int num){
		List<Airport> allAirports = getAirports();
		List<Airport> topNum = new ArrayList<>();

		while(topNum.size() < num) {
			Airport airport = getAirportClosestTo_Distance(code, allAirports);
			if(!airport.equals(null)) {
				topNum.add(airport);
				allAirports.remove(airport);
			}
		}
		return topNum;
	}
	
	public List<Airport> getAirportsSortedBy(String sortType){
		List<Airport> sorted = new ArrayList<>(getAirports());
		if(sortType.equals("State")) {
			Collections.sort(sorted, new AirportStateComparator());
		}
		else if(sortType.equals("City")) {
			Collections.sort(sorted, new AirportCityComparator());
		}
		else {
			return sorted;
		}
		return sorted;
	}
	
	
	public List<Airport> getAirportsWithin(double withinDist, double lat, double lon){
		List<Airport> withinRadius = new ArrayList<>();
		
		for(Airport a: getAirports()) {
			new DistanceCalculator();
			if(DistanceCalculator.getDistance(lat, lon, a.getLatitude(), a.getLongitude())<= withinDist) {
				withinRadius.add(a);
			}
		}
		
		return withinRadius;
	}
	
	public List<Airport> getAirportsWithin(String code, double withinDist){
		Airport a = getAirport(code);
		List<Airport> withinDistOfCode = getAirportsWithin(withinDist, a.getLatitude(), a.getLongitude());
		withinDistOfCode.remove(a);
		return withinDistOfCode;
	}
	
	public List<Airport> getAirportsWithin(String code1, String code2, double withinDist){
		List<Airport> airportsWithinDist = getAirportsWithin(code1, withinDist);
		airportsWithinDist.retainAll(getAirportsWithin(code2, withinDist));
		return airportsWithinDist;
	}
	
	public double getDistanceBetween(String code1, String code2) {
		Airport a1 = getAirport(code1);
		Airport a2 = getAirport(code2);
		new DistanceCalculator();
		double distance = DistanceCalculator.getDistance(a1.getLatitude(), a1.getLongitude(), a2.getLatitude(),a2.getLongitude());
		return distance;
	}
	
	public double getDistanceBetween(Airport airport1, Airport airport2) {
		return getDistanceBetween(airport1.getCode(), airport2.getCode());
	}
	
/*
 * Helper Methods
 */

	private Airport getAirportClosestTo(String code, List<Airport> airports) {
		Airport aKey = new Airport(code);
		int min = Integer.MAX_VALUE;
		Airport closest = null;
		int max = 0;
		
		for(Airport a: airports) {
			if(a.getCode().equals(aKey.getCode())) {
				return a;
			}
			else {
				int numEqualLetters = getNumEquals(code, a.getCode());
				
				if(numEqualLetters == 0 && max == 0) {
					int val = a.getCode().compareTo(code);
					if(val < min) {
						min = val;
						closest = a;
					}
				}
				else {
					if(numEqualLetters > max) {
						max = numEqualLetters;
						closest = a;
					}
					else if(numEqualLetters == 2 && max == 2) {
						closest = compareDiffWithLastChars(a, closest, a.getCode().charAt(2), code.charAt(2),
																closest.getCode().charAt(2));
					}
					else if(numEqualLetters == 1 && max == 1) {
						closest = compareDiffWithLast2Chars(a, closest, a.getCode().charAt(1), code.charAt(1), closest.getCode().charAt(1), 
															a.getCode().charAt(2), code.charAt(2), closest.getCode().charAt(2));
					}
				}
			}
		}
		return closest;
	}

	private int getNumEquals(String code, String compCode) {
		int numEqualLetters = 0;
		if(compCode.charAt(0) == code.charAt(0)) {
			numEqualLetters++;
			if(compCode.charAt(1) == code.charAt(1)) {
				numEqualLetters++;
			}
		}
		
		return numEqualLetters;
	}
	
	private Airport compareDiffWithLastChars(Airport a, Airport b, char pos2A, char pos2Code, char pos2B) {
		int diff1 = Math.abs(pos2A - pos2Code); 
		int diff2 = Math.abs(pos2B - pos2Code);
		
		if(diff1 < diff2) {
			return a;
		}
		else {
			return b;
		}
	}
	
	private Airport compareDiffWithLast2Chars(Airport a, Airport b, char pos1A, char pos1Code, char pos1B, char pos2A, char pos2Code, char pos2B) {
		int diff1 = Math.abs(pos1A - pos1Code) + Math.abs(pos2A - pos2Code); 
		int diff2 = Math.abs(pos1B - pos1Code) + Math.abs(pos2B - pos2Code);
		
		if(diff1 < diff2) {
			return a;
		}
		else {
			return b;
		}
	}
	
	private Airport getAirportClosestTo_Distance(String code, List<Airport> allAirports) {
		Airport a = getAirport(code);
		Airport closest = null;
		double leastDistance = Integer.MAX_VALUE;
		
		for(Airport airport: allAirports) {
			if(DistanceCalculator.getDistance(a.getLatitude(), a.getLongitude(), airport.getLatitude(), airport.getLongitude()) < leastDistance) {
				leastDistance = DistanceCalculator.getDistance(a.getLatitude(), a.getLongitude(), airport.getLatitude(), airport.getLongitude());
				closest = airport;
			}
		}
		
		return closest;
		
	}
}
